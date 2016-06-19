package com.soecode.wxtools.bean;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.util.xml.XStreamCDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * <pre>
 * 被动回复消息--回复图文消息体
 * 
 * 详情:http://mp.weixin.qq.com/wiki/1/6239b44c206cab9145b1d52c67e6c551.html
 * </pre>
 * @author antgan
 *
 */
@XStreamAlias("xml")
public class WxXmlOutNewsMessage extends WxXmlOutMessage {

	@XStreamAlias("ArticleCount")
	protected int articleCount;

	@XStreamAlias("Articles")
	protected final List<Item> articles = new ArrayList<Item>();

	public WxXmlOutNewsMessage() {
		this.msgType = WxConsts.XML_MSG_NEWS;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void addArticle(Item item) {
		this.articles.add(item);
		this.articleCount = this.articles.size();
	}

	public List<Item> getArticles() {
		return articles;
	}

	@XStreamAlias("item")
	public static class Item {

		@XStreamAlias("Title")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String Title;

		@XStreamAlias("Description")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String Description;

		@XStreamAlias("PicUrl")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String PicUrl;

		@XStreamAlias("Url")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String Url;

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getDescription() {
			return Description;
		}

		public void setDescription(String description) {
			Description = description;
		}

		public String getPicUrl() {
			return PicUrl;
		}

		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}

		public String getUrl() {
			return Url;
		}

		public void setUrl(String url) {
			Url = url;
		}

	}

}
