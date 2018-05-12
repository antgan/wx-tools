package com.soecode.wxtools.bean;

import java.io.IOException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class KfAccount {

  @JsonProperty("kf_account")
  private String kfAccount;
  private String nickname;
  private String password;

  public KfAccount() {
  }

  public KfAccount(String kfAccount, String nickname, String password) {
    this.kfAccount = kfAccount;
    this.nickname = nickname;
    this.password = password;
  }

  public String getKfAccount() {
    return kfAccount;
  }

  public void setKfAccount(String kfAccount) {
    this.kfAccount = kfAccount;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String toJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

  @Override
  public String toString() {
    return "KfAccount{" +
        "kfAccount='" + kfAccount + '\'' +
        ", nickname='" + nickname + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
