package com.soecode.wxtools.bean;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * <pre>
 * 图文信息
 * 
 * 各个字段意思查阅官方文档
 * 详情：http://mp.weixin.qq.com/wiki/10/10ea5a44870f53d79449290dfd43d006.html
 * </pre>
 * 
 * @author antgan
 *
 */
public class WxNewsInfo {
	private String title;
	private String thumb_media_id;
	private String thumb_url;
	private int show_cover_pic;
	private String author;
	private String digest;
	private String content;
	private String url;
	private String content_source_url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public int getShow_cover_pic() {
		return show_cover_pic;
	}

	public void setShow_cover_pic(int show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent_source_url() {
		return content_source_url;
	}

	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public String toJson() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper  = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
	@Override
	public String toString() {
		return "WxNewsInfo [title=" + title + ", thumb_media_id=" + thumb_media_id + ", thumb_url=" + thumb_url
				+ ", show_cover_pic=" + show_cover_pic + ", author=" + author + ", digest=" + digest + ", content="
				+ content + ", url=" + url + ", content_source_url=" + content_source_url + "]";
	}
}