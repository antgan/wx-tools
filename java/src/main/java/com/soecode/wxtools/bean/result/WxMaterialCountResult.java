package com.soecode.wxtools.bean.result;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 永久素材数量统计结果
 * @author antgan
 *
 */
public class WxMaterialCountResult {
	private int voice_count;
	private int video_count;
	private int image_count;
	private int news_count;
	public int getVoice_count() {
		return voice_count;
	}
	public void setVoice_count(int voice_count) {
		this.voice_count = voice_count;
	}
	public int getVideo_count() {
		return video_count;
	}
	public void setVideo_count(int video_count) {
		this.video_count = video_count;
	}
	public int getImage_count() {
		return image_count;
	}
	public void setImage_count(int image_count) {
		this.image_count = image_count;
	}
	public int getNews_count() {
		return news_count;
	}
	public void setNews_count(int news_count) {
		this.news_count = news_count;
	}
	@Override
	public String toString() {
		return "WxMaterialCountResult [voice_count=" + voice_count + ", video_count=" + video_count + ", image_count="
				+ image_count + ", news_count=" + news_count + "]";
	}

	/**
	 * json --> obj
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static WxMaterialCountResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxMaterialCountResult.class);
	}
	
	
	
}
