package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MediaTest {

  IService iService = new WxService();

  @Test
  public void should_upload_tmp_media_successfully() throws Exception {
    WxMediaUploadResult result = iService.uploadTempMedia(WxConsts.MEDIA_IMAGE, new File("D:/wx/wx.jpg"));
    System.out.println(result);
  }

  @Test
  public void should_download_tmp_media_successfully() throws Exception {
    File file = iService.downloadTempMedia("kQG6o5oSz7i-lftsReydIlrF9B3DeplO-MtunUwIY3SIUwje0PGp_VqozvrgUwRS", new File("D:/wx/"));
    System.out.println(file);
  }

  @Test
  public void should_upload_media_successfully() throws Exception {
    WxMediaUploadResult result = iService.uploadMedia(WxConsts.MEDIA_IMAGE, new File("D:/wx/20180517141550.jpg"), null);
    System.out.println(result);
  }

  @Test
  public void should_upload_news_media_successfully() throws Exception {
    WxNewsInfo news1 = new WxNewsInfo();
    news1.setTitle("标题1");
    news1.setThumb_media_id("QR3FgphTwoIpP1FZ-4c__U8tBWTHchoDa468te3P7Qg");
    news1.setContent("xxx");
    news1.setNeed_open_comment(1);

    List<WxNewsInfo> newsList = new ArrayList<WxNewsInfo>();
    newsList.add(news1);
    String mediaId = iService.addNewsMedia(newsList);
    System.out.println(mediaId);

  }
}
