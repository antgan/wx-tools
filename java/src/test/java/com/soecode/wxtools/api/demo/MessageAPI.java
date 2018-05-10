package com.soecode.wxtools.api.demo;

import java.util.ArrayList;
import java.util.List;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.PreviewSender;
import com.soecode.wxtools.bean.SenderContent.Media;
import com.soecode.wxtools.bean.SenderContent.Text;
import com.soecode.wxtools.bean.SenderFilter;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.WxTagSender;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.result.IndustryResult;
import com.soecode.wxtools.bean.result.SenderResult;
import com.soecode.wxtools.bean.result.TemplateListResult;
import com.soecode.wxtools.bean.result.TemplateResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.exception.WxErrorException;
import org.junit.Ignore;

/**
 * 关于消息的API使用示例
 * @author antgan
 *
 */
@Ignore
public class MessageAPI {
	IService iService =  new WxService();

	/**
	 * 通过用户组来群发
	 */
	public void sendAllByGroup(){
		WxTagSender sender = new WxTagSender();
		//设置哪些组需要接受群发
		sender.setFilter(new SenderFilter(true, 1));
		//群发文本内容
		sender.setText(new Text("文本内容"));
		//群发图片，以此类推
		sender.setImage(new Media("media_id"));
		try {
			SenderResult result = iService.sendAllByTag(new WxTagSender());
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 针对某群人的openid群发
	 */
	public void sendAllByOpenid(){
		WxOpenidSender sender = new WxOpenidSender();
		List<String> openidList = new ArrayList<>();
		openidList.add("oROCnuNihJnO9bnKOAORDFFriPgQ");
		openidList.add("oROCnuNihJnO9bnKOAORDFFriPgQ");
		sender.setTouser(openidList);
		//群发文本内容
		sender.setText(new Text("文本内容12"));
		sender.setMsgtype(WxConsts.SEND_ALL_TEXT);
		//群发图片，以此类推
//		sender.setImage(new Media("media_id"));
		try {
			SenderResult result = iService.sendAllByOpenid(sender);
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 群发预览
	 */
	public void sendAllPreview(){
		PreviewSender sender = new PreviewSender();
		//设置openid或者微信号，优先级为wxname高
		sender.setTouser("oROCnuNihJnO9bnKOAORDFFriPgQ");
		sender.setTowxname("微信号");
		//群发文本内容
		sender.setText(new Text("文本内容"));
		sender.setMsgtype(WxConsts.SEND_ALL_TEXT);
		//群发图片，以此类推
		sender.setImage(new Media("media_id"));
		try {
			SenderResult result = iService.sendAllPreview(sender);
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 删除群发
	 */
	public void sendAllDelete(){
		
		try {
			//此参数在发送接口 返回
			SenderResult result = iService.sendAllDelete("msg_id");
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
	
	/**
	 * 设置模板消息的行业
	 */
	public void templateSetIndustry(){
		//行业代码参考官方文档。
		try {
			iService.templateSetIndustry("1", "4");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取模板消息的行业设置
	 */
	public void templateGetIndustry(){
		try {
			IndustryResult result = iService.templateGetIndustry();
			System.out.println(result.getPrimary_industry());
			System.out.println(result.getSecondary_industry());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过短ID获取模板ID
	 */
	public void templateGetId(){
		//模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
		try {
			TemplateResult result = iService.templateGetId("template_id_short");
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取模板列表
	 */
	public void templateGetList(){
		try {
			TemplateListResult result = iService.templateGetList();
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除模板
	 */
	public void templateDelete(){
		try {
			iService.templateDelete("template_id");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 模板消息发送
	 */
	public void templateSend(){
		TemplateSender sender = new TemplateSender();
		sender.setTouser("openid");
		sender.setTemplate_id("templateId");
		sender.setData("Object：与模板内容对应的对象");
		sender.setUrl("url");
		try {
			TemplateSenderResult result = iService.templateSend(sender);
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
