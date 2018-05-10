package com.soecode.wxtools.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.bean.PayOrderInfo;
import com.soecode.wxtools.bean.WxUnifiedOrder;
import com.soecode.wxtools.util.crypto.MD5;

public class PayUtil {

	public static WxUnifiedOrder createPayInfo(PayOrderInfo order, String notifyUrl , String openid) {
		Map<String, String> payinfo = new HashMap<>();
		payinfo.put("appid", WxConfig.getInstance().getAppId());
		payinfo.put("mch_id", WxConfig.getInstance().getMchId());
		payinfo.put("device_info", "WEB");
		payinfo.put("nonce_str", StringUtils.randomStr(32));
		payinfo.put("body", order.getOrderName());
		payinfo.put("detail", order.getDetail());
		payinfo.put("out_trade_no", order.getOrderId());//商品订单号
		payinfo.put("total_fee", ""+order.getTotalFee());
		payinfo.put("attach", order.getAttach());
		payinfo.put("product_id", order.getProductId());	
		payinfo.put("notify_url", notifyUrl);
		payinfo.put("trade_type", order.getTradeType());
		payinfo.put("openid", openid);
		payinfo.put("sign", createSign(payinfo, WxConfig.getInstance().getApiKey()));
		
		WxUnifiedOrder pay = new WxUnifiedOrder();
		pay.setAppid(payinfo.get("appid"));
		pay.setMchId(payinfo.get("mch_id"));
		pay.setDeviceInfo(payinfo.get("device_info"));
		pay.setNonceStr(payinfo.get("nonce_str"));
		pay.setBody(payinfo.get("body"));
		pay.setDetail(payinfo.get("detail"));
		pay.setAttach(payinfo.get("attach"));
		pay.setOutTradeNo(payinfo.get("out_trade_no"));
		pay.setTotalFee(payinfo.get("total_fee"));
		pay.setNotifyUrl(payinfo.get("notify_url"));
		pay.setTradeType(payinfo.get("trade_type"));
		pay.setProductId(payinfo.get("product_id"));
		pay.setOpenid(payinfo.get("openid"));
		pay.setSign(payinfo.get("sign"));
		return pay;
	}
	
	public static String createSign(Map<String, String> payinfo, String keyStr){
		Set<String> keysSet = payinfo.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = payinfo.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            temp.append(valueString);
        }
        String tempStr = temp.toString()+"&key="+keyStr;
        return MD5.getMD5(tempStr, "UTF-8").toUpperCase();
	}
}
