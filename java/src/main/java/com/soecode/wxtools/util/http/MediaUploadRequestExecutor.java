package com.soecode.wxtools.util.http;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.WxVideoIntroduction;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 上传媒体文件请求执行器，请求的参数是File, 返回的结果是String
 * 
 * @author antgan
 *
 */
public class MediaUploadRequestExecutor implements RequestExecutor<WxMediaUploadResult, File> {
	private WxVideoIntroduction introduction;
	
	public MediaUploadRequestExecutor() {
		// TODO Auto-generated constructor stub
	}
	
	public MediaUploadRequestExecutor(WxVideoIntroduction introduction){
		this.introduction = introduction;
	}
	
	@Override
	public WxMediaUploadResult execute(CloseableHttpClient httpclient, String uri, File file)
			throws WxErrorException, ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(uri);

		if (file != null) {
			HttpEntity entity = null;
			//如果不等null，说明是上传视频素材
			if(this.introduction !=null){
				StringBody stringBody = new StringBody(introduction.toJson(),ContentType.TEXT_PLAIN.withCharset(StandardCharsets.UTF_8));
				entity = MultipartEntityBuilder.create().setCharset(StandardCharsets.UTF_8).addBinaryBody("media", file).addPart("description", stringBody).setMode(HttpMultipartMode.RFC6532).build();
			}else{
				entity = MultipartEntityBuilder.create().addBinaryBody("media", file).setMode(HttpMultipartMode.RFC6532).build();
			}
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
		}
		try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
			String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
			//如果请求发生错误，抛出异常
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(responseContent);
			if(node.get("errcode")!=null && !(node.get("errcode").asInt()==0)){
				WxError error = WxError.fromJson(responseContent);
				throw new WxErrorException(error);
			}
			return WxMediaUploadResult.fromJson(responseContent);
		}
	}

}
