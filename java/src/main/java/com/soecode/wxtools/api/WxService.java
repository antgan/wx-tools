package com.soecode.wxtools.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.InvokePay;
import com.soecode.wxtools.bean.PayOrderInfo;
import com.soecode.wxtools.bean.PreviewSender;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.WxAccessToken;
import com.soecode.wxtools.bean.WxGroupSender;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.WxQrcode;
import com.soecode.wxtools.bean.WxUnifiedOrder;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.WxVideoIntroduction;
import com.soecode.wxtools.bean.result.IndustryResult;
import com.soecode.wxtools.bean.result.QrCodeResult;
import com.soecode.wxtools.bean.result.SenderResult;
import com.soecode.wxtools.bean.result.TemplateListResult;
import com.soecode.wxtools.bean.result.TemplateResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.bean.result.UnifiedOrderResult;
import com.soecode.wxtools.bean.result.WxBatchGetMaterialResult;
import com.soecode.wxtools.bean.result.WxCurMenuInfoResult;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxMaterialCountResult;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.bean.result.WxMenuResult;
import com.soecode.wxtools.bean.result.WxNewsMediaResult;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.bean.result.WxUserGroupResult;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.bean.result.WxVideoMediaResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.DateUtil;
import com.soecode.wxtools.util.PayUtil;
import com.soecode.wxtools.util.RandomUtils;
import com.soecode.wxtools.util.crypto.SHA1;
import com.soecode.wxtools.util.file.FileUtils;
import com.soecode.wxtools.util.http.MediaDownloadGetRequestExecutor;
import com.soecode.wxtools.util.http.MediaDownloadPostRequestExecutor;
import com.soecode.wxtools.util.http.MediaUploadRequestExecutor;
import com.soecode.wxtools.util.http.QrCodeDownloadGetRequestExecutor;
import com.soecode.wxtools.util.http.RequestExecutor;
import com.soecode.wxtools.util.http.SimpleGetRequestExecutor;
import com.soecode.wxtools.util.http.SimplePostRequestExecutor;
import com.soecode.wxtools.util.http.URIUtil;
import com.soecode.wxtools.util.http.VideoDownloadPostRequestExecutor;

/**
 * 统一业务处理类
 * @author antgan
 * @date 2016/12/14
 * 
 */
public class WxService implements IService{
	
	WxConfig wxConfig;//多微信公众号实例配置
	
	//HttpClient
	protected CloseableHttpClient httpClient;
	
	/**
	 * 构造方法，初始化httpClient
	 */
	public WxService(WxConfig wxConfig) {
		this.wxConfig=wxConfig;
		httpClient = HttpClients.createDefault();
	}

	/*****************************
	 *                           *
	 *    以下为微信公众号API接口     *
	 *                           *
	 *****************************/
	
