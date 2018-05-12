package com.soecode.wxtools.bean;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class PreviewSender extends SenderContent {

  private String touser;
  private String towxname;
  private String msgtype;

  public String getTouser() {
    return touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getTowxname() {
    return towxname;
  }

  public void setTowxname(String towxname) {
    this.towxname = towxname;
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
