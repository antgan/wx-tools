package com.soecode.wxtools.handler;

import com.soecode.wxtools.exception.WxErrorException;

public interface WxErrorExceptionHandler {

	void handle(WxErrorException e);

}
