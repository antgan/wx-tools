package com.soecode.wxtools.api.demo;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;
import org.junit.Ignore;

/**
 * 图文相关API
 * @author antgan
 *
 */
@Ignore
public class NewsAPI {
	IService iService = new WxService();
	
	public void addNewsMedia(){
		
		WxNewsInfo news1 = new WxNewsInfo();
		news1.setTitle("标题1");
		news1.setThumb_media_id("图片media_id");
		news1.setContent("xxx");
		//....设置图文内容
		WxNewsInfo news2 = new WxNewsInfo();
		news2.setTitle("标题1");
		news2.setThumb_media_id("图片media_id");
		news2.setContent("xxx");
		
		List<WxNewsInfo> newsList = new ArrayList<>();
		newsList.add(news1);
		newsList.add(news2);
		
		try {
			String mediaId = iService.addNewsMedia(newsList);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改图文内容
	 */
	public void updateNewsInfo(){
		WxNewsInfo news1 = new WxNewsInfo();
		news1.setTitle("标题1");
		news1.setThumb_media_id("图片media_id");
		news1.setContent("xxx");
		try {
			//参数1：media_id；参数2：图文位置下标。从0开始；参数3：新的图文对象
			WxError result = iService.updateNewsInfo("media_id", 0, news1);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
