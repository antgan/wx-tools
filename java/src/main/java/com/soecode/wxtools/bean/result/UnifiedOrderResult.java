package com.soecode.wxtools.bean.result;

import com.soecode.wxtools.util.xml.XStreamCDataConverter;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("xml")
public class UnifiedOrderResult {
	@XStreamAlias("return_code")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String returnCode;
	
	@XStreamAlias("return_msg")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String returnMsg;
	
	@XStreamAlias("appid")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String appid;
	
	@XStreamAlias("mch_id")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String mchId;
	
	@XStreamAlias("nonce_str")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String nonceStr;
	
	@XStreamAlias("openid")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String openid;
	
	@XStreamAlias("sign")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String sign;
	
	@XStreamAlias("result_code")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String resultCode;
	
	@XStreamAlias("err_code")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String errCode;
	
	@XStreamAlias("err_code_des")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String errCodeDes;
	
	@XStreamAlias("prepay_id")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String prepayId;
	
	@XStreamAlias("trade_type")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String tradeType;
	
	@XStreamAlias("code_url")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String codeUrl;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

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

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public static UnifiedOrderResult fromXml(String xml) {
		return XStreamTransformer.fromXml(UnifiedOrderResult.class, xml);
	}
	
}
