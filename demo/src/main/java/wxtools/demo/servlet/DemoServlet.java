package wxtools.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soecode.wxtools.api.WxConfigStorage;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxInMemoryConfigStorage;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.api.WxServiceImpl;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;

import wxtools.demo.handler.DemoMessageHandler;
import wxtools.demo.interceptor.DemoInterceptor;
import wxtools.demo.matcher.DemoMatcher;



/**
 * <pre>
 * DemoServlet
 * 
 * 接收微信服务器请求
 * </pre>
 * 
 * @author antgan
 *
 */
@WebServlet("/wx")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WxConfigStorage config;
	private WxService service;
	
	public DemoServlet() {
		config = WxInMemoryConfigStorage.getInstance();
		service = WxServiceImpl.getInstance();
		service.setWxConfigStorage(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if(WxServiceImpl.getInstance().checkSignature(signature, timestamp, nonce, echostr)){
			out.print(echostr);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			WxXmlMessage wx=XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
			System.out.println("消息：\n "+wx.toString());
			
			WxXmlOutMessage xmlOutMsg = null;
			String XMLMessage = XStreamTransformer.toXml(WxXmlMessage.class, wx);
			System.out.println("消息(xml)：\n "+XMLMessage);
			
			WxMessageRouter router = new WxMessageRouter(WxServiceImpl.getInstance());
			router.rule().msgType(WxConsts.XML_MSG_TEXT).async(false).matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoMessageHandler()).end();
			xmlOutMsg = router.route(wx);
			if(xmlOutMsg!=null)
				out.print(xmlOutMsg.toXml());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
