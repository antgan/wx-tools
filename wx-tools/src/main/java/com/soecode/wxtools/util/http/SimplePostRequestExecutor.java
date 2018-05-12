package com.soecode.wxtools.util.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;

public class SimplePostRequestExecutor implements RequestExecutor<String, String> {
	@Override
	public String execute(CloseableHttpClient httpclient, String uri,String params)
			throws WxErrorException, ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(uri);
	
		if (params != null) {
			httpPost.setEntity(new StringEntity(params,"UTF-8"));
		}

		try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
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
