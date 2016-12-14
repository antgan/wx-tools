package com.soecode.wxtools.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.handler.DemoHandler;
import com.soecode.wxtools.util.crypto.WXBizMsgCrypt;
import com.soecode.wxtools.util.xml.XStreamTransformer;

public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 验证服务器的有效性
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (WxService.getInstance().checkSignature(signature, timestamp, nonce, echostr)) {
			out.print(echostr);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回消息给微信服务器
        PrintWriter out = null;
        String signature = request.getParameter("signature").toString();
        System.out.println("signature: "+ signature);
        String timestamp = request.getParameter("timestamp").toString();
        String nonce = request.getParameter("nonce").toString();
        System.out.println(request.getParameter("encrypt_type").toString());
        String msg_signature = request.getParameter("msg_signature").toString();
        try {
        	WxMessageRouter router = new WxMessageRouter(WxService.getInstance());
        	out = response.getWriter();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            //微信服务器推送过来的消息是XML格式。使用XStreamTransformer来反序列成WxXmlMessage对象
//            WxXmlMessage wx=XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
//            WxXmlMessage wx = WxXmlMessage.fromEncryptedXml(request.getInputStream(), WxConfig.getInstance(), timestamp, nonce, msg_signature);
            
            WxXmlMessage wx = WxXmlMessage.decryptMsg(request.getInputStream(), WxConfig.getInstance(), timestamp, nonce, msg_signature );

            System.out.println("消息：\n "+wx.toString());

            //添加规则；这里的规则是指，只接收TEXT类型，同步接收，交给DemoMatcher处理，交给DemoInterceptor处理，交给DemoMessageHandler处理
            //注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
            //end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
            router.rule().handler(new DemoHandler()).end();
            //把消息传递给路由器进行处理，返回值是WxXmlMessage的子类或者null
           WxXmlOutMessage xmlOutMsg = router.route(wx);
            if(xmlOutMsg!=null)
                out.print(WxXmlOutMessage.encryptMsg(WxConfig.getInstance(), xmlOutMsg.toXml(), timestamp, nonce));//返回给用户。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}