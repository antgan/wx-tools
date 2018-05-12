package com.soecode.wxtools.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class WxUserList{
	private List<WxUser> user_info_list = new ArrayList<>();

	public List<WxUser> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<WxUser> user_info_list) {
		this.user_info_list = user_info_list;
	}
	
	@Override
	public String toString() {
		return "WxUserList [user_info_list=" + user_info_list + "]";
	}

	public static WxUserList fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxUserList.class);
	}
	
	public static class WxUser {
		private int subscribe;
		private String openid;
		private String nickname;
		private int sex;
		private String language;
		private String city;
		private String province;
		private String country;
		private String headimgurl;
		private String subscribe_time;
		private String unionid;
		private String remark;
		private int groupid; 
		private String[] tagid_list;
		private String[] privilege;
		private String subscribe_scene;
		private int qr_scene;
		private String qr_scene_str;
		
		public String[] getPrivilege() {
			return privilege;
		}

		public void setPrivilege(String[] privilege) {
			this.privilege = privilege;
		}

		public String[] getTagid_list() {
			return tagid_list;
		}

		public void setTagid_list(String[] tagid_list) {
			this.tagid_list = tagid_list;
		}

		public int getSubscribe() {
			return subscribe;
		}

		public void setSubscribe(int subscribe) {
			this.subscribe = subscribe;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getHeadimgurl() {
			return headimgurl;
		}

		public void setHeadimgurl(String headimgurl) {
			this.headimgurl = headimgurl;
		}

		public String getSubscribe_time() {
			return subscribe_time;
		}

		public void setSubscribe_time(String subscribe_time) {
			this.subscribe_time = subscribe_time;
		}

		public String getUnionid() {
			return unionid;
		}

		public void setUnionid(String unionid) {
			this.unionid = unionid;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public int getGroupid() {
			return groupid;
		}

		public void setGroupid(int groupid) {
			this.groupid = groupid;
		}

		public String getSubscribe_scene() {
			return subscribe_scene;
		}

		public void setSubscribe_scene(String subscribe_scene) {
			this.subscribe_scene = subscribe_scene;
		}

		public int getQr_scene() {
			return qr_scene;
		}

		public void setQr_scene(int qr_scene) {
			this.qr_scene = qr_scene;
		}

		public String getQr_scene_str() {
			return qr_scene_str;
		}

		public void setQr_scene_str(String qr_scene_str) {
			this.qr_scene_str = qr_scene_str;
		}

		public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(this);
		}

		public static WxUser fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, WxUser.class);
		}

		@Override
		public String toString() {
			return "WxUser{" +
					"subscribe=" + subscribe +
					", openid='" + openid + '\'' +
					", nickname='" + nickname + '\'' +
					", sex=" + sex +
					", language='" + language + '\'' +
					", city='" + city + '\'' +
					", province='" + province + '\'' +
					", country='" + country + '\'' +
					", headimgurl='" + headimgurl + '\'' +
					", subscribe_time='" + subscribe_time + '\'' +
					", unionid='" + unionid + '\'' +
					", remark='" + remark + '\'' +
					", groupid=" + groupid +
					", tagid_list=" + Arrays.toString(tagid_list) +
					", privilege=" + Arrays.toString(privilege) +
					", subscribe_scene='" + subscribe_scene + '\'' +
					", qr_scene=" + qr_scene +
					", qr_scene_str='" + qr_scene_str + '\'' +
					'}';
		}

		public static class WxUserGet{
			private String openid;
			private String lang;
			
			public WxUserGet() {

			}
			
			public WxUserGet(String openid, String lang) {
				super();
				this.openid = openid;
				this.lang = lang;
			}

			public String getOpenid() {
				return openid;
			}
			public void setOpenid(String openid) {
				this.openid = openid;
			}
			public String getLang() {
				return lang;
			}
			public void setLang(String lang) {
				this.lang = lang;
			}
			public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(this);
			}
		}
	}

}

