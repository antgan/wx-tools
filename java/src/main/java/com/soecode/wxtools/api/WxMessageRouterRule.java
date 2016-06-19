package com.soecode.wxtools.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.handler.WxErrorExceptionHandler;

/**
 * 路由规则
 * @author antgan
 *
 */
public class WxMessageRouterRule {
	/**
	 * 路由builder
	 */
	private final WxMessageRouter routerBuilder;

	/**
	 * 是否异步，默认为true
	 */
	private boolean async = true;

	private String fromUser;

	private String msgType;

	private String event;

	private String eventKey;

	private String content;

	private String rContent;

	/**
	 * 消息匹配器
	 */
	private WxMessageMatcher matcher;

	/**
	 * 当调用next()时，reEnter=true。代表消息可以进入其他规则
	 */
	private boolean reEnter = false;

	private List<WxMessageHandler> handlers = new ArrayList<WxMessageHandler>();

	private List<WxMessageInterceptor> interceptors = new ArrayList<WxMessageInterceptor>();

	protected WxMessageRouterRule(WxMessageRouter routerBuilder) {
		this.routerBuilder = routerBuilder;
	}

	/**
	 * 设置是否异步执行，默认是true
	 *
	 * @param async
	 * @return
	 */
	public WxMessageRouterRule async(boolean async) {
		this.async = async;
		return this;
	}

	/**
	 * 如果msgType等于某值
	 *
	 * @param msgType
	 * @return
	 */
	public WxMessageRouterRule msgType(String msgType) {
		this.msgType = msgType;
		return this;
	}

	/**
	 * 如果event等于某值
	 *
	 * @param event
	 * @return
	 */
	public WxMessageRouterRule event(String event) {
		this.event = event;
		return this;
	}

	/**
	 * 如果eventKey等于某值
	 *
	 * @param eventKey
	 * @return
	 */
	public WxMessageRouterRule eventKey(String eventKey) {
		this.eventKey = eventKey;
		return this;
	}

	/**
	 * 如果content等于某值
	 *
	 * @param content
	 * @return
	 */
	public WxMessageRouterRule content(String content) {
		this.content = content;
		return this;
	}

	/**
	 * 如果content匹配该正则表达式
	 *
	 * @param regex
	 * @return
	 */
	public WxMessageRouterRule rContent(String regex) {
		this.rContent = regex;
		return this;
	}

	/**
	 * 如果fromUser等于某值
	 *
	 * @param fromUser
	 * @return
	 */
	public WxMessageRouterRule fromUser(String fromUser) {
		this.fromUser = fromUser;
		return this;
	}

	/**
	 * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
	 *
	 * @param matcher
	 * @return
	 */
	public WxMessageRouterRule matcher(WxMessageMatcher matcher) {
		this.matcher = matcher;
		return this;
	}

	/**
	 * 设置微信消息拦截器
	 *
	 * @param interceptor
	 * @return
	 */
	public WxMessageRouterRule interceptor(WxMessageInterceptor interceptor) {
		return interceptor(interceptor, (WxMessageInterceptor[]) null);
	}

	/**
	 * 设置微信消息拦截器
	 *
	 * @param interceptor
	 * @param otherInterceptors
	 * @return
	 */
	public WxMessageRouterRule interceptor(WxMessageInterceptor interceptor,
			WxMessageInterceptor... otherInterceptors) {
		this.interceptors.add(interceptor);
		if (otherInterceptors != null && otherInterceptors.length > 0) {
			for (WxMessageInterceptor i : otherInterceptors) {
				this.interceptors.add(i);
			}
		}
		return this;
	}

	/**
	 * 设置微信消息处理器
	 *
	 * @param handler
	 * @return
	 */
	public WxMessageRouterRule handler(WxMessageHandler handler) {
		return handler(handler, (WxMessageHandler[]) null);
	}

	/**
	 * 设置微信消息处理器
	 *
	 * @param handler
	 * @param otherHandlers
	 * @return
	 */
	public WxMessageRouterRule handler(WxMessageHandler handler, WxMessageHandler... otherHandlers) {
		this.handlers.add(handler);
		if (otherHandlers != null && otherHandlers.length > 0) {
			for (WxMessageHandler i : otherHandlers) {
				this.handlers.add(i);
			}
		}
		return this;
	}

	/**
	 * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
	 *
	 * @return
	 */
	public WxMessageRouter end() {
		this.routerBuilder.getRules().add(this);
		return this.routerBuilder;
	}

	/**
	 * 规则结束，但是消息还会进入其他规则
	 *
	 * @return
	 */
	public WxMessageRouter next() {
		this.reEnter = true;
		return end();
	}

	/**
	 * 验证路由条件
	 * @param wxMessage
	 * @return
	 */
	protected boolean test(WxXmlMessage wxMessage) {
		return (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUserName()))
				&& (this.msgType == null || this.msgType.equals(wxMessage.getMsgType()))
				&& (this.event == null || this.event.equals(wxMessage.getEvent()))
				&& (this.eventKey == null || this.eventKey.equals(wxMessage.getEventKey()))
				&& (this.content == null
						|| this.content.equals(wxMessage.getContent() == null ? null : wxMessage.getContent().trim()))
				&& (this.rContent == null || Pattern.matches(this.rContent,
						wxMessage.getContent() == null ? "" : wxMessage.getContent().trim()))
				&& (this.matcher == null || this.matcher.match(wxMessage));
	}

	/**
	 * 处理微信推送过来的消息
	 *
	 * @param wxMessage
	 * @param wxService
	 * @param exceptionHandler
	 * @return 
	 */
	protected WxXmlOutMessage service(WxXmlMessage wxMessage, WxService wxService,
			WxErrorExceptionHandler exceptionHandler) {
		try {
			Map<String, Object> context = new HashMap<String, Object>();
			// 如果拦截器不通过，返回null
			for (WxMessageInterceptor interceptor : this.interceptors) {
				if (!interceptor.intercept(wxMessage, context, wxService)) {
					return null;
				}
			}
			// 交给handler处理
			WxXmlOutMessage res = null;
			for (WxMessageHandler handler : this.handlers) {
				// 返回最后handler的结果
				res = handler.handle(wxMessage, context, wxService);
			}
			return res;
		} catch (WxErrorException e) {
			exceptionHandler.handle(e);
		}
		return null;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public void setMatcher(WxMessageMatcher matcher) {
		this.matcher = matcher;
	}

	public void setHandlers(List<WxMessageHandler> handlers) {
		this.handlers = handlers;
	}

	public void setInterceptors(List<WxMessageInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public boolean isReEnter() {
		return reEnter;
	}

	public void setReEnter(boolean reEnter) {
		this.reEnter = reEnter;
	}

}
