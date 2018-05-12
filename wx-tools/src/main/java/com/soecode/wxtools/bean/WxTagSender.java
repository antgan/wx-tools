package com.soecode.wxtools.bean;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

public class WxTagSender extends SenderContent{
	private SenderFilter filter;
	private String msgtype;
	public SenderFilter getFilter() {
		return filter;
	}
	public void setFilter(SenderFilter filter) {
		this.filter = filter;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	public String toJson() throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

}




