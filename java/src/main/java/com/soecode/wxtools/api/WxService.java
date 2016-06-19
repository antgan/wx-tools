package com.soecode.wxtools.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.soecode.wxtools.bean.WxJsapiSignature;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxMessage;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.WxQrcode;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.WxVideoIntroduction;
import com.soecode.wxtools.bean.result.QrCodeResult;
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
import com.soecode.wxtools.util.http.RequestExecutor;

/**
 * 微信API Service
 * @author antgan
 * @datetime 2016/6/15
 *
 */
public interface WxService {

	/**
	 * <pre>
	 * 验证推送过来的消息的正确性
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/8/f9a0b8382e0b77d87b3bcc1ce6fbc104.html}
	 * </pre>
	 *
	 * @param msgSignature
	 * @param timestamp
	 * @param nonce
	 * @param data  
	 * @return 微信传输过来的数据，有可能是echoStr，有可能是xml消息
	 */
	boolean checkSignature(String msgSignature, String timestamp, String nonce, String data);

	/**
	 * <pre>
	 * 获取access_token, 不强制刷新access_token
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/14/9f9c82c1af308e3b14ba9b973f99a8ba.html}
	 * </pre>
	 * @see #getAccessToken(boolean)
	 * @return
	 * @throws WxErrorException
	 */
	String getAccessToken() throws WxErrorException;

	/**
	 * <pre>
	 * 获取access_token，本方法线程安全
	 * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
	 * 另：本service的所有方法都会在access_token过期是调用此方法
	 * 程序员在非必要情况下尽量不要主动调用此方法
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/14/9f9c82c1af308e3b14ba9b973f99a8ba.html}
	 * </pre>
	 * 
	 * @param forceRefresh
	 *            强制刷新
	 * @return
	 * @throws WxErrorException
	 */
	String getAccessToken(boolean forceRefresh) throws WxErrorException;

	/**
	 * <pre>
	 * 自定义菜单创建接口
	 * 详情请见:{@link http://mp.weixin.qq.com/wiki/10/0234e39a2025342c17a7d23595c6b40a.html}
	 *
	 * </pre>
	 * @param menu
	 * @param condition 是否为个性菜单，当为个性菜单，需要添加MatchRule
	 * @throws WxErrorException
	 */
	String createMenu(WxMenu menu, boolean condition) throws WxErrorException;

	/**
	 * <pre>
	 * 【自定义】菜单删除接口
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/3/de21624f2d0d3dafde085dafaa226743.html}
	 * 
	 * </pre>
	 * @return String 查询结果
	 * @throws WxErrorException
	 */
	String deleteMenu() throws WxErrorException;

	/**
	 * <pre>
	 * 【个性化】菜单删除接口
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html}
	 * 
	 * </pre>
	 * @param menuid
	 * @return String 查询结果
	 * @throws WxErrorException
	 */
	String deleteMenu(String menuid) throws WxErrorException;

	/**
	 * <pre>
	 * 自定义菜单查询接口
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/5/f287d1a5b78a35a8884326312ac3e4ed.html}
	 * </pre>
	 *
	 * @return WxMenuResult 菜单栏查询结果
	 * @throws WxErrorException
	 */
	WxMenuResult getMenu() throws WxErrorException;

	/**
	 * <pre>
	 * 获取公众号菜单配置
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/14/293d0cb8de95e916d1216a33fcb81fd6.html}
	 * </pre>
	 *
	 * @return WxCurMenuInfoResult 菜单栏菜单配置结果
	 * @throws WxErrorException
	 */
	WxCurMenuInfoResult getMenuCurInfo() throws WxErrorException;
	
	/**
	 * <pre>
	 * 个性化菜单栏
	 * 测试个性化菜单匹配结果
	 * </pre>
	 * @param user_id user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
	 * @return
	 * @throws WxErrorException
	 */
	String menuTryMatch(String user_id) throws WxErrorException;
	
