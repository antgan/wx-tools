package com.soecode.wxtools.bean.msgbuilder;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxMessage;

/**
 * 文本消息builder
 * 
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.TEXT().content(...).toUser(...).build();
 * </pre>
 * 
 * @author antgan
 *
 */
public final class TextBuilder extends BaseBuilder<TextBuilder> {
	private String content;

	public TextBuilder() {
		this.msgType = WxConsts.CUSTOM_MSG_TEXT;
	}

	public TextBuilder content(String content) {
		this.content = content;
		return this;
	}

	public WxMessage build() {
		WxMessage m = super.build();
		m.setContent(this.content);
		return m;
	}
}
