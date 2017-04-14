package com.soecode.wxtools.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.InvokePay;
import com.soecode.wxtools.bean.PayOrderInfo;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.result.UnifiedOrderResult;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 此servlet仅仅作为测试支付用，对于API并无作用。
 * @author antgan
 *
 */
@WebServlet("/jssdk/config")
public class JssdkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IService iService = new WxService();
	
	/**
	 * 获取jssdk相关配置
	 * @return
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer;
		try {
			writer = response.getWriter();
		
//			String url = request.getParameter("url");
//			String jsApi = request.getParameter("jsApi");
			
//			String[] jsApiList = jsApi.split(",");
			List<String> list = new ArrayList<>();
//			for(String s : jsApiList){
//				list.add(s);
//			}
			String url = "http://antgan.hicp.net/wx-tools/pay.jsp";
			list.add("chooseImage");
			list.add("previewImage");
			WxJsapiConfig config = null;
			try {
				config = iService.createJsapiConfig(url, list);
				config.setAppid(WxConfig.getInstance().getAppId());
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
			writer.print(config.toJson());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//TODO
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer;
		try {
			writer = response.getWriter();
		
			PayOrderInfo order = new PayOrderInfo();
			order.setOrderId("123");
			order.setOrderName("测试商品");
			order.setDetail("商品细节");
			order.setTotalFee("1");
			//TODO
			InvokePay pay = null;
			try {
				pay = iService.unifiedOrder(order, "https://www.baidu.com/", "openid");
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			writer.print(pay.toJson());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
