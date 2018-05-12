package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class WxUserTagResult {
	private WxUserTag tag;
	private List<WxUserTag> tags;

	public WxUserTag getTag() {
		return tag;
	}

	public void setTag(WxUserTag tag) {
		this.tag = tag;
	}

	public List<WxUserTag> getTags() {
		return tags;
	}

	public void setTags(List<WxUserTag> tags) {
		this.tags = tags;
	}

	public static WxUserTagResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, WxUserTagResult.class);
	}

	@Override
	public String toString() {
		return "WxUserTagResult{" +
				"tag=" + tag +
				", tags=" + tags +
				'}';
	}

	public static class WxUserTag {
		private int id;
		private String name;
		private int count;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		@Override
		public String toString() {
			return "WxUserTag [id=" + id + ", name=" + name + ", count=" + count + "]";
		}
		
		
	}
	
}
