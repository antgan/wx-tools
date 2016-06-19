package com.soecode.wxtools.bean.result;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 二维码返回
 * @author antgan
 *
 */
public class QrCodeResult {
	private String ticket;
	private int expire_seconds;
	private String url;
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * json --> obj
	 * 
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static QrCodeResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, QrCodeResult.class);
	}
	
	@Override
	public String toString() {
		return "QrCodeResult [ticket=" + ticket + ", expire_seconds=" + expire_seconds + ", url=" + url + "]";
	}
	
	
}
