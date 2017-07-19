package com.soecode.wxtools.bean;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * <pre>
 * 二维码生成
 * 详情：http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html
 * </pre>
 * @author antgan
 *
 */
public class WxQrcode {
	private int expire_seconds;
	private String action_name;
	private WxQrActionInfo action_info;
	
	public int getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public WxQrActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(WxQrActionInfo action_info) {
		this.action_info = action_info;
	}

	public String toJson() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
	@Override
	public String toString() {
		return "WxQrcode [expire_seconds=" + expire_seconds + ", action_name=" + action_name + ", action_info="
				+ action_info + "]";
	}

	/**
	 * 二维码动作
	 * @author antgan
	 *
	 */
	public static class WxQrActionInfo{
		private WxScene scene;

		
		public WxQrActionInfo(WxScene scene) {
			this.scene = scene;
		}

		public WxScene getScene() {
			return scene;
		}

		public void setScene(WxScene scene) {
			this.scene = scene;
		}

		@Override
		public String toString() {
			return "WxQrActionInfo [scene=" + scene + "]";
		}
		
		/**
		 * 二维码场景
		 * @author antgan
		 *
		 */
		public static class WxScene{
			private int scene_id;
			private String scene_str;
			
			public WxScene(int scene_id) {
				this.scene_id = scene_id;
			}
			
			public WxScene(String scene_str) {
				this.scene_str = scene_str;
			}
			
			public int getScene_id() {
				return scene_id;
			}
			public void setScene_id(int scene_id) {
				this.scene_id = scene_id;
			}
			public String getScene_str() {
				return scene_str;
			}
			public void setScene_str(String scene_str) {
				this.scene_str = scene_str;
			}
			@Override
			public String toString() {
				return "WxScene [scene_id=" + scene_id + ", scene_str=" + scene_str + "]";
			}
		}
	}
	
	
	
}
