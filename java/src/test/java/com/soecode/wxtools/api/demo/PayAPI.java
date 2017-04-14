package com.soecode.wxtools.api.demo;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.InvokePay;
import com.soecode.wxtools.bean.PayOrderInfo;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 支付API 【未测试】
 * @author antgan
 *
 */
public class PayAPI {
	IService iService = new WxService();
	/**
	 * 统一下单接口
	 */
	public void unifiedOrder(){
		//下订单的商品价格相关信息
		PayOrderInfo order = new PayOrderInfo();
		order.setOrderId("订单ID");
		order.setOrderName("商品名");
		order.setDetail("商品详情");
		order.setTotalFee("100");//一块钱，单位为分

		try {
			InvokePay invokePay = iService.unifiedOrder(order, "支付后回调页面URL", "待支付的用户openid");
			//只要把invokePay返回到前端调用JSSDK中的chooseWXPay方法即可
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
