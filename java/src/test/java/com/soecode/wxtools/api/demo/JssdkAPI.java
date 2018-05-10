package com.soecode.wxtools.api.demo;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.exception.WxErrorException;
import org.junit.Ignore;

/**
 * Js SDK API示例<br>
 * 这里仅仅展示如何获取js sdk config。真正调用的js并不展示。<br>
 * 详情参考官方文档
 * @author antgan
 *
 */
@Ignore
public class JssdkAPI {
	IService iService = new WxService();
	/**
	 * 获取JSSDK 中config的配置
	 */
	public void createJsapiConfig(){
		List<String> jsApiList = new ArrayList<>();
		//需要用到哪些JS SDK API 就设置哪些
		jsApiList.add("chooseImage");//拍照或从手机相册中选图接口
		jsApiList.add("onMenuShareQZone");//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口

		try {
			//把config返回到前端进行js调用即可。
			WxJsapiConfig config = iService.createJsapiConfig("调用jssdk的完整url", jsApiList);
			System.out.println(config.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
