package com.soecode.wxtools.api;

public class WxConsts {

	public static final String XML_MSG_TEXT = "text";
	public static final String XML_MSG_IMAGE = "image";
	public static final String XML_MSG_VOICE = "voice";
	public static final String XML_MSG_VIDEO = "video";
	public static final String XML_MSG_NEWS = "news";
	public static final String XML_MSG_MUSIC = "music";
	public static final String XML_MSG_LOCATION = "location";
	public static final String XML_MSG_LINK = "link";
	public static final String XML_MSG_EVENT = "event";
	public static final String XML_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";

	public static final String CUSTOM_MSG_TEXT = "text";
	public static final String CUSTOM_MSG_IMAGE = "image";
	public static final String CUSTOM_MSG_VOICE = "voice";
	public static final String CUSTOM_MSG_VIDEO = "video";
	public static final String CUSTOM_MSG_MUSIC = "music";
	public static final String CUSTOM_MSG_NEWS = "news";
	public static final String CUSTOM_MSG_FILE = "file";
	public static final String CUSTOM_MSG_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
	public static final String CUSTOM_MSG_SAFE_NO = "0";
	public static final String CUSTOM_MSG_SAFE_YES = "1";

	public static final String MASS_MSG_MPNEWS = "mpnews";
	public static final String MASS_MSG_NEWS = "news";
	public static final String MASS_MSG_TEXT = "text";
	public static final String MASS_MSG_VOICE = "voice";
	public static final String MASS_MSG_IMAGE = "image";
	public static final String MASS_MSG_MPVIDEO = "mpvideo";
	public static final String MASS_MSG_VIDEO = "video";
	public static final String MASS_MSG_MUSIC = "music";

	public static final String EVT_SUBSCRIBE = "subscribe";
	public static final String EVT_UNSUBSCRIBE = "unsubscribe";
	public static final String EVT_SCAN = "SCAN";
	public static final String EVT_LOCATION = "LOCATION";
	public static final String EVT_CLICK = "CLICK";
	public static final String EVT_VIEW = "VIEW";
	public static final String EVT_MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";
	public static final String EVT_SCANCODE_PUSH = "scancode_push";
	public static final String EVT_SCANCODE_WAITMSG = "scancode_waitmsg";
	public static final String EVT_PIC_SYSPHOTO = "pic_sysphoto";
	public static final String EVT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	public static final String EVT_PIC_WEIXIN = "pic_weixin";
	public static final String EVT_LOCATION_SELECT = "location_select";
	public static final String EVT_TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";
	public static final String EVT_ENTER_AGENT = "enter_agent";
	public static final String EVT_QUALIFICATION_VERIFY_SUCCESS = "qualification_verify_success";
	public static final String EVT_QUALIFICATION_VERIFY_FAIL = "qualification_verify_fail";
	public static final String EVT_NAMING_VERIFY_SUCCESS = "naming_verify_success";
	public static final String EVT_NAMING_VERIFY_FAIL = "naming_verify_fail";
	public static final String EVT_ANNUAL_RENEW = "annual_renew";
	public static final String EVT_VERIFY_EXPIRED = "verify_expired";

	public static final String MEDIA_IMAGE = "image";
	public static final String MEDIA_VOICE = "voice";
	public static final String MEDIA_VIDEO = "video";
	public static final String MEDIA_THUMB = "thumb";
	public static final String MEDIA_FILE = "file";
	public static final String FILE_JPG = "jpeg";
	public static final String FILE_MP3 = "mp3";
	public static final String FILE_AMR = "amr";
	public static final String FILE_MP4 = "mp4";

	public static final String MENU_BUTTON_CLICK = "click";
	public static final String MENU_BUTTON_VIEW = "view";
	public static final String MENU_SCANCODE_PUSH = "scancode_push";
	public static final String MENU_SCANCODE_WAITMSG = "scancode_waitmsg";
	public static final String MENU_PIC_SYSPHOTO = "pic_sysphoto";
	public static final String MENU_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	public static final String MENU_PIC_WEIXIN = "pic_weixin";
	public static final String MENU_LOCATION_SELECT = "location_select";
	public static final String MENU_MEDIA_ID = "media_id";
	public static final String MENU_VIEW_LIMITED = "view_limited";

