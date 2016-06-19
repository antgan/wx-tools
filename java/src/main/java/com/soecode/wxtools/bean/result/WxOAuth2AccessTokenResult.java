package com.soecode.wxtools.bean.result;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.WxAccessToken;

/**
 * OAuth2.0 认证 access_token
 * @author antgan
 *
 */
public class WxOAuth2AccessTokenResult extends WxAccessToken{
	private String refresh_token;
	private String openid;
	private String scope;
	private String unionid;
	
	
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public static WxOAuth2AccessTokenResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxOAuth2AccessTokenResult.class);
	}
	@Override
	public String toString() {
		return "WxOAuth2AccessTokenResult [refresh_token=" + refresh_token + ", openid=" + openid + ", scope=" + scope
				+ ", unionid=" + unionid + "]";
	}
	
	
}
