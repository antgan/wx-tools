package wxtools.demo.handler;

import java.util.Map;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * Demo 处理微信消息Handler
 * @author antgan
 *
 */
public class DemoMessageHandler implements WxMessageHandler {
	@Override
	public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, WxService wxService)
			throws WxErrorException {
		WxXmlOutMessage xmlOutMsg = null;
		if(wxMessage.getMsgType().equals(WxConsts.XML_MSG_TEXT)){
			String url = wxService.oauth2buildAuthorizationUrl("http://www.antgan.cn/demo/oauth", WxConsts.OAUTH2_SCOPE_USER_INFO, "param");
			xmlOutMsg = WxXmlOutMessage.TEXT().content(url).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
		}
		return xmlOutMsg;
	}
}
