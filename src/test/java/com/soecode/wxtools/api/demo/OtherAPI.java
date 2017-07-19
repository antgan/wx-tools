package com.soecode.wxtools.api.demo;

import java.io.File;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxQrcode;
import com.soecode.wxtools.bean.WxQrcode.WxQrActionInfo;
import com.soecode.wxtools.bean.WxQrcode.WxQrActionInfo.WxScene;
import com.soecode.wxtools.bean.result.QrCodeResult;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 另外一些API调用示例，如二维码生产等
 * @author antgan
 *
 */
public class OtherAPI {
	IService iService = new WxService(WxConfig.getInstance("appId", "appSecret", "token", "aesKey", null, null));
	/**
	 * 生产二维码
	 */
	public void createQrCode(){
		WxQrcode code = new WxQrcode();
		code.setAction_name("actionName");
		code.setAction_info(new WxQrActionInfo(new WxScene("scene_id/str")));
		code.setExpire_seconds(720);
		try {
			QrCodeResult result = iService.createQrCode(code);
			System.out.println(result.getUrl());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载二维码，需要用到createQrCode中的ticket
	 */
	public void downloadQrCode(){
		try {
			File file = iService.downloadQrCode(new File("E://temp"), "ticket");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 长链接变短链接
	 */
	public void getShortUrl(){
		try {
			String shortUrl = iService.getShortUrl("long_url");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取微信服务器的ip段
	 */
	public void getCallbackIp(){
		try {
			String [] ipList = iService.getCallbackIp();
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
