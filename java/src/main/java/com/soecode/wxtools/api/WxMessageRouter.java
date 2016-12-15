package com.soecode.wxtools.api;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;

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
 * WxMessageRouter router = new WxMessageRouter(wxService);
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

	//规则集合
	private final List<WxMessageRouterRule> rules = new ArrayList<WxMessageRouterRule>();
	//业务
	private final IService iService;
	//异常处理器
	private WxErrorExceptionHandler exceptionHandler;

	//消息路由器构造方法
	public WxMessageRouter(IService iService) {
		this.iService = iService;
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
		for (final WxMessageRouterRule rule : matchRules) {
			// 在同步操作结束
			res = rule.service(wxMessage, iService, exceptionHandler);	
		}
		return res;
	}

}
