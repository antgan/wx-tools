package com.soecode.wxtools.bean;

import com.soecode.wxtools.api.WxConfigStorage;
import com.soecode.wxtools.bean.outxmlbuilder.ImageBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.MusicBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.NewsBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.TextBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.VideoBuilder;
import com.soecode.wxtools.bean.outxmlbuilder.VoiceBuilder;
import com.soecode.wxtools.util.crypto.WxCpCryptUtil;
import com.soecode.wxtools.util.xml.XStreamCDataConverter;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
/**
 * <pre>
 * 被动回复消息--消息体超类
 * 
 * 详情:http://mp.weixin.qq.com/wiki/1/6239b44c206cab9145b1d52c67e6c551.html
 * </pre>
 * @author antgan
 *
 */

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

	/**
	 * 转换成加密的xml格式
	 * //TODO
	 * @return
	 */
	public String toEncryptedXml(WxConfigStorage wxConfigStorage) {
		String plainXml = toXml();
		WxCpCryptUtil pc = new WxCpCryptUtil(wxConfigStorage);
		return pc.encrypt(plainXml);
	}

	/**
	 * 获得文本消息builder
	 * 
	 * @return
	 */
	public static TextBuilder TEXT() {
		return new TextBuilder();
	}

	/**
	 * 获得图片消息builder
	 * 
	 * @return
	 */
	public static ImageBuilder IMAGE() {
		return new ImageBuilder();
	}

	/**
	 * 获得语音消息builder
	 * 
	 * @return
	 */
	public static VoiceBuilder VOICE() {
		return new VoiceBuilder();
	}

	/**
	 * 获得视频消息builder
	 * 
	 * @return
	 */
	public static VideoBuilder VIDEO() {
		return new VideoBuilder();
	}

	/**
	 * 获得图文消息builder
	 * 
	 * @return
	 */
	public static NewsBuilder NEWS() {
		return new NewsBuilder();
	}
	
	/**
	 * 获取音乐消息builder
	 * @return
	 */
	public static MusicBuilder MUSIC(){
		return new MusicBuilder();
	}

	@Override
	public String toString() {
		return "WxXmlOutMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + "]";
	}

}
