package com.soecode.wxtools.exception;

import com.soecode.wxtools.bean.result.WxError;

public class WxErrorException extends Exception {

	private static final long serialVersionUID = -6357149550353160810L;

	private WxError error;

	public WxErrorException(WxError error) {
		super(error.toString());
		this.error = error;
	}
	
	public WxErrorException(String msg){
		super(msg);
	}

	public WxError getError() {
		return error;
	}

}
