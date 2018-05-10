package com.soecode.wxtools.api;

import com.soecode.wxtools.bean.KfAccount;
import com.soecode.wxtools.bean.KfSender;
import com.soecode.wxtools.bean.result.KfAccountListResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.soecode.wxtools.bean.InvokePay;
import com.soecode.wxtools.bean.PayOrderInfo;
import com.soecode.wxtools.bean.PreviewSender;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.WxTagSender;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.WxQrcode;
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
import com.soecode.wxtools.bean.result.WxBatchGetMaterialResult;
import com.soecode.wxtools.bean.result.WxCurMenuInfoResult;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxMaterialCountResult;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.bean.result.WxMenuResult;
import com.soecode.wxtools.bean.result.WxNewsMediaResult;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.bean.result.WxUserTagResult;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.bean.result.WxVideoMediaResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.http.RequestExecutor;


public interface IService {

	boolean checkSignature(String msgSignature, String timestamp, String nonce, String data);

	String getAccessToken() throws WxErrorException;

	String getAccessToken(boolean forceRefresh) throws WxErrorException;

	String createMenu(WxMenu menu, boolean condition) throws WxErrorException;

	String deleteMenu() throws WxErrorException;

	String deleteMenu(String menuId) throws WxErrorException;

	WxMenuResult getMenu() throws WxErrorException;

	WxCurMenuInfoResult getMenuCurInfo() throws WxErrorException;

	String menuTryMatch(String userId) throws WxErrorException;

	WxMediaUploadResult uploadTempMedia(String mediaType, String fileType, InputStream inputStream)
			throws WxErrorException, IOException;

	WxMediaUploadResult uploadTempMedia(String mediaType, File file) throws WxErrorException;

	File downloadTempMedia(String mediaId, File path) throws WxErrorException;

	WxMediaUploadResult uploadMedia(String mediaType, String fileType, InputStream inputStream, WxVideoIntroduction introduction)
			throws WxErrorException, IOException;

	WxMediaUploadResult uploadMedia(String mediaType, File file, WxVideoIntroduction introduction) throws WxErrorException;

	File downloadMedia(String mediaId, File path) throws WxErrorException;

	WxNewsMediaResult downloadNewsMedia(String mediaId) throws WxErrorException;

	WxVideoMediaResult downloadVideoMedia(String mediaId, File path) throws WxErrorException;

	WxError deleteMediaMaterial(String mediaId) throws WxErrorException;

	WxMediaUploadResult imageDomainChange(File file) throws WxErrorException;

	String addNewsMedia(List<WxNewsInfo> news) throws WxErrorException;

	WxError updateNewsInfo(String mediaId, int index, WxNewsInfo newInfo) throws WxErrorException;

	WxMaterialCountResult getMaterialCount() throws WxErrorException;

	WxBatchGetMaterialResult batchGetMeterial(String type, int offset, int count) throws WxErrorException;

	WxUserTagResult createUserTag(String name) throws WxErrorException;

	WxError deleteUserTag(int tagId) throws WxErrorException;

	WxUserTagResult queryAllUserTag() throws WxErrorException;

	WxUserListResult queryAllUserUnderByTag(int tagId, String nextOpenid) throws WxErrorException;

	WxError updateUserTagName(int tagId, String name) throws WxErrorException;

	WxError batchMovingUserToNewTag(List<String> userIdList, int toTagId) throws WxErrorException;

	WxError batchRemoveUserTag(List<String> userIdList, int tagId) throws WxErrorException;

	WxError updateUserRemark(String openid, String remark) throws WxErrorException;

	WxUser getUserInfoByOpenId(WxUserGet userGet) throws WxErrorException;

	WxUserList batchGetUserInfo(List<WxUserGet> usersGet) throws WxErrorException;

	WxUserListResult batchGetUserOpenId(String nextOpenid) throws WxErrorException;

	WxError batchAddUserToBlackList(List<String> userList) throws WxErrorException;

	WxError batchRemoveUserFromBlackList(List<String> userList) throws WxErrorException;

	WxUserListResult batchGetUsersFromBlackList(String nextOpenId) throws WxErrorException;

	String oauth2buildAuthorizationUrl(String redirectUri, String scope, String state) throws WxErrorException;;

	WxOAuth2AccessTokenResult oauth2ToGetAccessToken(String code) throws WxErrorException;

	WxOAuth2AccessTokenResult oauth2ToGetRefreshAccessToken(String refreshToken) throws WxErrorException;

	WxUser oauth2ToGetUserInfo(String accessToken, WxUserGet userGet) throws WxErrorException;

	WxError oauth2CheckAccessToken(String accessToken, String openid) throws WxErrorException;

	QrCodeResult createQrCode(WxQrcode qrcode) throws WxErrorException;

	File downloadQrCode(File dir, String ticket) throws WxErrorException;

	String getShortUrl(String long_url) throws WxErrorException;

	String getJsapiTicket() throws WxErrorException;

	String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

	WxJsapiConfig createJsapiConfig(String url, List<String> jsApiList) throws WxErrorException;

	String[] getCallbackIp() throws WxErrorException;

	SenderResult sendAllByTag(WxTagSender sender) throws WxErrorException;

	SenderResult sendAllByOpenid(WxOpenidSender sender) throws WxErrorException;

	SenderResult sendAllPreview(PreviewSender sender) throws WxErrorException;

	SenderResult sendAllDelete(String msgId) throws WxErrorException;

	SenderResult sendAllGetStatus(String msgId) throws WxErrorException;

	WxError templateSetIndustry(String industry1, String industry2) throws WxErrorException;

	IndustryResult templateGetIndustry() throws WxErrorException;

	TemplateResult templateGetId(String templateIdShort)throws WxErrorException;

	TemplateListResult templateGetList()throws WxErrorException;

	WxError templateDelete(String templateId)throws WxErrorException;

	TemplateSenderResult templateSend(TemplateSender sender)throws WxErrorException;

	@Deprecated
	InvokePay unifiedOrder(PayOrderInfo order, String notifyUrl , String openid) throws WxErrorException;

	WxError addKfAccount(KfAccount account) throws WxErrorException;

	WxError updateKfAccount(KfAccount account) throws WxErrorException;

	WxError deleteKfAccount(KfAccount account) throws WxErrorException;

	WxError updateKfHeadImage(String kfAccount, File file) throws WxErrorException;

	KfAccountListResult getAllKfAccount() throws WxErrorException;

	WxError sendMessageByKf(KfSender sender) throws WxErrorException;

	String get(String url, Map<String, String> params) throws WxErrorException;

	String post(String url, String params) throws WxErrorException;

	<T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

}
