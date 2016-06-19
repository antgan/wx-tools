package com.soecode.wxtools.api;

import java.io.File;

import com.soecode.wxtools.bean.WxAccessToken;

/**
 * <pre>
 * 微信客户端配置存储库接口
 * </pre>
 * 
 * @author antgan
 *
 */
public interface WxConfigStorage {
	
	/**
	 * <pre>
	 * 获取AccessToken
	 * 
	 * </pre>
	 * @return 
	 */
	public String getAccessToken();

	/**
	 * <pre>
	 * AccessToken是否过期
	 * 
	 * </pre>
	 * @return
	 */
	public boolean isAccessTokenExpired();

	/**
	 * <pre>
	 * 强制将access token过期掉
	 * 
	 * </pre>
	 */
	public void expireAccessToken();

	/**
	 * 更新AccessToken
	 * @param accessToken
	 */
	public void updateAccessToken(WxAccessToken accessToken);
	/**
	 * 更新AccessToken
	 * @param accessToken
	 */
	public void updateAccessToken(String accessToken, int expiresIn);

	/**
	 * 获取JSAPITicket
	 * @return
	 */
	public String getJsapiTicket();

	/**
	 * 判断JSAPITicket是否过期
	 * @return
	 */
	public boolean isJsapiTicketExpired();

	/**
	 * 强制将jsapi ticket过期掉
	 */
	public void expireJsapiTicket();

	/**
	 * 更新JSAPITicket应该是线程安全的 
	 * 
	 * @param jsapiTicket
	 */
	public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

	public String getAppId() ;

	public void setAppId(String appId);

	public String getAppSecret() ;

	public void setAppSecret(String appSecret) ;

	public String getToken() ;

	public long getExpiresTime();

	public void setToken(String token);

	public String getAesKey() ;

	public void setAesKey(String aesKey) ;

	public void setAccessToken(String accessToken) ;

	public void setExpiresTime(long expiresTime) ;

	public String getOauth2redirectUri() ;

	public void setOauth2redirectUri(String oauth2redirectUri);

	public File getTmpDirFile() ;

	public void setTmpDirFile(File tmpDirFile);

	public File getMaterialDirFile() ;

	public void setMaterialDirFile(File materialDirFile);

}
