package com.soecode.wxtools.bean;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.SenderContent.NewsList.News;
import java.io.IOException;
import java.util.Arrays;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class KfSender extends SenderContent {
  private String touser;
  private String msgtype;

  public String getTouser() {
    return touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getMsgtype() {
    return msgtype;
  }

  public void setMsgtype(String msgtype) {
    this.msgtype = msgtype;
  }


  public String toJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Inclusion.NON_NULL);
    return mapper.writeValueAsString(this);
  }
}
