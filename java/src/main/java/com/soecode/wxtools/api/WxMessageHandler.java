package com.soecode.wxtools.api;

import java.util.Map;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * <pre>
 * 微信推送消息的handler接口
 *</pre>
 * @author antgan
 */
public interface WxMessageHandler {

	/**
	 * 处理方法
	 * @param wxMessage  消息
	 * @param context
	 *            上下文，如果handler或interceptor之间有信息要传递，可以用这个
	 * @param wxService
	 * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
	 */
	public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, WxService wxService) throws WxErrorException;

}