	@Override
	public boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {
		try {
			return SHA1.gen(wxConfig.getToken(), timestamp, nonce).equals(signature);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getAccessToken() throws WxErrorException {
		return getAccessToken(false);
	}

	@Override
	public String getAccessToken(boolean forceRefresh) throws WxErrorException {
		if (forceRefresh) {
			wxConfig.expireAccessToken();
		}
		if (wxConfig.isAccessTokenExpired()) {
			synchronized (wxConfig.accessTokenRefreshLock) {
				if (wxConfig.isAccessTokenExpired()) {
					String url = WxConsts.URL_GET_ACCESSTOEKN.replace("APPID", wxConfig.getAppId())
							.replace("APPSECRET", wxConfig.getAppSecret());
					try {
						String resultContent = get(url, null);
						WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
						wxConfig.updateAccessToken(accessToken.getAccess_token(), accessToken.getExpires_in());
						System.out.println("[wx-tools]update accessToken success. accessToken:"+accessToken.getAccess_token());
					} catch (IOException e) {
						throw new WxErrorException("[wx-tools]refresh accessToken failure.");
					}
				}
			}
		}
		return wxConfig.getAccessToken();
	}

	@Override
	public String[] getCallbackIp() throws WxErrorException {
		String[] ips = null;
		String url = WxConsts.URL_GET_WX_SERVICE_IP.replace("ACCESS_TOKEN", getAccessToken());
		String responseContent = get(url, null);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(responseContent);
			ips = mapper.readValue(node.get("ip_list"), String[].class);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getCallbackIp failure.");
		}
		return ips;
	}

	@Override
	public String createMenu(WxMenu menu, boolean condition) throws WxErrorException {
		String url = null, result = null;
		if (condition)
			url = WxConsts.URL_CREATE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", getAccessToken());
		else
			url = WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", getAccessToken());

		try {
			result = post(url, menu.toJson());
			System.out.println("[wx-tools]Create Menu result:" + result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createMenu failure.");
		}
		return result;
	}

	@Override
	public String deleteMenu() throws WxErrorException {
		String url = WxConsts.URL_DELETE_MENU.replace("ACCESS_TOKEN", getAccessToken());
		String result = get(url, null);
		System.out.println("[wx-tools]Delete Menu result:" + result);
		return result;
	}

	@Override
	public String deleteMenu(String menuid) throws WxErrorException {
		String url = WxConsts.URL_DELETE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", getAccessToken());

		String json = "{" + "\"menuid\":" + menuid + "}";
		String result = post(url, json);
		System.out.println("[wx-tools]Delete Conditional Menu result:" + result);
		return result;
	}

	@Override
	public WxMenuResult getMenu() throws WxErrorException {
		String url = WxConsts.URL_GET_MENU.replace("ACCESS_TOKEN", getAccessToken());
		WxMenuResult result = null;
		try {
			result = WxMenuResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMenu failure.");
		}
		return result;
	}

	@Override
	public WxCurMenuInfoResult getMenuCurInfo() throws WxErrorException {
		String url = WxConsts.URL_GET_CURRENT_MENU_INFO.replace("ACCESS_TOKEN", getAccessToken());
		WxCurMenuInfoResult result = null;
		try {
			result = WxCurMenuInfoResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMenuCurInfo failure.");
		}
		return result;
	}

	@Override
	public String menuTryMatch(String user_id) throws WxErrorException {
		String url = WxConsts.URL_TRYMATCH_MENU.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"user_id\":\"" + user_id + "\"" + "}";
		return post(url, json);
	}

	@Override
	public WxMediaUploadResult uploadTempMedia(String mediaType, String fileType, InputStream inputStream)
			throws WxErrorException, IOException {
		return uploadTempMedia(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
	}

	@Override
	public WxMediaUploadResult uploadTempMedia(String mediaType, File file) throws WxErrorException {
		String url = WxConsts.URL_UPLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE", mediaType);
		return execute(new MediaUploadRequestExecutor(), url, file);
	}

	public File downloadTempMedia(String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", getAccessToken()).replace("MEDIA_ID",
				media_id);
		return execute(new MediaDownloadGetRequestExecutor(path), url, null);
	}

	@Override
	public WxMediaUploadResult uploadMedia(String mediaType, String fileType, InputStream inputStream,
			WxVideoIntroduction introduction) throws WxErrorException, IOException {
		return uploadMedia(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType),
				introduction);
	}

	@Override
	public WxMediaUploadResult uploadMedia(String mediaType, File file, WxVideoIntroduction introduction)
			throws WxErrorException {
		WxMediaUploadResult result = null;
		String url = WxConsts.URL_UPLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE",
				mediaType);
		// 如果是视频素材，添加视频描述对象
		if (WxConsts.MEDIA_VIDEO.equals(mediaType)) {
			result = execute(new MediaUploadRequestExecutor(introduction), url, file);
		} else {
			result = execute(new MediaUploadRequestExecutor(), url, file);
		}
		return result;
	}

	@Override
	public File downloadMedia(String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		return execute(new MediaDownloadPostRequestExecutor(path), url, json);
	}

