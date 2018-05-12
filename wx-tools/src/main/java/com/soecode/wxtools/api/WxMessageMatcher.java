package com.soecode.wxtools.api;

import com.soecode.wxtools.bean.WxXmlMessage;

public interface WxMessageMatcher {

	boolean match(WxXmlMessage message);

}
