package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * <pre>
 * 获取当前公众号 菜单配置 返回结果
 * 
 * 详情：http://mp.weixin.qq.com/wiki/14/293d0cb8de95e916d1216a33fcb81fd6.html
 * </pre>
 * @author antgan
 *
 */
public class WxCurMenuInfoResult {
	private int is_menu_open;
	private WxCurSelfMenuInfo selfmenu_info;

	public int getIs_menu_open() {
		return is_menu_open;
	}

	public void setIs_menu_open(int is_menu_open) {
		this.is_menu_open = is_menu_open;
	}

	public WxCurSelfMenuInfo getSelfmenu_info() {
		return selfmenu_info;
	}

	public void setSelfmenu_info(WxCurSelfMenuInfo selfmenu_info) {
		this.selfmenu_info = selfmenu_info;
	}
	
	/**
	 * obj --> json
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

	/**
	 * json --> obj
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static WxCurMenuInfoResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxCurMenuInfoResult.class);
	}
	
	

	@Override
	public String toString() {
		return "WxCurMenuInfoResult [is_menu_open=" + is_menu_open + ", selfmenu_info=" + selfmenu_info + "]";
	}

	/**
	 * 菜单栏信息
	 * @author antgan
	 *
	 */
	public static class WxCurSelfMenuInfo{
		private List<WxCurMenuButtonInfo> button = new ArrayList<>();

		public List<WxCurMenuButtonInfo> getButton() {
			return button;
		}

		public void setButton(List<WxCurMenuButtonInfo> button) {
			this.button = button;
		}

		@Override
		public String toString() {
			return "WxCurSelfMenuInfo [button=" + button + "]";
		}
		
	}
	
	/**
	 * 菜单栏按钮结果
	 * @author antgan
	 *
	 */
	public static class WxCurMenuButtonInfo {
		private String type;
		private String name;
		private String key;
		private String url;
		private String value;
		private WxCurMenuNews news_info;
		private WxCurMenuButton sub_button;
		
		public WxCurMenuButton getSub_button() {
			return sub_button;
		}
		public void setSub_button(WxCurMenuButton sub_button) {
			this.sub_button = sub_button;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public WxCurMenuNews getNews_info() {
			return news_info;
		}
		public void setNews_info(WxCurMenuNews news_info) {
			this.news_info = news_info;
		}
		@Override
		public String toString() {
			return "WxCurMenuButtonInfo [type=" + type + ", name=" + name + ", key=" + key + ", url=" + url + ", value="
					+ value + ", news_info=" + news_info + ", sub_button=" + sub_button + "]";
		}
		
	}
	
	/**
	 * 按钮
	 * @author antgan
	 *
	 */
	public static class WxCurMenuButton{
		private List<WxCurMenuButtonInfo> list = new ArrayList<WxCurMenuButtonInfo>();
		public List<WxCurMenuButtonInfo> getList() {
			return list;
		}
		public void setList(List<WxCurMenuButtonInfo> list) {
			this.list = list;
		}
		@Override
		public String toString() {
			return "WxCurMenuButton [list=" + list + "]";
		}
		
	}
	
	/**
	 * 图文
	 * @author antgan
	 *
	 */
	public static class WxCurMenuNews{
		private List<WxCurMenuNewsInfo> list = new ArrayList<>();
		public List<WxCurMenuNewsInfo> getList() {
			return list;
		}
		public void setList(List<WxCurMenuNewsInfo> list) {
			this.list = list;
		}
		@Override
		public String toString() {
			return "WxCurMenuNews [list=" + list + "]";
		}
		
	}
	
	/**
	 * 图文信息结果
	 * @author antgan
	 *
	 */
	public static class WxCurMenuNewsInfo{
		private String title;//图文消息的标题
		private String author;//作者
		private String digest;//摘要
		private int show_cover;//是否显示封面，0为不显示，1为显示
		private String cover_url;//封面图片的URL
		private String content_url;//正文的URL
		private String source_url;//原文的URL，若置空则无查看原文入口
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
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
		public int getShow_cover() {
			return show_cover;
		}
		public void setShow_cover(int show_cover) {
			this.show_cover = show_cover;
		}
		public String getCover_url() {
			return cover_url;
		}
		public void setCover_url(String cover_url) {
			this.cover_url = cover_url;
		}
		public String getContent_url() {
			return content_url;
		}
		public void setContent_url(String content_url) {
			this.content_url = content_url;
		}
		public String getSource_url() {
			return source_url;
		}
		public void setSource_url(String source_url) {
			this.source_url = source_url;
		}
		@Override
		public String toString() {
			return "WxCurMenuNewsInfo [title=" + title + ", author=" + author + ", digest=" + digest + ", show_cover="
					+ show_cover + ", cover_url=" + cover_url + ", content_url=" + content_url + ", source_url="
					+ source_url + "]";
		}
		
	}
	
}