	@Override
	public WxNewsMediaResult downloadNewsMedia(String media_id) throws WxErrorException {
		WxNewsMediaResult newsResult = null;
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		String result = execute(new SimplePostRequestExecutor(), url, json);
		try {
			newsResult = WxNewsMediaResult.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]downloadNewsMedia failure.");
		}
		return newsResult;
	}

	@Override
	public WxVideoMediaResult downloadVideoMedia(String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		return execute(new VideoDownloadPostRequestExecutor(path), url, json);
	}

	@Override
	public WxError deleteMediaMaterial(String media_id) throws WxErrorException {
		String url = WxConsts.URL_DELETE_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		String result = execute(new SimplePostRequestExecutor(), url, json);
		WxError err = null;
		try {
			err = WxError.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]deleteMediaMaterial failure.");
		}
		return err;
	}

	@Override
	public String addNewsMedia(List<WxNewsInfo> news) throws WxErrorException {
		String media_id = null;
		String url = WxConsts.URL_ADD_NEWS_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String arrayJson = mapper.writeValueAsString(news);
			String json = "{\"articles\":" + arrayJson + "}";
			String result = execute(new SimplePostRequestExecutor(), url, json);
			JsonNode node = mapper.readTree(result);
			media_id = node.get("media_id").asText();
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]addNewsMedia failure.");
		}
		return media_id;
	}

	@Override
	public WxMediaUploadResult imageDomainChange(File file) throws WxErrorException {
		String url = WxConsts.URL_IMAGE_DOMAIN_CHANGE.replace("ACCESS_TOKEN", getAccessToken());
		return execute(new MediaUploadRequestExecutor(), url, file);
	}

	@Override
	public WxError updateNewsInfo(String media_id, int index, WxNewsInfo newInfo) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_NEWS_MEDIA.replace("ACCESS_TOKEN", getAccessToken());

		try {
			String json = "{" + "\"media_id\":" + "\"" + media_id + "\"," + "\"index\":" + index + "," + "\"articles\":"
					+ newInfo.toJson() + "}";
			String result = execute(new SimplePostRequestExecutor(), url, json);
			err = WxError.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateNewsInfo failure.");
		}
		return err;
	}

	@Override
	public WxMaterialCountResult getMaterialCount() throws WxErrorException {
		String url = WxConsts.URL_GET_MATERIAL_COUNT.replace("ACCESS_TOKEN", getAccessToken());
		WxMaterialCountResult result = null;
		try {
			result = WxMaterialCountResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMaterialCount failure.");
		}
		return result;
	}

	@Override
	public WxBatchGetMaterialResult batchGetMeterial(String type, int offset, int count) throws WxErrorException {
		String url = WxConsts.URL_BATCHGET_MATERIAL_MEDIA_LIST.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"type\":\"" + type + "\"," + "\"offset\":" + offset + "," + "\"count\":" + count + "}";
		String result = post(url, json);
		WxBatchGetMaterialResult materialResult = null;
		try {
			materialResult = WxBatchGetMaterialResult.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetMeterial failure.");
		}
		return materialResult;
	}

	@Override
	public WxUserGroupResult createUserGroup(String name) throws WxErrorException {
		WxUserGroupResult result = null;
		String url = WxConsts.URL_CREATE_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"group\":{\"name\":\"" + name + "\"}}";
		String postResult = post(url, json);
		try {
			result = WxUserGroupResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createUserGroup failure.");
		}
		return result;
	}

	@Override
	public WxUserGroupResult queryAllUserGroup() throws WxErrorException {
		WxUserGroupResult result = null;
		String url = WxConsts.URL_QUERY_ALL_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		String getResult = get(url, null);
		try {
			result = WxUserGroupResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]queryAllUserGroup failure.");
		}
		return result;
	}

	@Override
	public int queryGroupIdByOpenId(String openid) throws WxErrorException {
		int result = -1;
		String url = WxConsts.URL_QUERY_USER_GROUP_BY_OPENID.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"openid\":\"" + openid + "\"}";
		String postResult = post(url, json);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(postResult);
			result = Integer.parseInt(node.get("groupid").toString());
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]queryGroupIdByOpenId failure.");
		}
		return result;
	}

	@Override
	public WxError updateUserGroupName(int groupid, String name) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_USER_GROUP_NAME.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"group\":{\"id\":" + groupid + ",\"name\":\"" + name + "\"}}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateUserGroupName failure.");
		}
		return err;
	}

	@Override
	public WxError movingUserToNewGroup(String openid, int to_groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_MOVING_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"openid\":\"" + openid + "\",\"to_groupid\":" + to_groupid + "}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]movingUserToNewGroup failure.");
		}
		return err;
	}

	@Override
	public WxError batchMovingUserToNewGroup(List<String> openids, int to_groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_BATCH_MOVING_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		String arrayJson = null;
		try {
			arrayJson = mapper.writeValueAsString(openids);
			String json = "{\"openid_list\":" + arrayJson + ",\"to_groupid\":" + to_groupid + "}";
			String postResult = post(url, json);
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchMovingUserToNewGroup failure.");
		}
		return err;
	}

	@Override
	public WxError deleteUserGroup(int groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_DELETE_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String json = "{\"group\":{\"id\":" + groupid + "}}";
			String postResult = post(url, json);
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]deleteUserGroup failure.");
		}
		return err;
	}

	@Override
	public WxError updateUserRemark(String openid, String remark) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_USER_REMARK.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"openid\":\"" + openid + "\",\"remark\":\"" + remark + "\"}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateUserRemark failure.");
		}
		return err;
	}

	@Override
	public WxUser getUserInfoByOpenId(WxUserGet userGet) throws WxErrorException {
		WxUser user = null;
		String url = WxConsts.URL_GET_USER_INFO.replace("ACCESS_TOKEN", getAccessToken())
				.replace("OPENID", userGet.getOpenid()).replace("zh_CN", userGet.getLang());
		String postResult = post(url, null);
		try {
			user = WxUser.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getUserInfoByOpenId failure.");
		}
		return user;
	}

	@Override
	public WxUserList batchGetUserInfo(List<WxUserGet> usersGet) throws WxErrorException {
		WxUserList list = null;
		String url = WxConsts.URL_BATCH_GET_USER_INFO.replace("ACCESS_TOKEN", getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		String arrayJson = null;
		try {
			arrayJson = mapper.writeValueAsString(usersGet);
			String json = "{\"user_list\":" + arrayJson + "}";
			String postResult = post(url, json);
			list = WxUserList.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetUserInfo failure.");
		}
		return list;
	}

	@Override
	public WxUserListResult batchGetUserOpenId(String next_openid) throws WxErrorException {
		WxUserListResult result = null;
		String url = WxConsts.URL_BATCH_GET_USER_OPENID.replace("ACCESS_TOKEN", getAccessToken()).replace("NEXT_OPENID",
				next_openid);
		String getResult = get(url, null);
		try {
			result = WxUserListResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetUserOpenId failure.");
		}
		return result;
	}

	@Override
	public String oauth2buildAuthorizationUrl(String redirectUri, String scope, String state) {
		redirectUri = URIUtil.encodeURIComponent(redirectUri);
		String url = WxConsts.URL_OAUTH2_GET_CODE.replace("APPID", wxConfig.getAppId())
				.replace("REDIRECT_URI", redirectUri).replace("SCOPE", scope).replace("STATE", state);
		return url;
	}

	@Override
	public WxOAuth2AccessTokenResult oauth2ToGetAccessToken(String code) throws WxErrorException {
		WxOAuth2AccessTokenResult result = null;
		String url = WxConsts.URL_OAUTH2_GET_ACCESSTOKEN.replace("APPID", wxConfig.getAppId())
				.replace("SECRET", wxConfig.getAppSecret()).replace("CODE", code);
		String getResult = get(url, null);
		try {
			result = WxOAuth2AccessTokenResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetAccessToken failure.");
		}
		return result;
	}

	@Override
	public WxOAuth2AccessTokenResult oauth2ToGetRefreshAccessToken(String refresh_token) throws WxErrorException {
		WxOAuth2AccessTokenResult result = null;
		String url = WxConsts.URL_OAUTH2_GET_REFRESH_ACCESSTOKEN.replace("APPID", wxConfig.getAppId())
				.replace("REFRESH_TOKEN", refresh_token);
		String getResult = get(url, null);
		try {
			result = WxOAuth2AccessTokenResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetRefreshAccessToken failure.");
		}
		return result;
	}

	@Override
	public WxUser oauth2ToGetUserInfo(String access_token, WxUserGet userGet) throws WxErrorException {
		WxUser user = null;
		String url = WxConsts.URL_OAUTH2_GET_USER_INFO.replace("ACCESS_TOKEN", access_token)
				.replace("OPENID", userGet.getOpenid()).replace("zh_CN", userGet.getLang());
		String getResult = get(url, null);
		try {
			user = WxUser.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetUserInfo failure.");
		}
		return user;
	}

	@Override
	public WxError oauth2CheckAccessToken(String access_token, String openid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_OAUTH2_CHECK_ACCESSTOKEN.replace("ACCESS_TOKEN", access_token).replace("OPENID",
				openid);
		String getResult = get(url, null);
		try {
			err = WxError.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2CheckAccessToken failure.");
		}
		return err;
	}

	@Override
	public QrCodeResult createQrCode(WxQrcode qrcode) throws WxErrorException {
		QrCodeResult result = null;
		String url = WxConsts.URL_GET_QR_CODE.replace("TOKEN", getAccessToken());
		String json = null;
		try {
			json = qrcode.toJson();
			String postResult = post(url, json);
			result = QrCodeResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createQrCode failure.");
		}
		return result;
	}

	@Override
	public File downloadQrCode(File dir, String ticket) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_QR_CODE.replace("TICKET", URIUtil.encodeURIComponent(ticket));
		return execute(new QrCodeDownloadGetRequestExecutor(dir), url, null);
	}

	@Override
	public String getShortUrl(String long_url) throws WxErrorException {
		String url = WxConsts.URL_LONGURL_TO_SHORTURL.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"action\":\"long2short\",\"long_url\":\"" + long_url + "\"}";
		String postResult = post(url, json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getShortUrl failure.");
		}
		String shortUrl = node.get("short_url").asText();
		return shortUrl;
	}

	public String getJsapiTicket() throws WxErrorException {
		return getJsapiTicket(false);
	}

	public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
		if (forceRefresh) {
			wxConfig.expireJsapiTicket();
		}
		if (wxConfig.isJsapiTicketExpired()) {
			synchronized (wxConfig.jsapiTicketRefreshLock) {
				if (wxConfig.isJsapiTicketExpired()) {
					String url = WxConsts.URL_GET_JS_API_TICKET.replace("ACCESS_TOKEN", getAccessToken());
					String responseContent = execute(new SimpleGetRequestExecutor(), url, null);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = null;
					try {
						node = mapper.readTree(responseContent);
						if(node.get("errcode")!=null && !(node.get("errcode").asInt()==0)){
							WxError error = WxError.fromJson(responseContent);
							throw new WxErrorException(error);
						}
						String jsapiTicket = node.get("ticket").asText();
						int expiresInSeconds = node.get("expires_in").asInt();
						wxConfig.updateJsapiTicket(jsapiTicket, expiresInSeconds);
						System.out.println("[wx-tools]update jsapiTicket success. ticket: "+jsapiTicket);
					} catch (Exception e) {
						throw new WxErrorException("[wx-tools]getJsapiTicket failure.");
					}
				}
			}
		}
		return wxConfig.getJsapiTicket();
	}

	public WxJsapiConfig createJsapiConfig(String url, List<String> jsApiList) throws WxErrorException {
		long timestamp = System.currentTimeMillis() / 1000;
		String noncestr = RandomUtils.getRandomStr(16);
		String jsapiTicket = getJsapiTicket();
		try {
			String signature = SHA1.genWithAmple("noncestr="+noncestr,
					"jsapi_ticket="+jsapiTicket,"timestamp="+timestamp,"url="+url);
			WxJsapiConfig jsapiConfig = new WxJsapiConfig();
			jsapiConfig.setTimestamp(timestamp);
			jsapiConfig.setNoncestr(noncestr);
			jsapiConfig.setUrl(url);
			jsapiConfig.setSignature(signature);
			jsapiConfig.setJsApiList(jsApiList);
			return jsapiConfig;
		} catch (NoSuchAlgorithmException e) {
			throw new WxErrorException("[wx-tools]createJsapiConfig failure.");
		}
	}
	
	@Override
	public SenderResult sendAllByGroup(WxGroupSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_GROUP_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllByGroup failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllByOpenid(WxOpenidSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_OPENID_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllByOpenid failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllPreview(PreviewSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_PREVIEW_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllPreview failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllDelete(String msg_id) throws WxErrorException {
		SenderResult result = null;
		String json = "{\"msg_id\":"+msg_id+"}";
		String url = WxConsts.URL_DELETE_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, json);
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllDelete failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllGetStatus(String msg_id) throws WxErrorException {
		SenderResult result = null;
		String json = "{\"msg_id\":\""+msg_id+"\"}";
		String url = WxConsts.URL_GET_STATUS_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, json);
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllGetStatus failure.");
		}
		return result;
	}
	
	@Deprecated
	@Override
	public InvokePay unifiedOrder(PayOrderInfo order, String notifyUrl, String openid) throws WxErrorException{
		InvokePay ivp = new InvokePay();
		WxUnifiedOrder payinfo = PayUtil.createPayInfo(wxConfig,order, notifyUrl, openid);
		String postResult = null;
		try {
			postResult = post(WxConsts.URL_PAY_UNIFIEORDER, payinfo.toXml());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(postResult);
		
		UnifiedOrderResult result = UnifiedOrderResult.fromXml(postResult);
		
		//赋值
		ivp.setAppId(result.getAppid());
		ivp.setNonceStr(result.getNonceStr());
		ivp.setPaySign(result.getSign());
		ivp.setPrepayId(result.getPrepayId());
		ivp.setSignType("MD5");
		ivp.setTimeStamp(DateUtil.getTimestamp());
		
		//拼接map
		Map<String,String> map = new HashMap<>();
		map.put("appId", ivp.getAppId());
		map.put("timeStamp", ivp.getTimeStamp());
		map.put("nonceStr", ivp.getNonceStr());
		map.put("package", "prepay_id="+ivp.getPrepayId());
		map.put("signType", ivp.getSignType());
		ivp.setPaySign(PayUtil.createSign(map, wxConfig.getApiKey()));;
		return ivp;
	}
	
	@Override
	public WxError templateSetIndustry(String industry1, String industry2) throws WxErrorException {
		WxError result = null;
		String url = WxConsts.URL_TEMPLATE_SET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"industry_id1\":\""+industry1+"\",\"industry_id2\":\""+industry2+"\"}";
		try {
			String postResult = post(url, json);
			result = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateSetIndustry failure.");
		}
		return result;
	}
	
	@Override
	public IndustryResult templateGetIndustry() throws WxErrorException {
		IndustryResult result = null;
		String getResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken());
		try {
			getResult = get(url, null);
			result = IndustryResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetIndustry failure.");
		}
		return result;
	}
	
	@Override
	public TemplateResult templateGetId(String template_id_short) throws WxErrorException {
		TemplateResult result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_ID.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"template_id_short\":\""+template_id_short+"\"}";
		try {
			postResult = post(url, json);
			result = TemplateResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetId failure.");
		}
		return result;
	}
	
	@Override
	public TemplateListResult templateGetList() throws WxErrorException {
		TemplateListResult result = null;
		String getResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_LIST.replace("ACCESS_TOKEN", getAccessToken());
		try {
			getResult = get(url, null);
			result = TemplateListResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetList failure.");
		}
		return result;
	}
	
	@Override
	public WxError templateDelete(String template_id) throws WxErrorException {
		WxError result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_DELETE.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"template_id\":\""+template_id+"\"}";
		try {
			postResult = post(url, json);
			result = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateDelete failure.");
		}
		return result;
	}
	
	@Override
	public TemplateSenderResult templateSend(TemplateSender sender) throws WxErrorException {
		TemplateSenderResult result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN", getAccessToken());
		try {
			postResult = post(url, sender.toJson());
			result = TemplateSenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateSend failure.");
		}
		return result;
	}

	protected CloseableHttpClient getHttpclient() {
		return this.httpClient;
	}

	public String get(String url, Map<String, String> params) throws WxErrorException {
		return execute(new SimpleGetRequestExecutor(), url, params);
	}

	public String post(String url, String params) throws WxErrorException {
		return execute(new SimplePostRequestExecutor(), url, params);
	}

	/**
	 * 
	 * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
	 *
	 * @param executor
	 * @param uri
	 * @param data
	 * @return
	 * @throws WxErrorException
	 * @throws IOException 
	 */
	public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException{
		try {
			return executeInternal(executor, uri, data);
		} catch (WxErrorException e) {
			throw e;
		}
	}

	/**
	 * 请求执行器
	 * 
	 * @param executor
	 * @param uri
	 * @param data
	 * @return
	 * @throws WxErrorException
	 * @throws IOException 
	 */
	protected synchronized <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data)
			throws WxErrorException{
		try {
			return executor.execute(getHttpclient(), uri, data);
		} catch (WxErrorException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得wx服务对应的配置对象
	 * */
	@Override
	public WxConfig getWxConfig() {
		return wxConfig;
	}
	
}