	/**
	 * <pre>
	 * 上传【临时】多媒体文件，三天后微信服务器自动删除。
	 * 上传的多媒体文件有格式和大小限制，如下：
	 *   图片（image）: 1M，支持JPG格式
	 *   语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 *   视频（video）：10MB，支持MP4格式
	 *   缩略图（thumb）：64KB，支持JPG格式
	 *   临时素材 请见：{@link http://mp.weixin.qq.com/wiki/15/2d353966323806a202cd2deaafe8e557.html}
	 * </pre>
	 *
	 * @param mediaType 媒体类型  {@link WxConsts}
	 * @param fileType  文件类型  {@link WxConsts}
	 * @param inputStream
	 *            输入流
	 * @throws WxErrorException
	 */
	WxMediaUploadResult uploadTempMedia(String mediaType, String fileType, InputStream inputStream)
			throws WxErrorException, IOException;

	/**
	 * <pre>
	 * 上传【临时】多媒体文件，三天后微信服务器自动删除。
	 * 上传的多媒体文件有格式和大小限制，如下：
	 *   图片（image）: 1M，支持JPG格式
	 *   语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 *   视频（video）：10MB，支持MP4格式
	 *   缩略图（thumb）：64KB，支持JPG格式
	 *   临时素材 请见：{@link http://mp.weixin.qq.com/wiki/15/2d353966323806a202cd2deaafe8e557.html}
	 * </pre>
	 *
	 * @param mediaType 媒体类型  {@link WxConsts}
	 * @param fileType  文件类型  {@link WxConsts}
	 * @throws WxErrorException
	 */
	WxMediaUploadResult uploadTempMedia(String mediaType, File file) throws WxErrorException;

	/**
	 * <pre>
	 * 下载【临时】多媒体文件，下载到{@link WxConfigStorage}中的【临时】目录下（TmpDirFile）
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/9/677a85e3f3849af35de54bb5516c2521.html}
	 * </pre>
	 *
	 * @return File 临时文件File
	 * @throws WxErrorException
	 * @params media_id
	 */
	File downloadTempMedia(String media_id) throws WxErrorException;
	
	
	/**
	 * <pre>
	 * 上传【永久】多媒体文件
	 * 上传的多媒体文件有格式和大小限制，如下：
	 *   图片（image）: 1M，支持JPG格式
	 *   语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 *   视频（video）：10MB，支持MP4格式
	 *   缩略图（thumb）：64KB，支持JPG格式
	 *   永久素材 请见: {@link http://mp.weixin.qq.com/wiki/10/10ea5a44870f53d79449290dfd43d006.html}
	 * </pre>
	 *
	 * @param mediaType 媒体类型  {@link WxConsts}
	 * @param fileType  文件类型  {@link WxConsts}
	 * @param introduction 当上传为视频资源时(Video)，可以附带说明。其他资源传null即可
	 * @param inputStream 输入流
	 * @throws WxErrorException
	 */
	WxMediaUploadResult uploadMedia(String mediaType, String fileType, InputStream inputStream, WxVideoIntroduction introduction)
			throws WxErrorException, IOException;

	/**
	 * <pre>
	 * 上传【永久】多媒体文件
	 * 上传的多媒体文件有格式和大小限制，如下：
	 *   图片（image）: 1M，支持JPG格式
	 *   语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 *   视频（video）：10MB，支持MP4格式
	 *   缩略图（thumb）：64KB，支持JPG格式
	 *   永久素材 请见: {@link http://mp.weixin.qq.com/wiki/10/10ea5a44870f53d79449290dfd43d006.html}
	 * </pre>
	 *
	 * @param mediaType 媒体类型  {@link WxConsts}
	 * @param fileType  文件类型  {@link WxConsts}
	 * @param introduction 当上传为视频资源时(Video)，可以附带说明。其他资源传null即可
	 * @throws WxErrorException
	 */
	WxMediaUploadResult uploadMedia(String mediaType, File file, WxVideoIntroduction introduction) throws WxErrorException;
	
	/**
	 * <pre>
	 * 下载【永久】多媒体文件，下载到{@link WxConfigStorage}中的【永久】目录下（MaterialDirFile）
	 * 详情请见: http://mp.weixin.qq.com/wiki/12/3c12fac7c14cb4d0e0d4fe2fbc87b638.html
	 * </pre>
	 *
	 * @return 保存到本地的永久文件
	 * @throws WxErrorException
	 * @params media_id
	 */
	File downloadMedia(String media_id) throws WxErrorException;
	
