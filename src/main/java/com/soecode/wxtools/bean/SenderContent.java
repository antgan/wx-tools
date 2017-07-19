package com.soecode.wxtools.bean;

/**
 * 群发内容
 * @author antgan
 *
 */
public class SenderContent {
	private Media mpnews;
	private Text text;
	private Media voice;
	private Media image;
	private Media mpvideo;
	
	public Media getMpnews() {
		return mpnews;
	}
	public void setMpnews(Media mpnews) {
		this.mpnews = mpnews;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public Media getVoice() {
		return voice;
	}
	public void setVoice(Media voice) {
		this.voice = voice;
	}
	public Media getImage() {
		return image;
	}
	public void setImage(Media image) {
		this.image = image;
	}
	public Media getMpvideo() {
		return mpvideo;
	}
	public void setMpvideo(Media mpvideo) {
		this.mpvideo = mpvideo;
	}

	/**
	 * 媒体包括图文语音视频图片
	 * @author antgan
	 *
	 */
	public static class Media{
		private String media_id;
		public Media() {
			// TODO Auto-generated constructor stub
		}
		public Media(String media_id) {
			this.media_id = media_id;
		}
		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		
		
	}

	public static class Text{
		private String content;
		public Text() {
			// TODO Auto-generated constructor stub
		}
		public Text(String content){
			this.content = content;
		}
		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
	}
}

