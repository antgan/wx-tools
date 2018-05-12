package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.PreviewSender;
import com.soecode.wxtools.bean.SenderContent.Media;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.result.SenderResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MessageTest {

  IService iService = new WxService();

  @Test
  public void should_preview_send_news_to_user() throws Exception {
    PreviewSender sender = new PreviewSender();
    sender.setTouser("oROCnuNihJnO9bnKOAORDFFriPgQ");
    sender.setMsgtype(WxConsts.MASS_MSG_MPNEWS);
    sender.setMpnews(new Media("QR3FgphTwoIpP1FZ-4c__cQTEeIHxMl7e_rWAfFYyfo"));
    SenderResult result = iService.sendAllPreview(sender);
    System.out.println(result.toString());
  }

  @Test
  public void should_send_news_to_user() throws Exception {
    WxOpenidSender sender = new WxOpenidSender();
    List<String> openidList = new ArrayList<>();
    openidList.add("oROCnuNihJnO9bnKOAORDFFriPgQ");
    openidList.add("oROCnuAQMnkPpEhsAYFzU-1xhKcQ");
    sender.setTouser(openidList);
    sender.setMsgtype(WxConsts.MASS_MSG_MPNEWS);
    sender.setMpnews(new Media("QR3FgphTwoIpP1FZ-4c__cQTEeIHxMl7e_rWAfFYyfo"));
    SenderResult result = iService.sendAllByOpenid(sender);
    System.out.println(result.toString());
  }
}
