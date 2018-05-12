package com.soecode.wxtools.util.http;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;

public class SimpleGetRequestExecutor implements RequestExecutor<String, Map<String,String>> {
	@Override
	public String execute(CloseableHttpClient httpclient, String uri, Map<String,String> params)
			throws WxErrorException, ClientProtocolException, IOException {
		if (params != null) {
			uri += '?';
			for(String key : params.keySet()){
				uri+=key+"="+params.get(key)+"&";
			}
			uri = uri.substring(0, uri.length()-1);
		}
		HttpGet httpGet = new HttpGet(uri);
		
		try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
			String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(responseContent);
			if(node.get("errcode")!=null && !(node.get("errcode").asInt()==0)){
				WxError error = WxError.fromJson(responseContent);
				throw new WxErrorException(error);
			}
			return responseContent;
		}
	}

}
