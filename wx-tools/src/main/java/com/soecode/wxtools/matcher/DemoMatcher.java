package com.soecode.wxtools.matcher;

import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;

public class DemoMatcher implements WxMessageMatcher{

	@Override
	public boolean match(WxXmlMessage message) {
		if("Here".equals(message.getContent())){
			return false;
		}
		return true;
	}
}
