package com.soecode.wxtools.api;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.bean.WxAccessToken;

/**
 * 微信全局配置对象-从配置文件读取
 * @author antgan
 * @datetime 2016/12/14
 *
 */
public class WxConfig {
	//private static final String configFile = "/wx.properties";
	//private static WxConfig config = null;
	
	public final Object accessTokenRefreshLock =new Object();
	public final Object jsapiTicketRefreshLock =new Object();
	
	private static List<WxConfig> pools=new ArrayList<WxConfig>();
	
	//配置文件读取项
	private String appId;
	private String appSecret;
	private String token;
	private String aesKey;
	private String mchId;
	private String apiKey;
	
	//内存更新
	private String accessToken;
	private long expiresTime;
	private String jsapiTicket;
	private long jsapiTicketExpiresTime;
	
	private WxConfig(String appId,String appSecret,String token,String aesKey,String mchId,String apiKey) {
			this.appId = appId;
            this.appSecret = appSecret;
            this.token = token;
            this.aesKey = aesKey;
            this.mchId = mchId;
            this.apiKey = apiKey;
	}
	
	/**
	 * 同步获取/加载单例
	 * @return
	 */
	public static synchronized WxConfig getInstance(String appId,String appSecret,String token,String aesKey,String mchId,String apiKey){
		for(WxConfig c: pools){
			if(c.appId.equals(appId)){
				return c;
			}
		}
		WxConfig c=new WxConfig(appId,appSecret,token,aesKey,mchId,apiKey);
		pools.add(c);
		return c;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public boolean isAccessTokenExpired() {
		return System.currentTimeMillis() > this.expiresTime;
	}

	public void expireAccessToken() {
		this.expiresTime = 0;
	}

	public synchronized void updateAccessToken(WxAccessToken accessToken) {
		updateAccessToken(accessToken.getAccess_token(), accessToken.getExpires_in());
	}

	public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
		this.accessToken = accessToken;
		this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public long getJsapiTicketExpiresTime() {
		return jsapiTicketExpiresTime;
	}

	public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
		this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
	}

	public boolean isJsapiTicketExpired() {
		return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
	}

	public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
		this.jsapiTicket = jsapiTicket;
		// 预留200秒的时间
		this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
	}
	
	public void expireJsapiTicket() {
		this.jsapiTicketExpiresTime = 0;
	}

	
	//getter


	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getToken() {
		return token;
	}
	
	public String getAesKey() {
		return aesKey;
	}
	
	public String getMchId() {
		return mchId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return "WxConfig [appId=" + appId + ", appSecret=" + appSecret + ", token=" + token + ", aesKey=" + aesKey
				+ ", mchId=" + mchId + ", apiKey=" + apiKey + ", accessToken=" + accessToken + ", expiresTime="
				+ expiresTime + ", jsapiTicket=" + jsapiTicket + ", jsapiTicketExpiresTime=" + jsapiTicketExpiresTime
				+ "]";
	}
	
}
