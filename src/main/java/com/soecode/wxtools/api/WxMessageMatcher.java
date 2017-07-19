package com.soecode.wxtools.api;

import com.soecode.wxtools.bean.WxXmlMessage;

/**
 * <pre>
 * 消息匹配器
 * 使用场景： 需要更复杂的匹配规则时可以使用matcher
 * </pre>
 * 
 * @author antgan
 */
public interface WxMessageMatcher {

	/**
	 * 消息是否匹配某种模式
	 * 
	 * @param message
	 * @return
	 */
	public boolean match(WxXmlMessage message);

}
