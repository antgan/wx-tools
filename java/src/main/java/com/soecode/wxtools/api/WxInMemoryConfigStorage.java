package com.soecode.wxtools.api;

import java.io.File;

import com.soecode.wxtools.bean.WxAccessToken;

/**
 * <pre>
 * 基于内存的微信配置库，在实际生产环境中应该将这些配置持久化。
 * 可以自己实现一个配置储存器，实现WxConfigStorage接口即可。
 * 
 * 【该配置库类在整个程序中是单例的，初始化后getInstance()即可获取实例】
 * </pre>
 * 
 * @author antgan
 *
 */
public class WxInMemoryConfigStorage implements WxConfigStorage {
	/**
	 * 单例模式
	 */
	private static WxConfigStorage config = null;
	private volatile String appId;
	private volatile String appSecret;
	private volatile String token;
	private volatile String accessToken;
	private volatile String aesKey;
	private volatile long expiresTime;
	private volatile String oauth2redirectUri;
	private volatile String jsapiTicket;
	private volatile long jsapiTicketExpiresTime;
	/**
	 * 临时多媒体素材存放路径
	 */
	private volatile File tmpDirFile;
	
	/**
	 * 永久多媒体素材存放路径
	 */
	private volatile File materialDirFile;
	
	/**
	 * 同步获取/加载单例
	 * @return
	 */
	public static synchronized WxConfigStorage getInstance(){
		if(config == null){
			config = new WxInMemoryConfigStorage();
		}
		return config;
	}
	
	public String getAccessToken() {
		return this.accessToken;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return this.token;
	}

	public long getExpiresTime() {
		return this.expiresTime;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setExpiresTime(long expiresTime) {
		this.expiresTime = expiresTime;
	}

	public String getOauth2redirectUri() {
		return this.oauth2redirectUri;
	}

	public void setOauth2redirectUri(String oauth2redirectUri) {
		this.oauth2redirectUri = oauth2redirectUri;
	}

	public File getTmpDirFile() {
		return tmpDirFile;
	}

	public void setTmpDirFile(File tmpDirFile) {
		this.tmpDirFile = tmpDirFile;
	}

	public File getMaterialDirFile() {
		return materialDirFile;
	}

	public void setMaterialDirFile(File materialDirFile) {
		this.materialDirFile = materialDirFile;
	}

	@Override
	public String toString() {
		return "WxInMemoryConfigStorage [appId=" + appId + ", appSecret=" + appSecret + ", token=" + token
				+ ", accessToken=" + accessToken + ", aesKey=" + aesKey + ", expiresTime=" + expiresTime
				+ ", oauth2redirectUri=" + oauth2redirectUri + ", jsapiTicket=" + jsapiTicket
				+ ", jsapiTicketExpiresTime=" + jsapiTicketExpiresTime + ", tmpDirFile=" + tmpDirFile + "]";
	}

	

	
}
