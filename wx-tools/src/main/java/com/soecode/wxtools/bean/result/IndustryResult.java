package com.soecode.wxtools.bean.result;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class IndustryResult {
	private Industry primary_industry;
	private Industry secondary_industry;

	public Industry getPrimary_industry() {
		return primary_industry;
	}

	public void setPrimary_industry(Industry primary_industry) {
		this.primary_industry = primary_industry;
	}

	public Industry getSecondary_industry() {
		return secondary_industry;
	}

	public void setSecondary_industry(Industry secondary_industry) {
		this.secondary_industry = secondary_industry;
	}
	
	public static IndustryResult fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, IndustryResult.class);
	}
	
	

	@Override
	public String toString() {
		return "IndustryResult [primary_industry=" + primary_industry + ", secondary_industry=" + secondary_industry
				+ "]";
	}



	public static class Industry{
		private String first_class;
		private String second_class;
		public String getFirst_class() {
			return first_class;
		}
		public void setFirst_class(String first_class) {
			this.first_class = first_class;
		}
		public String getSecond_class() {
			return second_class;
		}
		public void setSecond_class(String second_class) {
			this.second_class = second_class;
		}
		@Override
		public String toString() {
			return "Industry [first_class=" + first_class + ", second_class=" + second_class + "]";
		}
		
	}
}