	/**
	 * 下载获取永久图文素材，返回图文信息结果{@link WxNewsMediaResult}
	 * @param media_id
	 * @return
	 * @throws WxErrorException
	 */
	WxNewsMediaResult downloadNewsMedia(String media_id) throws WxErrorException;
	
	/**
	 * <pre>
	 * 下载获取永久视频素材，返回图文信息结果{@link WxVideoMediaResult}
	 * 保存在 {@link WxConfigStorage} 下的永久目录下
	 * </pre>
	 * @param media_id
	 * @return
	 * @throws WxErrorException
	 */
	WxVideoMediaResult downloadVideoMedia(String media_id) throws WxErrorException;
	
	/**
	 * 删除永久素材
	 * @param media_id
	 * @return WxError ，若 errcode = 0 则删除成功
	 * @throws WxErrorException
	 */
	WxError deleteMediaMaterial(String media_id) throws WxErrorException;
	
	/**
	 * <pre>
	 * 新增永久图文，最多8个
	 * 
	 * 请注意，在图文消息的具体内容中，将过滤外部的图片链接，只能使用腾讯域名下的图片资源。
	 * 可以调用 imageDomainChange() 方法将图片上传转成腾讯域名下的图片资源。
	 * </pre>
	 * @param news
	 * @return String MediaId
	 * @throws WxErrorException
	 */
	String addNewsMedia(List<WxNewsInfo> news) throws WxErrorException;
	
	/**
	 * <pre>
	 * 上传图片，获取腾讯域名下的图片资源路径
	 * 
	 * </pre>
	 * @return WxMediaUploadResult    获取成员变量Url
	 * 								腾讯域名下的图片资源。用于图文正文
	 * @throws WxErrorException
	 */
	WxMediaUploadResult imageDomainChange(File file) throws WxErrorException;
	
	/**
	 * <pre>
	 * 修改永久图文素材
	 * 
	 * </pre>
	 * @param media_id  图文id
	 * @param index  图文的位置，第一篇为0
	 * @param WxNewsInfo 新的图文
	 * 
	 * @return WxError, 若errcode = 0 修改成功
	 * @throws WxErrorException
	 */
	WxError updateNewsInfo(String media_id, int index, WxNewsInfo newInfo) throws WxErrorException;
	
	/**
	 * <pre>
	 * 获取永久素材数量接口
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/5/a641fd7b5db7a6a946ebebe2ac166885.html}
	 * </pre>
	 * @return WxMaterialCountResult
	 * @throws WxErrorException
	 */
	WxMaterialCountResult getMaterialCount() throws WxErrorException;
	
	/**
	 * <pre>
	 * 批量获取永久素材资源信息
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/15/8386c11b7bc4cdd1499c572bfe2e95b3.html}
	 * </pre> 
	 * @param type
	 * @param offset
	 * @param count
	 * @return WxBatchGetMaterialResult
	 * @throws WxErrorException
	 */
	WxBatchGetMaterialResult batchGetMeterial(String type, int offset, int count) throws WxErrorException;
	
	/**
	 * <pre>
	 * 创建用户分组
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/8/d6d33cf60bce2a2e4fb10a21be9591b8.html}
	 * </pre>
	 * @param name
	 * @return
	 * @throws WxErrorException
	 */
	WxUserGroupResult createUserGroup(String name) throws WxErrorException;
	
	/**
	 * <pre>
	 * 查询所有用户分组
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/8/d6d33cf60bce2a2e4fb10a21be9591b8.html}
	 * </pre>
	 * @param name
	 * @return
	 * @throws WxErrorException
	 */
	WxUserGroupResult queryAllUserGroup() throws WxErrorException;
	
	/**
	 * <pre>
	 * 查询用户所在用户组
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/8/d6d33cf60bce2a2e4fb10a21be9591b8.html}
	 * </pre>
	 * @param openid
	 * @return
	 * @throws WxErrorException
	 */
	int queryGroupIdByOpenId(String openid) throws WxErrorException;
	
	/**
	 * <pre>
	 * 修改用户组名
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/8/d6d33cf60bce2a2e4fb10a21be9591b8.html}
	 * </pre>
	 * @param groupId
	 * @param name
	 * @return
	 * @throws WxErrorException
	 */
	WxError updateUserGroupName(int groupid, String name) throws WxErrorException;
	
