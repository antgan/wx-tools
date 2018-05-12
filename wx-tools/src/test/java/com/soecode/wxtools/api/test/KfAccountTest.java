package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.KfAccount;
import com.soecode.wxtools.bean.KfSender;
import com.soecode.wxtools.bean.SenderContent.NewsList;
import com.soecode.wxtools.bean.SenderContent.NewsList.News;
import com.soecode.wxtools.bean.SenderContent.Text;
import com.soecode.wxtools.bean.result.KfAccountListResult;
import com.soecode.wxtools.bean.result.WxError;
import java.io.File;
import java.util.Arrays;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class KfAccountTest {
  IService iService = new WxService();

  @Test
  public void should_add_kf_account_successfully() throws Exception {
    WxError result = iService.addKfAccount(new KfAccount("ant@test", "ant", "ant"));
    System.out.println(result);
  }

  @Test
  public void should_update_kf_account_successfully() throws Exception {
    WxError result = iService.updateKfAccount(new KfAccount("ant@test", "ant", "ant"));
    System.out.println(result);
  }

  @Test
  public void should_delete_kf_account_successfully() throws Exception {
    WxError result = iService.deleteKfAccount(new KfAccount("ant@test", "ant", "ant"));
    System.out.println(result);
  }

  @Test
  public void should_get_all_kf_account_successfully() throws Exception {
    KfAccountListResult result = iService.getAllKfAccount();
    System.out.println(result);
  }

  @Test
  public void should_send_text_message_to_user_successfully() throws Exception {
    KfSender sender = new KfSender();
    sender.setTouser("oROCnuNihJnO9bnKOAORDFFriPgQ");
    sender.setMsgtype(WxConsts.MASS_MSG_TEXT);
    sender.setText(new Text("hi"));
    System.out.println(iService.sendMessageByKf(sender));
  }

  @Test
  public void should_send_news_message_to_user_successfully() throws Exception {
    KfSender sender = new KfSender();
    sender.setTouser("oROCnuNihJnO9bnKOAORDFFriPgQ");
    sender.setMsgtype(WxConsts.MASS_MSG_NEWS);
    sender.setNews(new NewsList(Arrays.asList(new News("title","desc","http://www.baidu.com","http://www.baidu.com"))));
    System.out.println(iService.sendMessageByKf(sender));
  }

  @Test
  public void should_update_kf_head_image_successfully() throws Exception {
    System.out.println(iService.updateKfHeadImage("oROCnuNihJnO9bnKOAORDFFriPgQ", new File("D:/wx/wx.jpg")));
  }
}