	public static final String QR_CODE_LIMIT_SCENE = "QR_LIMIT_SCENE";
	public static final String QR_CODE_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

	public static final String OAUTH2_SCOPE_BASE = "snsapi_base";
	public static final String OAUTH2_SCOPE_USER_INFO = "snsapi_userinfo";

	public static final String LANG_CHINA = "zh_CN";
	public static final String LANG_CHINA_TAIWAN = "zh_TW";
	public static final String LANG_ENGLISH = "en";
	
	public static final String MATERIAL_NEWS = "news";
	public static final String MATERIAL_VOICE = "voice";
	public static final String MATERIAL_IMAGE = "image";
	public static final String MATERIAL_VIDEO = "video";
	
	public static final String SEND_ALL_NEWS = "mpnews";
	public static final String SEND_ALL_TEXT = "text";
	public static final String SEND_ALL_VOICE = "voice";
	public static final String SEND_ALL_IMAGE = "image";
	public static final String SEND_ALL_VIDEO = "mpvideo";
	

	public static final String URL_GET_ACCESSTOEKN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String URL_GET_WX_SERVICE_IP = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	public static final String URL_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String URL_CREATE_MENU_CONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_MENU_CONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";
	public static final String URL_GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static final String URL_GET_CURRENT_MENU_INFO = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
	public static final String URL_TRYMATCH_MENU = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN";
	
	public static final String URL_UPLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static final String URL_DOWNLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	public static final String URL_UPLOAD_MATERIAL_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
	public static final String URL_BATCHGET_MATERIAL_MEDIA_LIST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	public static final String URL_DOWNLOAD_MATERIAL_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_MATERIAL_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
	public static final String URL_GET_MATERIAL_COUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
	public static final String URL_ADD_NEWS_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	public static final String URL_IMAGE_DOMAIN_CHANGE = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_NEWS_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";

	public static final String URL_CREATE_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";
	public static final String URL_QUERY_ALL_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
	public static final String URL_QUERY_ALL_USER_UNDER_TAG = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_USER_TAG_NAME = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
	public static final String URL_BATCH_MOVING_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
	public static final String URL_BATCH_UN_TAG_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";

	public static final String URL_UPDATE_USER_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	public static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String URL_BATCH_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	public static final String URL_BATCH_GET_USER_OPENID = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	public static final String URL_OAUTH2_GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public static final String URL_OAUTH2_GET_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String URL_OAUTH2_GET_REFRESH_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	public static final String URL_OAUTH2_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String URL_OAUTH2_CHECK_ACCESSTOKEN = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	public static final String URL_BATCH_ADD_USER_TO_BLACK_LISE = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN";
	public static final String URL_BATCH_REMOVE_USER_FROM_BLACK_LISE = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN";
	public static final String URL_BATCH_GET_USERS_FROM_BLACK_LISE = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN";
	
	public static final String URL_GET_QR_CODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	public static final String URL_DOWNLOAD_QR_CODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	public static final String URL_LONGURL_TO_SHORTURL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";

	public static final String URL_GET_JS_API_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	public static final String URL_TAG_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	public static final String URL_OPENID_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_SEND_ALL ="https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
	public static final String URL_PREVIEW_SEND_ALL ="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	public static final String URL_GET_STATUS_SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN";
	
	public static final String URL_PAY_UNIFIEORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	public static final String URL_TEMPLATE_SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_GET_ID = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_GET_LIST = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_DELETE = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
	public static final String URL_TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	public static final String URL_ADD_KF_ACCOUNT = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_KF_ACCOUNT = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_KF_ACCOUNT = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_KF_HEAD_IMAGE = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
	public static final String URL_GET_ALL_KF_ACCOUNT = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
	public static final String URL_KF_SEND_MESSAGE_TO_USER = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	public static final String URL_KF_SEND_TYPING_TO_USER = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN";

}
