package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 批量获取素材资源结果
 * @author antgan
 *
 */
public class WxBatchGetMaterialResult {
	private int total_count;
	private int item_count;
	private List<MaterialItem> item = new ArrayList<>();

	public List<MaterialItem> getItem() {
		return item;
	}

	public void setItem(List<MaterialItem> item) {
		this.item = item;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getItem_count() {
		return item_count;
	}
	
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	/**
	 * json --> obj
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static WxBatchGetMaterialResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxBatchGetMaterialResult.class);
	}

	@Override
	public String toString() {
		return "WxBatchGetMaterialResult [total_count=" + total_count + ", item_count=" + item_count + ", item=" + item
				+ "]";
	}




	/**
	 * 资源素材Item
	 * @author antgan
	 *
	 */
	public static class MaterialItem{
		private String media_id;
		private WxNewsMediaResult content;
		private String update_time;
		private String name;
		private String url;
		
		public String getMedia_id() {
			return media_id;
		}
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		public WxNewsMediaResult getContent() {
			return content;
		}
		public void setContent(WxNewsMediaResult content) {
			this.content = content;
		}
		public String getUpdate_time() {
			return update_time;
		}
		public void setUpdate_time(String update_time) {
			this.update_time = update_time;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		@Override
		public String toString() {
			return "MaterialItem [media_id=" + media_id + ", content=" + content + ", update_time=" + update_time
					+ ", name=" + name + ", url=" + url + "]";
		}
		
	
		
	}
	
}
