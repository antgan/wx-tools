package com.soecode.wxtools.api.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.PreviewSender;
import com.soecode.wxtools.bean.SenderContent.Media;
import com.soecode.wxtools.bean.SenderContent.Text;
import com.soecode.wxtools.bean.SenderFilter;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.WxGroupSender;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.WxVideoIntroduction;
import com.soecode.wxtools.bean.result.IndustryResult;
import com.soecode.wxtools.bean.result.SenderResult;
import com.soecode.wxtools.bean.result.TemplateListResult;
import com.soecode.wxtools.bean.result.TemplateResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.bean.result.WxBatchGetMaterialResult;
import com.soecode.wxtools.bean.result.WxBatchGetMaterialResult.MaterialItem;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.bean.result.WxNewsMediaResult;
import com.soecode.wxtools.bean.result.WxVideoMediaResult;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 关于消息的API使用示例
 * @author antgan
 *
 */
public class WxMpAPI {
	IService iService =  new WxService(WxConfig.getInstance("wxbc3a29333c9c965e", "4ac85c686671d8a05b739c73f616069b", "xerllent", null, null, null));
	
	/**
	 * 上传临时文件到微信服务器
	 */
	public void uploadTempMedia(){
		//可以上传file或者InputSteam，拿到MediaID
		try {
			WxMediaUploadResult result = iService.uploadTempMedia(WxConsts.MEDIA_IMAGE,new File("D://work/test/001.jpg"));
			System.out.println(result.getMedia_id());
			
			File file = iService.downloadTempMedia(result.getMedia_id(),new File("D://work//test//a"));
			
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传永久文件到微信服务器。可以传File或者输入流
	 */
	public void uploadMedia(){
		try {
			//这里注意，如果是上传非视频格式的素材，第三个参数(WxVideoIntroduction)为null即可
			WxMediaUploadResult result1 = iService.uploadMedia(WxConsts.MEDIA_IMAGE,new File("D://work/test/001.jpg"), null);
			
			//如果是上传视频Video，可以添加描述
//			WxVideoIntroduction intro = new WxVideoIntroduction();
//			intro.setTitle("视频1");
//			intro.setIntroduction("描述1");
//			WxMediaUploadResult result2 = iService.uploadMedia(WxConsts.MEDIA_VIDEO, new File("E://test.mp4"), intro);
			
			
			batchGetMeterial();
			
			File file = iService.downloadMedia(result1.getMedia_id(),new File("D://work//test//a"));
			
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void test1(){
		
	}
	
	
	/**
	 *  批量获取永久素材资源信息
	 */
	public void batchGetMeterial(){
		try {
			WxBatchGetMaterialResult result = iService.batchGetMeterial(WxConsts.MEDIA_IMAGE, 0, 5);
			for(MaterialItem item:result.getItem()){
				System.out.println("getMedia_id:"+item.getMedia_id());
				System.out.println("getName:"+item.getName());
				System.out.println("getUpdate_time:"+item.getUpdate_time());
				System.out.println("getUrl:"+item.getUrl());
				System.out.println("getContent:"+item.getContent());
			}
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 群发消息测试
	 * */
	public void sendGroupMsg(){
		try {
			//第1步 可以上传file或者InputSteam，拿到MediaID
			WxMediaUploadResult result = iService.uploadMedia(WxConsts.MEDIA_IMAGE,new File("D://work/test/001.jpg"), null);
			String media_id=result.getMedia_id();

			
			/*//第2步 制作新闻mediaid
			WxNewsInfo news1 = new WxNewsInfo();
			news1.setTitle("标题1标题1标题1标题1标题1标题1标题1");
			news1.setThumb_media_id(media_id);
			news1.setContent("简介1简介1简介1简介1简介1简介1简介1简介1简介1");
			//....设置图文内容
			WxNewsInfo news2 = new WxNewsInfo();
			news2.setTitle("标题2标题2标题2标题2标题2标题2标题2");
			news2.setThumb_media_id(media_id);
			news2.setContent("简介2简介2简介2简介2简介2简介2简介2简介2");
			
			List<WxNewsInfo> newsList = new ArrayList<>();
			newsList.add(news1);
			newsList.add(news2);
			
			String newsmediaId = iService.addNewsMedia(newsList);*/
			
			//3群发 群发消息
			WxGroupSender sender = new WxGroupSender();
			//设置哪些组需要接受群发
			sender.setFilter(new SenderFilter(true, 1));
			//群发文本内容
//			sender.setText(new Text("文本内容"));
//			sender.setMsgtype(WxConsts.SEND_ALL_TEXT);
			
			
			//群发图片，以此类推
			sender.setImage(new Media(media_id));
			sender.setMsgtype(WxConsts.SEND_ALL_IMAGE);
			
			//群发图文消息
			//sender.setMpnews(new Media(newsmediaId));
			//sender.setMsgtype(WxConsts.SEND_ALL_NEWS);
			
			
			SenderResult result2 = iService.sendAllByGroup(sender);
			System.out.println(result2.toString());
		
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 图文相关API
	 * @author antgan
	 *
	 */
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
	 * 下载永久文件，与临时文件一样用法（注意：图文和视频需要使用另外的方法）
	 */
	public void downloadMedia(){
		try {
			File file = iService.downloadMedia("media_id",new File("E://temp"));
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载图文素材
	 */
	public void downloadNewsMedia(){
		try {
			//图文结果
			WxNewsMediaResult result = iService.downloadNewsMedia("media_id");
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载视频素材
	 */
	public void downloadVideoMedia(){
		try {
			//视频结果，取出URL即可下载
			WxVideoMediaResult result = iService.downloadVideoMedia("media_id", new File("E://temp"));
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 通过用户组来群发
	 */
	public void sendAllByGroup(){
		WxGroupSender sender = new WxGroupSender();
		//设置哪些组需要接受群发
		sender.setFilter(new SenderFilter(true, 1));
		//群发文本内容
		sender.setText(new Text("文本内容"));
		//群发图片，以此类推
		sender.setImage(new Media("media_id"));
		try {
			SenderResult result = iService.sendAllByGroup(sender);
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取群发状态
	 */
	public void sendAllGetStatus(){
		try {
			SenderResult result = iService.sendAllGetStatus("msg_id");
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
