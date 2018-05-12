package com.soecode.wxtools.bean;

import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.bean.outxmlbuilder.ImageBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.MusicBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.NewsBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.TextBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.VideoBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.VoiceBuilder;
import com.soecode.wxtools.exception.AesException;
import com.soecode.wxtools.util.crypto.WXBizMsgCrypt;
import com.soecode.wxtools.util.xml.XStreamCDataConverter;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("xml")
public abstract class WxXmlOutMessage {

	@XStreamAlias("ToUserName")
	@XStreamConverter(value = XStreamCDataConverter.class)
	protected String toUserName;

	@XStreamAlias("FromUserName")
	@XStreamConverter(value = XStreamCDataConverter.class)
	protected String fromUserName;

	@XStreamAlias("CreateTime")
	protected Long createTime;

	@XStreamAlias("MsgType")
	@XStreamConverter(value = XStreamCDataConverter.class)
	protected String msgType;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String toXml() {
		return XStreamTransformer.toXml((Class) this.getClass(), this);
	}

	public static String encryptMsg(WxConfig wxconfig, String replyMsg, String timeStamp, String nonce) throws AesException {
		WXBizMsgCrypt pc = new WXBizMsgCrypt(WxConfig.getInstance().getToken(), WxConfig.getInstance().getAesKey(), WxConfig.getInstance().getAppId());
		return pc.encryptMsg(replyMsg, timeStamp, nonce);
	}

	public static TextBuilder TEXT() {
		return new TextBuilder();
	}

	public static ImageBuilder IMAGE() {
		return new ImageBuilder();
	}

	public static VoiceBuilder VOICE() {
		return new VoiceBuilder();
	}

	public static VideoBuilder VIDEO() {
		return new VideoBuilder();
	}

	public static NewsBuilder NEWS() {
		return new NewsBuilder();
	}
	
	public static MusicBuilder MUSIC(){
		return new MusicBuilder();
	}

	@Override
	public String toString() {
		return "WxXmlOutMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + "]";
	}

}
