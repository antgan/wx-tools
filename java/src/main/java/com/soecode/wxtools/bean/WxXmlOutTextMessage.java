package com.soecode.wxtools.bean;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.util.xml.XStreamCDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("xml")
public class WxXmlOutTextMessage extends WxXmlOutMessage {

	@XStreamAlias("Content")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String content;

	public WxXmlOutTextMessage() {
		this.msgType = WxConsts.XML_MSG_TEXT;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "WxXmlOutTextMessage [content=" + content + "]";
	}

	
}
