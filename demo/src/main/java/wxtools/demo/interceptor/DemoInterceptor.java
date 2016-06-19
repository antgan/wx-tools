package wxtools.demo.interceptor;

import java.util.Map;

import com.soecode.wxtools.api.WxMessageInterceptor;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * Demo 拦截器，可以做身份验证，权限验证等操作。
 * @author antgan
 *
 */
public class DemoInterceptor implements WxMessageInterceptor{

	@Override
	public boolean intercept(WxXmlMessage wxMessage, Map<String, Object> context, WxService wxService)
			throws WxErrorException {
		//可以使用wxService的微信API方法
		//可以在Handler和Interceptor传递消息，使用context上下文
		//这里我只简单的验证一下,如果来的消息是admin，允许通过；反之不允许。
		if(wxMessage.getContent().equals("admin")){
			return true;
		}
		return false;
	}
}
