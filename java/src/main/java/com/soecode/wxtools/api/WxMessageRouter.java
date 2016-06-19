package com.soecode.wxtools.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.handler.WxErrorExceptionHandler;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link WxMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link WxMessageRouterRule#end()}或者{@link WxMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * WxCpMessageRouter router = new WxCpMessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将WxXmlMessage交给消息路由器
 * router.route(message);
 *
 * </pre>
 * 
 * @author antgan
 *
 */
public class WxMessageRouter {
	//默认线程池为100
	private static final int DEFAULT_THREAD_POOL_SIZE = 100;
	//规则集合
	private final List<WxMessageRouterRule> rules = new ArrayList<WxMessageRouterRule>();
	//业务
	private final WxService wxService;
	//线程池执行服务
	private ExecutorService executorService;
	//重复检查器
	private WxMessageDuplicateChecker messageDuplicateChecker;
	//异常处理器
	private WxErrorExceptionHandler exceptionHandler;

	//消息路由器构造方法
	public WxMessageRouter(WxService wxService) {
		this.wxService = wxService;
		this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
		this.messageDuplicateChecker = new WxMessageInMemoryDuplicateChecker();
	}

	/**
	 * <pre>
	 * 设置自定义的 {@link ExecutorService}
	 * 如果不调用该方法，默认使用 Executors.newFixedThreadPool(100)
	 * </pre>
	 * 
	 * @param executorService
	 */
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	/**
	 * <pre>
	 * 设置自定义的 {@link com.soecode.wxtools.api.WxMessageDuplicateChecker}
	 * 如果不调用该方法，默认使用 {@link com.soecode.wxtools.api.WxMessageInMemoryDuplicateChecker}
	 * </pre>
	 * 
	 * @param messageDuplicateChecker
	 */
	public void setMessageDuplicateChecker(WxMessageDuplicateChecker messageDuplicateChecker) {
		this.messageDuplicateChecker = messageDuplicateChecker;
	}

	/**
	 * <pre>
	 * 设置自定义的{@link com.soecode.wxtools.api.WxErrorExceptionHandler}
	 * </pre>
	 * 
	 * @param exceptionHandler
	 */
	public void setExceptionHandler(WxErrorExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	List<WxMessageRouterRule> getRules() {
		return this.rules;
	}

	/**
	 * 开始一个新的Route规则
	 * 
	 * @return
	 */
	public WxMessageRouterRule rule() {
		return new WxMessageRouterRule(this);
	}

	/**
	 * 处理微信消息
	 * 
	 * @param wxMessage
	 */
	public WxXmlOutMessage route(final WxXmlMessage wxMessage) {
		if (isDuplicateMessage(wxMessage)) {
			// 如果是重复消息，那么就不做处理
			return null;
		}

		final List<WxMessageRouterRule> matchRules = new ArrayList<WxMessageRouterRule>();
		// 收集匹配的规则
		for (final WxMessageRouterRule rule : rules) {
			if (rule.test(wxMessage)) {
				matchRules.add(rule);
				//如果含有next()，继续匹配规则
				if (!rule.isReEnter()) {
					break;
				}
			}
		}
		
		//如果没有规则匹配成功，返回null
		if (matchRules.size() == 0) {
			return null;
		}

		WxXmlOutMessage res = null;
		//创建Future接口，用于管理线程
		final List<Future> futures = new ArrayList<Future>();
		for (final WxMessageRouterRule rule : matchRules) {
			// 返回最后一个非异步的rule的执行结果
			if (rule.isAsync()) {
				futures.add(executorService.submit(new Runnable() {
					@Override
					public void run() {
						rule.service(wxMessage, wxService, exceptionHandler);
					}
				}));
			} else {
				// 在同步操作结束
				res = rule.service(wxMessage, wxService, exceptionHandler);	
			}
		}
		return res;
	}

	/**
	 * 验证是否重复消息
	 * @param wxMessage
	 * @return
	 */
	protected boolean isDuplicateMessage(WxXmlMessage wxMessage) {
		String messageId = "";
		if (wxMessage.getMsgId() == null) {
			messageId = String.valueOf(wxMessage.getCreateTime()) + "-"
					+ wxMessage.getFromUserName() + "-"
					+ String.valueOf(wxMessage.getEventKey() == null ? "" : wxMessage.getEventKey()) + "-"
					+ String.valueOf(wxMessage.getEvent() == null ? "" : wxMessage.getEvent());
		} else {
			messageId = String.valueOf(wxMessage.getMsgId());
		}
		if (messageDuplicateChecker.isDuplicate(messageId)) {
			return true;
		}
		return false;
	}

}
