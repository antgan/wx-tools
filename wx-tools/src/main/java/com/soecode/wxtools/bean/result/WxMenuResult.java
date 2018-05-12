package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.WxMenu;

public class WxMenuResult {
	private WxMenu menu;
	private List<WxMenu> conditionalmenu;

	public WxMenu getMenu() {
		return menu;
	}
	public void setMenu(WxMenu menu) {
		this.menu = menu;
	}
	
	public List<WxMenu> getConditionalmenu() {
		return conditionalmenu;
	}
	
	public void setConditionalmenu(List<WxMenu> conditionalmenu) {
		this.conditionalmenu = conditionalmenu;
	}
	
	
	@Override
	public String toString() {
		return "WxMenuResult [menu=" + menu + ", conditionalmenu=" + conditionalmenu + "]";
	}

	public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

	public static WxMenuResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, WxMenuResult.class);
	}
}