	/**
	 * <pre>
	 * 移动一个用户到指定用户组里
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/8/d6d33cf60bce2a2e4fb10a21be9591b8.html}
	 * </pre>
	 * @param openid
	 * @param to_groupid
	 * @return
	 * @throws WxErrorException
	 */
	WxError movingUserToNewGroup(String openid, int to_groupid) throws WxErrorException;
	
	/**
	 * <pre>
	 * 批量移动用户到指定用户组
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/8/d6d33cf60bce2a2e4fb10a21be9591b8.html}
	 * </pre>
	 * @param openids
	 * @param to_groupid
	 * @return
	 * @throws WxErrorException
	 */
	WxError batchMovingUserToNewGroup(List<String> openids, int to_groupid) throws WxErrorException;
	
	/**
	 * <pre>
	 * 删除用户组
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/8/d6d33cf60bce2a2e4fb10a21be9591b8.html}
	 * </pre>
	 * @param groupid
	 * @return
	 * @throws WxErrorException
	 */
	WxError deleteUserGroup(int groupid) throws WxErrorException;
	
	/**
	 * <pre>
	 * 修改用户备注名
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/16/528098c4a6a87b05120a7665c8db0460.html}
	 * </pre>
	 * @param openid
	 * @param remark
	 * @return
	 * @throws WxErrorException
	 */
	WxError updateUserRemark(String openid, String remark) throws WxErrorException;
	
	/**
	 * <pre>
	 * 获取用户个人信息
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html}
	 * </pre>
	 * @param openid
	 * @param lang  返回结果的语言版本  {@link WxConsts} 
	 * @return
	 * @throws WxErrorException
	 */
	WxUser getUserInfoByOpenId(WxUserGet userGet) throws WxErrorException;
	
	/**
	 * <pre>
	 * 批量获取用户信息
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html}
	 * </pre>
	 * @param usersGet
	 * @return
	 * @throws WxErrorException
	 */
	WxUserList batchGetUserInfo(List<WxUserGet> usersGet) throws WxErrorException;
	
	/**
	 * <pre>
	 * 批量获取关注者openid
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/12/54773ff6da7b8bdc95b7d2667d84b1d4.html}
	 * </pre>
	 * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取
	 * @return
	 * @throws WxErrorException
	 */
	WxUserListResult batchGetUserOpenId(String next_openid) throws WxErrorException;
	
	/**
	 * <pre>
	 * 构造oauth2授权的url连接
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html}
	 * </pre>
	 * 
	 * @param redirectUri   重定向地址
	 * @param scope {@link WxConsts} 
	 * 				snsapi_base 用户静默授权
	 * 				snsapi_userinfo 用户手动授权
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return url
	 */
	String oauth2buildAuthorizationUrl(String redirectUri, String scope, String state) throws WxErrorException;;

	/**
	 * <pre>
	 * 获取OAuth2.0认证的凭证 access_token
	 * 这个access_token跟全局的access_token不同，可以到官方文档查阅
	 * {@link http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html}
	 * </pre>
	 * @param code  
	 * 				oauth2buildAuthorizationUrl()重定向后会携带code
	 * @return
	 * @throws WxErrorException
	 */
	WxOAuth2AccessTokenResult oauth2ToGetAccessToken(String code) throws WxErrorException; 
	
	/**
	 * <pre>
	 * 刷新access_token（如果需要）
	 * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
	 * refresh_token拥有较长的有效期（7天、30天、60天、90天），当refresh_token失效的后，需要用户重新授权
	 * 
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html}
	 * </pre>
	 * @param refresh_token 填写通过access_token获取到的refresh_token参数
	 * @return
	 * @throws WxErrorException
	 */
	WxOAuth2AccessTokenResult oauth2ToGetRefreshAccessToken(String refresh_token) throws WxErrorException; 
	
	/**
	 * <pre>
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
	 * 
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html}
	 * 
	 * </pre>
	 * @param userGet
	 * @return
	 * @throws WxErrorException
	 */
	WxUser oauth2ToGetUserInfo(String access_token, WxUserGet userGet) throws WxErrorException; 
	
