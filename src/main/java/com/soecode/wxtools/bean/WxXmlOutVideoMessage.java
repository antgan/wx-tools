package com.soecode.wxtools.bean;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.util.xml.XStreamCDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * <pre>
 * 被动回复消息--回复视频消息体
 * 
 * 详情:http://mp.weixin.qq.com/wiki/1/6239b44c206cab9145b1d52c67e6c551.html
 * </pre>
 * @author antgan
 *
 */
@XStreamAlias("xml")
public class WxXmlOutVideoMessage extends WxXmlOutMessage {

	@XStreamAlias("Video")
	protected final Video video = new Video();

	public WxXmlOutVideoMessage() {
		this.msgType = WxConsts.XML_MSG_VIDEO;
	}

	public String getMediaId() {
		return video.getMediaId();
	}

	public void setMediaId(String mediaId) {
		video.setMediaId(mediaId);
	}

	public String getTitle() {
		return video.getTitle();
	}

	public void setTitle(String title) {
		video.setTitle(title);
	}

	public String getDescription() {
		return video.getDescription();
	}

	public void setDescription(String description) {
		video.setDescription(description);
	}

	@XStreamAlias("Video")
	public static class Video {

		@XStreamAlias("MediaId")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String mediaId;

		@XStreamAlias("Title")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String title;

		@XStreamAlias("Description")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String description;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}
