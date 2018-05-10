package com.soecode.wxtools.api;

import java.util.Map;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.exception.WxErrorException;

public interface WxMessageInterceptor {

	boolean intercept(WxXmlMessage wxMessage, Map<String, Object> context, IService iService)
			throws WxErrorException;

}
