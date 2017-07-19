package com.soecode.wxtools.bean.result;

import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 微信错误码说明 http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html
 * 
 * @author antgan
 *
 */
public class WxError{

	private int errcode;

	private String errmsg;

	public WxError() {
		// TODO Auto-generated constructor stub
	}
	
	public WxError(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	
	public int getErrcode() {
		return errcode;
	}


	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}


	public String getErrmsg() {
		return errmsg;
	}


	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


	public static WxError fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, WxError.class);
	}


	@Override
	public String toString() {
		return "WxError [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}

	

}
