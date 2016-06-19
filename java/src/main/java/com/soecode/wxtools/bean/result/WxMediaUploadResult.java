package com.soecode.wxtools.bean.result;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 多媒体上传结果
 * @author antgan
 *
 */
public class WxMediaUploadResult {

	private String type;
	private String media_id;
	private long created_at;
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public static WxMediaUploadResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxMediaUploadResult.class);
	}

	@Override
	public String toString() {
		return "WxMediaUploadResult [type=" + type + ", media_id=" + media_id + ", created_at=" + created_at + ", url="
				+ url + "]";
	}

}
