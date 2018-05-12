package com.soecode.wxtools.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.soecode.wxtools.bean.WxAccessToken;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.StringUtils;

public class WxConfig {
	private static final String configFile = "/wx.properties";
	private static WxConfig config = null;

	private volatile String appId;
	private volatile String appSecret;
	private volatile String token;
	private volatile String aesKey;
	private volatile String mchId;
	private volatile String apiKey;

	private volatile String accessToken;
	private volatile long expiresTime;
	private volatile String jsapiTicket;
	private volatile long jsapiTicketExpiresTime;
	
	public WxConfig() {
		Properties p = new Properties();
		InputStream inStream = this.getClass().getResourceAsStream(configFile);
		if(inStream == null){
			try {
				throw new WxErrorException("Can't find file");
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		try {
			p.load(inStream);
			this.appId = p.getProperty("wx.appId");
            if(StringUtils.isNotBlank(this.appId)) this.appId = this.appId.trim();
            this.appSecret = p.getProperty("wx.appSecret");
            if(StringUtils.isNotBlank(this.appSecret)) this.appSecret = this.appSecret.trim();
            this.token = p.getProperty("wx.token");
            if(StringUtils.isNotBlank(this.token)) this.token = this.token.trim();
            this.aesKey = p.getProperty("wx.aesKey");
            if(StringUtils.isNotBlank(this.aesKey)) this.aesKey = this.aesKey.trim();
            this.mchId = p.getProperty("wx.mchId");
            if(StringUtils.isNotBlank(this.mchId)) this.mchId = this.mchId.trim();
            this.apiKey = p.getProperty("wx.apiKey");
            if(StringUtils.isNotBlank(this.apiKey)) this.apiKey = this.apiKey.trim();
			inStream.close();
		} catch (IOException e) {
			try {
				throw new WxErrorException("load wx.properties error,class, can't find wx.properties");
			} catch (WxErrorException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("load wx.properties success");
	}

	public static synchronized WxConfig getInstance(){
		if(config == null){
			config = new WxConfig();
		}
		return config;
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
		this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
	}
	
	public void expireJsapiTicket() {
		this.jsapiTicketExpiresTime = 0;
	}

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
