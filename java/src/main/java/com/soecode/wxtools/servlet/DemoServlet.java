package com.soecode.wxtools.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.handler.DemoHandler;
import com.soecode.wxtools.interceptor.DemoInterceptor;
import com.soecode.wxtools.matcher.DemoMatcher;
import com.soecode.wxtools.util.xml.XStreamTransformer;

import net.xerllent.web.SiteManager;
import net.xerllent.web.model.Site;

/**
 * <pre>
 * Demo Servlet
 * 如果使用的是SpringMVC，也是类似这样的写法~
 * 
 * 注意：WxConfig请调用getInstance()
 * 因为对于全局是唯一的。采用单例模式。
 * </pre>
 * 
 * @author antgan
 * @date 2016/12/15
 *
 */
@WebServlet("/wx")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private IService iService = new WxService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 验证服务器的有效性
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		//sxh  根据公众号里预先设置url的appId获取数据库工号配置对象
		String appId = request.getParameter("appId");
		WxConfig config=getWxConfigFromSitesByAppId(appId);
		
		IService iService = new WxService(config);
		
		if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
			out.print(echostr);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 返回消息给微信服务器
		PrintWriter out = response.getWriter();
		// 获取encrypt_type 消息加解密方式标识
		String encrypt_type = request.getParameter("encrypt_type");
		
		//sxh  根据公众号里预先设置url的appId获取数据库工号配置对象
		String appId = request.getParameter("appId");
		WxConfig config=getWxConfigFromSitesByAppId(appId);
		IService iService = new WxService(config);
		
		// 创建一个路由器
		WxMessageRouter router = new WxMessageRouter(iService);
		try {
			// 判断消息加解密方式，如果是加密模式。encrypt_type==aes
			if (encrypt_type != null && "aes".equals(encrypt_type)) {
//				String signature = request.getParameter("signature");
				String timestamp = request.getParameter("timestamp");
				String nonce = request.getParameter("nonce");
				String msg_signature = request.getParameter("msg_signature");

				// 微信服务器推送过来的加密消息是XML格式。使用WxXmlMessage中的decryptMsg()解密得到明文。
				WxXmlMessage wx = WxXmlMessage.decryptMsg(request.getInputStream(), iService.getWxConfig(), timestamp,
						nonce, msg_signature);
				System.out.println("消息：\n " + wx.toString());
				// 添加规则；这里的规则所有消息都交给DemoMatcher处理，交给DemoInterceptor处理，交给DemoHandler处理
				// 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
				// end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
				router.rule().matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoHandler()).end();
				// 把消息传递给路由器进行处理，得到最后一个handler处理的结果
				WxXmlOutMessage xmlOutMsg = router.route(wx);
				if (xmlOutMsg != null) {
					// 将要返回的消息加密，返回
					out.print(WxXmlOutMessage.encryptMsg(iService.getWxConfig(), xmlOutMsg.toXml(), timestamp, nonce));// 返回给用户。
				}
			//如果是明文模式，执行以下语句
			} else {
				// 微信服务器推送过来的是XML格式。
				WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
				System.out.println("消息：\n " + wx.toString());
				iService.getAccessToken();
				router.rule().matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoHandler()).end();
				// 把消息传递给路由器进行处理
				WxXmlOutMessage xmlOutMsg = router.route(wx);
				if (xmlOutMsg != null)
					out.print(xmlOutMsg.toXml());// 因为是明文，所以不用加密，直接返回给用户。
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	/**
	 * 与站群数据库对接,用AppId
	 * @throws Exception 
	 * */
	public static WxConfig getWxConfigFromSitesByAppId(String appId) throws ServletException{
		Site site=SiteManager.getManager().getByAppId(appId);
		if(site==null) throw new ServletException("站点中找不到这个微信appId配置"+appId);
		
		return WxConfig.getInstance(site.getAppId(), site.getAppSecret(), site.getToken(), site.getEncodingAESKey(), null, null);
	}
}