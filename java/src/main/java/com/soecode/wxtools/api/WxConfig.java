package com.soecode.wxtools.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.soecode.wxtools.bean.WxAccessToken;
import com.soecode.wxtools.util.StringUtils;

import ch.qos.logback.classic.Logger;

/**
 * 微信全局配置对象-从配置文件读取
 * @author antgan
 * @datetime 2016/12/14
 *
 */
public class WxConfig {
	private static Logger logger = (Logger) LoggerFactory.getLogger(WxConfig.class);
	private static final String configFile = "/config.properties";
	private static WxConfig config = null;
	
	//配置文件读取项
	private volatile String appId;
	private volatile String appSecret;
	private volatile String token;
	private volatile String aesKey;
	private volatile String oauth2redirectUri;
	
	//内存更新
	private volatile String accessToken;
	private volatile long expiresTime;
	private volatile String jsapiTicket;
	private volatile long jsapiTicketExpiresTime;
	
	public WxConfig() {
		//写读配置文件代码
		Properties p = new Properties();
		InputStream inStream = this.getClass().getResourceAsStream(configFile);
		if(inStream == null){
			logger.error("根目录下找不到wxconfig.properties文件");
			return;
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
            this.oauth2redirectUri = p.getProperty("wx.oauth2redirectUri");
            if(StringUtils.isNotBlank(this.oauth2redirectUri)) this.oauth2redirectUri = this.oauth2redirectUri.trim();
			inStream.close();
		} catch (IOException e) {
			logger.error("load wxconfig.properties error,class根目录下找不到wxconfig.properties文件");
			e.printStackTrace();
		}
		logger.info("load wxconfig.properties success");
	}
	
	/**
	 * 同步获取/加载单例
	 * @return
	 */
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

	public String getOauth2redirectUri() {
		return oauth2redirectUri;
	}
	
	@Override
	public String toString() {
		return "WxConfig [accessToken=" + accessToken + ", expiresTime=" + expiresTime + ", jsapiTicket=" + jsapiTicket
				+ ", jsapiTicketExpiresTime=" + jsapiTicketExpiresTime + "]";
	}
}