	/**
	 * <pre>
	 * 检验授权凭证（access_token）是否有效
	 * 
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html}
	 * </pre>
	 * @param access_token
	 * @param openid
	 * @return WxError 若errcode=0，有效；反之，无效。
	 * @throws WxErrorException
	 */
	WxError oauth2CheckAccessToken(String access_token, String openid) throws WxErrorException; 
	
	/**
	 * <pre>
	 * 生成二维码
	 * 详情请见：{@link http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html}
	 * </pre>
	 * @param WxQrcode里的action_name
	 * 					{@link WxConsts.QR_CODE_LIMIT_SCENE} 临时二维码
	 * 					{@link WxConsts.QR_CODE_LIMIT_STR_SCENE} 永久二维码
	 * @return
	 * @throws WxErrorException
	 */
	QrCodeResult createQrCode(WxQrcode qrcode) throws WxErrorException; 
	
	/**
	 * <pre>
	 * 下载/展示 二维码
	 * </pre>
	 * @param dir 二维码存放目录目录
	 * @param ticket
	 * @return
	 * @throws WxErrorException
	 */
	File downloadQrCode(File dir, String ticket) throws WxErrorException; 
	
	/**
	 * 长链接转成短链接
	 * @param long_url
	 * @return
	 * @throws WxErrorException
	 */
	String getShortUrl(String long_url) throws WxErrorException; 
	
	/////////////////////////////////////////////////////////
	
	/**
	 * 获得jsapi_ticket,不强制刷新jsapi_ticket
	 * 
	 * @see #getJsapiTicket(boolean)
	 * @return
	 * @throws WxErrorException
	 */
	public String getJsapiTicket() throws WxErrorException;

	/**
	 * <pre>
	 * 获得jsapi_ticket
	 * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
	 *
	 * 详情请见：http://mp.weixin.qq.com/wiki/11/74ad127cc054f6b80759c40f77ec03db.html
	 * </pre>
	 * 
	 * @param forceRefresh
	 *            强制刷新
	 * @return
	 * @throws WxErrorException
	 */
	public String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

	/**
	 * <pre>
	 * 创建调用jsapi时所需要的签名
	 *
	 * 详情请见：http://mp.weixin.qq.com/wiki/11/74ad127cc054f6b80759c40f77ec03db.html
	 * 校验：http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign
	 * </pre>
	 * 
	 * @param url
	 *            url
	 * @return
	 */
	public WxJsapiSignature createJsapiSignature(String url) throws WxErrorException;

	/**
	 * <pre>
	 * 获取微信服务器的ip段
	 * 详情请见: {@link http://mp.weixin.qq.com/wiki/4/41ef0843d6e108cf6b5649480207561c.html}
	 * </pre>
	 * 
	 * @return { "ip_list": ["101.226.103.*", "101.226.62.*"] }
	 * @throws WxErrorException
	 */
	String[] getCallbackIp() throws WxErrorException;


	/**
	 * 注入 {@link WxConfigStorage} 的实现
	 *
	 * @param wxConfigProvider
	 */
	void setWxConfigStorage(WxConfigStorage wxConfigProvider);

	/**
	 * <pre>
	 * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
	 * 默认：1000ms
	 * </pre>
	 * 
	 * @param retrySleepMillis
	 */
	void setRetrySleepMillis(int retrySleepMillis);

	/**
	 * <pre>
	 * 设置当微信系统响应系统繁忙时，最大重试次数
	 * 默认：5次
	 * </pre>
	 * 
	 * @param maxRetryTimes
	 */
	void setMaxRetryTimes(int maxRetryTimes);
	
	/**
	 * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
	 * 
	 * @param url
	 * @param queryParam
	 * @return
	 * @throws WxErrorException
	 */
	String get(String url, Map<String, String> params) throws WxErrorException;

	/**
	 * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
	 * 
	 * @param url
	 * @param postData
	 * @return
	 * @throws WxErrorException
	 */
	String post(String url, String params) throws WxErrorException;

	/**
	 * <pre>
	 * Service没有实现某个API的时候，可以用这个，
	 * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
	 * 可以参考，{@link com.soecode.wxtools.utll.http.MediaDownloadGetRequestExecutor}的实现方法
	 * </pre>
	 * 
	 * @param executor
	 * @param uri
	 * @param data
	 * @param <T>
	 * @param <E>
	 * @return
	 * @throws WxErrorException
	 */
	<T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

}
