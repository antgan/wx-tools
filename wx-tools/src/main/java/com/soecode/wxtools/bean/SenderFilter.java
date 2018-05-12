package com.soecode.wxtools.bean;

public class SenderFilter {

  private boolean is_to_all;
  private int tag_id;

  public SenderFilter() {}

  public SenderFilter(boolean is_to_all, int tag_id) {
    this.is_to_all = is_to_all;
    this.tag_id = tag_id;
  }

  public Boolean getIs_to_all() {
    return is_to_all;
  }

  public void setIs_to_all(Boolean is_to_all) {
    this.is_to_all = is_to_all;
  }

  public int getTag_id() {
    return tag_id;
  }

  public void setTag_id(int tag_id) {
    this.tag_id = tag_id;
  }
}
