package com.soecode.wxtools.api.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.SenderContent.Media;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.WxVideoIntroduction;
import com.soecode.wxtools.bean.result.SenderResult;
import com.soecode.wxtools.bean.result.WxBatchGetMaterialResult;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxMaterialCountResult;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.bean.result.WxNewsMediaResult;
import com.soecode.wxtools.bean.result.WxVideoMediaResult;
import com.soecode.wxtools.exception.WxErrorException;
import org.junit.Ignore;

/**
 * 上传相关API示例
 * @author antgan
 *
 */
@Ignore
public class UploadMediaAPI {

	IService iService = new WxService();
	
	/**
	 * 上传临时文件到微信服务器
	 */
	public void uploadTempMedia(){
		//可以上传file或者InputSteam，拿到MediaID
		try {
			WxMediaUploadResult result = iService.uploadTempMedia(WxConsts.MEDIA_IMAGE,new File("E://test.png"));
			System.out.println(result.getMedia_id());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 下载临时文件，存在E://temp文件夹
	 */
	public void downloadTempMedia(){
		try {
			File file = iService.downloadTempMedia("media_id",new File("E://temp"));
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
			WxMediaUploadResult result1 = iService.uploadMedia(WxConsts.MEDIA_VOICE, new File("E://test.m4a"), null);
			
			//如果是上传视频Video，可以添加描述
			WxVideoIntroduction intro = new WxVideoIntroduction();
			intro.setTitle("视频1");
			intro.setIntroduction("描述1");
			WxMediaUploadResult result2 = iService.uploadMedia(WxConsts.MEDIA_VIDEO, new File("E://test.mp4"), intro);
			
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
	 * 删除素材资源
	 */
	public void deleteMediaMaterial(){
		try {
			WxError result = iService.deleteMediaMaterial("media_id");
			System.out.println(result.getErrcode());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传图片变成腾讯域名下的图片
	 */
	public void imageDomainChange(){
		try {
			WxMediaUploadResult result = iService.imageDomainChange(new File("E://test.jpg"));
			System.out.println(result.getUrl());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取永久素材数量接口
	 */
	public void getMaterialCount(){
		try {
			WxMaterialCountResult result = iService.getMaterialCount();
			System.out.println(result.getImage_count());
			System.out.println(result.getNews_count());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  批量获取永久素材资源信息
	 */
	public void batchGetMeterial(){
		try {
			WxBatchGetMaterialResult result = iService.batchGetMeterial(WxConsts.MEDIA_IMAGE, 0, 5);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
