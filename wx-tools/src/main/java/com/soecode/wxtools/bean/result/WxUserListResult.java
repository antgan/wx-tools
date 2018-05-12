package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.Arrays;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class WxUserListResult {
	private int total;
	private int count;
	private WxOpenId data;
	private String next_openid;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public WxOpenId getData() {
		return data;
	}

	public void setData(WxOpenId data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
	@Override
	public String toString() {
		return "WxUserListResult [total=" + total + ", count=" + count + ", data=" + data + ", next_openid="
				+ next_openid + "]";
	}

	public static WxUserListResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, WxUserListResult.class);
	}

	public static class WxOpenId {
		private String[] openid;

		public String[] getOpenid() {
			return openid;
		}

		public void setOpenid(String[] openid) {
			this.openid = openid;
		}

		@Override
		public String toString() {
			return "WxOpenId [openid=" + Arrays.toString(openid) + "]";
		}
		
	}
}
