package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 微信用户分组返回
 * @author antgan
 *
 */
public class WxUserGroupResult {
	private WxUserGroup group;
	private List<WxUserGroup> groups;
	
	public WxUserGroup getGroup() {
		return group;
	}

	public void setGroup(WxUserGroup group) {
		this.group = group;
	}

	public List<WxUserGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<WxUserGroup> groups) {
		this.groups = groups;
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
	public static WxUserGroupResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxUserGroupResult.class);
	}

	@Override
	public String toString() {
		return "WxUserGroupResult [group=" + group + ", groups=" + groups + "]";
	}



	/**
	 * 分组信息
	 * @author antgan
	 *
	 */
	public static class WxUserGroup{
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
			return "WxUserGroup [id=" + id + ", name=" + name + ", count=" + count + "]";
		}
		
		
	}
	
}
