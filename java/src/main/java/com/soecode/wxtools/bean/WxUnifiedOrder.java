package com.soecode.wxtools.bean;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一支付订单实体
 * @author antgan
 *
 */
@XStreamAlias("xml")
public class WxUnifiedOrder {
	@XStreamAlias("appid")
	private String appid;//公众账号ID
	
	@XStreamAlias("mch_id")
	private String mchId;//商户号
	
	@XStreamAlias("device_info")
	private String deviceInfo;//设备号
	
	@XStreamAlias("nonce_str")
	private String nonceStr;//随机字符串
	
	@XStreamAlias("sign")
	private String sign;//签名
	
	@XStreamAlias("body")
	private String body;//商品描述
	
	@XStreamAlias("detail")
	private String detail;//商品详情
	
	@XStreamAlias("attach")
	private String attach;//附加数据
	
	@XStreamAlias("out_trade_no")
	private String outTradeNo;//商户订单号
	
	@XStreamAlias("fee_type")
	private String feeType;//货币类型
	
	@XStreamAlias("total_fee")
	private String totalFee;//总金额
	
	@XStreamAlias("spbill_create_ip")
	private String spbillCreateIp;//终端IP
	
	@XStreamAlias("time_start")
	private String timeStart;//交易起始时间
	
	@XStreamAlias("time_expire")
	private String timeExpire;//交易结束时间
	
	@XStreamAlias("goods_tag")
	private String goodsTag;//商品标记
	
	@XStreamAlias("notify_url")
	private String notifyUrl;//通知地址
	
	@XStreamAlias("trade_type")
	private String tradeType;//交易类型
	
	@XStreamAlias("product_id")
	private String productId;//商品ID
	
	@XStreamAlias("limit_pay")
	private String limitPay;//指定支付方式
	
	@XStreamAlias("openid")
	private String openid;//用户标识
	
	
	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getMchId() {
		return mchId;
	}


	public void setMchId(String mchId) {
		this.mchId = mchId;
	}


	public String getDeviceInfo() {
		return deviceInfo;
	}


	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}


	public String getNonceStr() {
		return nonceStr;
	}


	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getAttach() {
		return attach;
	}


	public void setAttach(String attach) {
		this.attach = attach;
	}


	public String getOutTradeNo() {
		return outTradeNo;
	}


	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}


	public String getFeeType() {
		return feeType;
	}


	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}


	public String getTotalFee() {
		return totalFee;
	}


	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}


	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}


	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}


	public String getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}


	public String getTimeExpire() {
		return timeExpire;
	}


	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}


	public String getGoodsTag() {
		return goodsTag;
	}


	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}


	public String getNotifyUrl() {
		return notifyUrl;
	}


	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getLimitPay() {
		return limitPay;
	}


	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}


	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String toXml() {
		return XStreamTransformer.toXml((Class) this.getClass(), this);
	}
	
	public String toJson() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}
