package com.soecode.wxtools.bean.result;

/**
 * 【永久】视频素材下载结果
 * @author antgan
 *
 */
public class WxVideoMediaResult {
	private String title;
	private String description;
	private String down_url;
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
	public String getDown_url() {
		return down_url;
	}
	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}
	@Override
	public String toString() {
		return "WxVideoMediaResult [title=" + title + ", description=" + description + ", down_url=" + down_url + "]";
	}
	
	
}
