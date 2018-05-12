package com.soecode.wxtools.api;

import com.soecode.wxtools.exception.WxErrorException;

public interface WxErrorExceptionHandler {

	void handle(WxErrorException e);

}
