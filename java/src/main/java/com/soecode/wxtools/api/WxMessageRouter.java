package com.soecode.wxtools.api;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;

/**
 * <pre>
 * WxMessageRouter router = new WxMessageRouter(wxService);
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *   ...
 *   .end()
 * ;
 *
 * router.route(message);
 *
 * </pre>
 * 
 * @author antgan
 *
 */
public class WxMessageRouter {

	private final List<WxMessageRouterRule> rules = new ArrayList<WxMessageRouterRule>();
	private final IService iService;
	private WxErrorExceptionHandler exceptionHandler;
	public WxMessageRouter(IService iService) {
		this.iService = iService;
	}

	public void setExceptionHandler(WxErrorExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	List<WxMessageRouterRule> getRules() {
		return this.rules;
	}

	public WxMessageRouterRule rule() {
		return new WxMessageRouterRule(this);
	}

	public WxXmlOutMessage route(final WxXmlMessage wxMessage) {
		
		final List<WxMessageRouterRule> matchRules = new ArrayList<WxMessageRouterRule>();
		for (final WxMessageRouterRule rule : rules) {
			if (rule.test(wxMessage)) {
				matchRules.add(rule);
				if (!rule.isReEnter()) {
					break;
				}
			}
		}
		
		if (matchRules.size() == 0) {
			return null;
		}
		
		WxXmlOutMessage res = null;
		for (final WxMessageRouterRule rule : matchRules) {
			res = rule.service(wxMessage, iService, exceptionHandler);
		}
		return res;
	}
}
