package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.WxMenu;

/**
 * 微信菜单栏查询返回结果
 * @author antgan
 *
 */
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
	/**
	 * obj --> json
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

	/**
	 * json --> obj
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static WxMenuResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, WxMenuResult.class);
	}
}
