package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.WxNewsInfo;

/**
 * 获取【永久】素材--图文消息结果
 * 
 * @author antgan
 *
 */
public class WxNewsMediaResult {
	private List<WxNewsInfo> news_item = new ArrayList<>();
	private String update_time;
	private String create_time;
	
	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public List<WxNewsInfo> getNews_item() {
		return news_item;
	}

	public void setNews_item(List<WxNewsInfo> news_item) {
		this.news_item = news_item;
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
	public static WxNewsMediaResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxNewsMediaResult.class);
	}

	@Override
	public String toString() {
		return "WxNewsMediaResult [news_item=" + news_item + ", update_time=" + update_time + ", create_time="
				+ create_time + "]";
	}
	
	

}
