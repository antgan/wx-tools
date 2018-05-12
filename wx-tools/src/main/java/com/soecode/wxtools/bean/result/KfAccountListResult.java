package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class KfAccountListResult {
  @JsonProperty("kf_list")
  private List<KfAccountResult> kfList;

  public List<KfAccountResult> getKfList() {
    return kfList;
  }

  public void setKfList(
      List<KfAccountResult> kfList) {
    this.kfList = kfList;
  }

  @Override
  public String toString() {
    return "KfAccountListResult{" +
        "kfList=" + kfList +
        '}';
  }

  public static KfAccountListResult fromJson(String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.readValue(json, KfAccountListResult.class);
  }

  class KfAccountResult{
    @JsonProperty("kf_account")
    private String kfAccount;
    @JsonProperty("kf_nick")
    private String kfNick;
    @JsonProperty("kf_id")
    private String kfId;
    @JsonProperty("kf_headimgurl")
    private String kfHeadImgUrl;

    public String getKfAccount() {
      return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
      this.kfAccount = kfAccount;
    }

    public String getKfNick() {
      return kfNick;
    }

    public void setKfNick(String kfNick) {
      this.kfNick = kfNick;
    }

    public String getKfId() {
      return kfId;
    }

    public void setKfId(String kfId) {
      this.kfId = kfId;
    }

    public String getKfHeadImgUrl() {
      return kfHeadImgUrl;
    }

    public void setKfHeadImgUrl(String kfHeadImgUrl) {
      this.kfHeadImgUrl = kfHeadImgUrl;
    }

    @Override
    public String toString() {
      return "KfAccountResult{" +
          "kfAccount='" + kfAccount + '\'' +
          ", kfNick='" + kfNick + '\'' +
          ", kfId='" + kfId + '\'' +
          ", kfHeadImgUrl='" + kfHeadImgUrl + '\'' +
          '}';
    }
  }
}
