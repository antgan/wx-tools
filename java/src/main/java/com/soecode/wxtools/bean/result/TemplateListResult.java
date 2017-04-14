package com.soecode.wxtools.bean.result;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 获取自身的模板列表
 * @author antgan
 *
 */
public class TemplateListResult {

	private List<TemplateResult> template_list;

	public List<TemplateResult> getTemplate_list() {
		return template_list;
	}

	public void setTemplate_list(List<TemplateResult> template_list) {
		this.template_list = template_list;
	}
	

	/**
	 * json --> obj
	 * 
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static TemplateListResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, TemplateListResult.class);
	}

	@Override
	public String toString() {
		return "TemplateListResult [template_list=" + template_list + "]";
	}
	
	
}
