package com.soecode.wxtools.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class WxMenu {

  private List<WxMenuButton> button = new ArrayList<WxMenuButton>();

  private WxMenuRule matchrule;

  private String menuid;

  public List<WxMenuButton> getButton() {
    return button;
  }

  public void setButton(List<WxMenuButton> button) {
    this.button = button;
  }

  public WxMenuRule getMatchrule() {
    return matchrule;
  }

  public void setMatchrule(WxMenuRule matchrule) {
    this.matchrule = matchrule;
  }

  public String getMenuid() {
    return menuid;
  }

  public void setMenuid(String menuid) {
    this.menuid = menuid;
  }

  public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

  public static WxMenu fromJson(String json)
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, WxMenu.class);
  }

  @Override
  public String toString() {
    return "WxMenu [button=" + button + ", matchrule=" + matchrule + ", menuid=" + menuid + "]";
  }

  public static class WxMenuButton {

    private String type;
    private String name;
    private String key;
    private String url;
    private String appid;
    private String pagepath;

    private List<WxMenuButton> sub_button = new ArrayList<WxMenuButton>();

    public String getAppid() {
      return appid;
    }

    public void setAppid(String appid) {
      this.appid = appid;
    }

    public String getPagepath() {
      return pagepath;
    }

    public void setPagepath(String pagepath) {
      this.pagepath = pagepath;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public List<WxMenuButton> getSub_button() {
      return sub_button;
    }

    public void setSub_button(List<WxMenuButton> sub_button) {
      this.sub_button = sub_button;
    }

    @Override
    public String toString() {
      return "WxMenuButton{" +
          "type='" + type + '\'' +
          ", name='" + name + '\'' +
          ", key='" + key + '\'' +
          ", url='" + url + '\'' +
          ", appid='" + appid + '\'' +
          ", pagepath='" + pagepath + '\'' +
          ", sub_button=" + sub_button +
          '}';
    }
  }

  public static class WxMenuRule {

    private String tag_id;
    private String sex;
    private String country;
    private String province;
    private String city;
    private String client_platform_type;
    private String language;

    public String getLanguage() {
      return language;
    }

    public void setLanguage(String language) {
      this.language = language;
    }

    public String getTag_id() {
      return tag_id;
    }

    public void setTag_id(String tag_id) {
      this.tag_id = tag_id;
    }

    public String getSex() {
      return sex;
    }

    public void setSex(String sex) {
      this.sex = sex;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public String getProvince() {
      return province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getClient_platform_type() {
      return client_platform_type;
    }

    public void setClient_platform_type(String client_platform_type) {
      this.client_platform_type = client_platform_type;
    }

    @Override
    public String toString() {
      return "WxMenuRule{" +
          "tag_id='" + tag_id + '\'' +
          ", sex='" + sex + '\'' +
          ", country='" + country + '\'' +
          ", province='" + province + '\'' +
          ", city='" + city + '\'' +
          ", client_platform_type='" + client_platform_type + '\'' +
          ", language='" + language + '\'' +
          '}';
    }
  }

}
