package com.soecode.wxtools.interceptor;

import java.util.Map;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageInterceptor;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.exception.WxErrorException;

public class DemoInterceptor implements WxMessageInterceptor{

	@Override
	public boolean intercept(WxXmlMessage wxMessage, Map<String, Object> context, IService iService)
			throws WxErrorException {
		if(WxConsts.XML_MSG_TEXT.equals(wxMessage.getMsgType())){
			return true;
		}
		return false;
	}
}
