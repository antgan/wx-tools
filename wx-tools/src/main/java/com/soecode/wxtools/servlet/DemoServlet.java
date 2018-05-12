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
import jdk.nashorn.internal.ir.annotations.Ignore;

@WebServlet("/wx")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IService iService = new WxService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
			out.print(echostr);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String encrypt_type = request.getParameter("encrypt_type");
		WxMessageRouter router = new WxMessageRouter(iService);
		try {
			if (encrypt_type != null && "aes".equals(encrypt_type)) {
//				String signature = request.getParameter("signature");
				String timestamp = request.getParameter("timestamp");
				String nonce = request.getParameter("nonce");
				String msg_signature = request.getParameter("msg_signature");

				WxXmlMessage wx = WxXmlMessage.decryptMsg(request.getInputStream(), WxConfig.getInstance(), timestamp,
						nonce, msg_signature);
				System.out.println("Message：\n " + wx.toString());
				router.rule().matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoHandler()).end();
				WxXmlOutMessage xmlOutMsg = router.route(wx);
				if (xmlOutMsg != null) {
					out.print(WxXmlOutMessage.encryptMsg(WxConfig.getInstance(), xmlOutMsg.toXml(), timestamp, nonce));
				}
			} else {
				WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
				System.out.println("Message：\n " + wx.toString());
				iService.getAccessToken();
				router.rule().matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoHandler()).end();
				WxXmlOutMessage xmlOutMsg = router.route(wx);
				if (xmlOutMsg != null)
					out.print(xmlOutMsg.toXml());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}