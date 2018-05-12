package com.soecode.wxtools.bean;

import java.util.List;

public class SenderContent {
	private Media mpnews;
	private NewsList news;
	private Text text;
	private Media voice;
	private Media image;
	private Media mpvideo;
	private Media video;
	private Media music;

	public Media getMpnews() {
		return mpnews;
	}

	public void setMpnews(Media mpnews) {
		this.mpnews = mpnews;
	}

	public NewsList getNews() {
		return news;
	}

	public void setNews(NewsList news) {
		this.news = news;
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

	public Media getVideo() {
		return video;
	}

	public void setVideo(Media video) {
		this.video = video;
	}

	public Media getMusic() {
		return music;
	}

	public void setMusic(Media music) {
		this.music = music;
	}

	/**
	 * 媒体包括图文语音视频图片
	 * @author antgan
	 *
	 */
	public static class Media{
		private String media_id;
		private String thumb_media_id;
		private String title;
		private String description;
		private String musicurl;
		private String hqmusicurl;
		public Media() {
		}
		public Media(String media_id) {
			this.media_id = media_id;
		}

		public Media(String media_id, String thumb_media_id, String title, String description) {
			this.media_id = media_id;
			this.thumb_media_id = thumb_media_id;
			this.title = title;
			this.description = description;
		}

		public Media(String thumb_media_id, String title, String description, String musicurl,
				String hqmusicurl) {
			this.thumb_media_id = thumb_media_id;
			this.title = title;
			this.description = description;
			this.musicurl = musicurl;
			this.hqmusicurl = hqmusicurl;
		}

		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}

		public String getThumb_media_id() {
			return thumb_media_id;
		}

		public void setThumb_media_id(String thumb_media_id) {
			this.thumb_media_id = thumb_media_id;
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

		public String getMusicurl() {
			return musicurl;
		}

		public void setMusicurl(String musicurl) {
			this.musicurl = musicurl;
		}

		public String getHqmusicurl() {
			return hqmusicurl;
		}

		public void setHqmusicurl(String hqmusicurl) {
			this.hqmusicurl = hqmusicurl;
		}
	}

	public static class Text{
		private String content;
		public Text() {
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

	public static class NewsList{
		private List<News> articles;

		public NewsList(List<News> articles) {
			this.articles = articles;
		}

		public List<News> getArticles() {
			return articles;
		}

		public void setArticles(List<News> articles) {
			this.articles = articles;
		}

		public static class News{
			private String title;
			private String description;
			private String url;
			private String picurl;

			public News() {
			}

			public News(String title, String description, String url, String picurl) {
				this.title = title;
				this.description = description;
				this.url = url;
				this.picurl = picurl;
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

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getPicurl() {
				return picurl;
			}

			public void setPicurl(String picurl) {
				this.picurl = picurl;
			}
		}
	}
}

