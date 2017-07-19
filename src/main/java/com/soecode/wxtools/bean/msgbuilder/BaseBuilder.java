package com.soecode.wxtools.bean.msgbuilder;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxMessage;

public class BaseBuilder<T> {
	protected String msgType;
	protected String agentId;
	protected String toUser;
	protected String toParty;
	protected String toTag;
	protected String safe;

	public T agentId(String agentId) {
		this.agentId = agentId;
		return (T) this;
	}

	public T toUser(String toUser) {
		this.toUser = toUser;
		return (T) this;
	}

	public T toParty(String toParty) {
		this.toParty = toParty;
		return (T) this;
	}

	public T toTag(String toTag) {
		this.toTag = toTag;
		return (T) this;
	}

	public T safe(String safe) {
		this.safe = safe;
		return (T) this;
	}

	public WxMessage build() {
		WxMessage m = new WxMessage();
		m.setAgentId(this.agentId);
		m.setMsgType(this.msgType);
		m.setToUser(this.toUser);
		m.setToParty(this.toParty);
		m.setToTag(this.toTag);
		m.setSafe((this.safe == null || "".equals(this.safe)) ? WxConsts.CUSTOM_MSG_SAFE_NO : this.safe);
		return m;
	}

}
