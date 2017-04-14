package com.soecode.wxtools.bean.result;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 发送模板消息结果
 * @author antgan
 *
 */
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
	
	public static TemplateSenderResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, TemplateSenderResult.class);
	}
	
}
