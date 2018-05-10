package com.soecode.wxtools.bean.result;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class SenderResult extends WxError{

	private long msg_id;
	private long msg_data_id;
	private String msg_status;
	public long getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(long msg_id) {
		this.msg_id = msg_id;
	}
	public long getMsg_data_id() {
		return msg_data_id;
	}
	public void setMsg_data_id(long msg_data_id) {
		this.msg_data_id = msg_data_id;
	}
	public String getMsg_status() {
		return msg_status;
	}
	public void setMsg_status(String msg_status) {
		this.msg_status = msg_status;
	}
	
	public static SenderResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, SenderResult.class);
	}
	@Override
	public String toString() {
		return "SenderResult [msg_id=" + msg_id + ", msg_data_id=" + msg_data_id + ", msg_status=" + msg_status 
				+ " errcode= "+ getErrcode()+
				 " errmsg= " + getErrmsg()+"]";
	}
	
	
}
