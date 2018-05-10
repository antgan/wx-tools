package com.soecode.wxtools.bean.result;

import java.io.IOException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class TemplateSenderResult extends WxError{

	private long msgid;

	public long getMsgid() {
		return msgid;
	}

	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}

	@Override
	public String toString() {
		return "TemplateSenderResult [msgid=" + msgid + "]";
	}
	
	public static TemplateSenderResult fromJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, TemplateSenderResult.class);
	}
	
}
