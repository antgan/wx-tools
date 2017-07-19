package com.soecode.wxtools.bean;

public class SenderFilter {
	private boolean is_to_all;
	private int group_id;
	
	public SenderFilter() {
		// TODO Auto-generated constructor stub
	}
	public SenderFilter(boolean is_to_all,int group_id) {
		this.is_to_all = is_to_all;
		this.group_id = group_id;
	}
	
	public Boolean getIs_to_all() {
		return is_to_all;
	}
	public void setIs_to_all(Boolean is_to_all) {
		this.is_to_all = is_to_all;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	
	
}
