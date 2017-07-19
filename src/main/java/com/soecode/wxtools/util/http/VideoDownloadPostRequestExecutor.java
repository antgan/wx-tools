package com.soecode.wxtools.util.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxVideoMediaResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.StringUtils;
import com.soecode.wxtools.util.file.FileUtils;

/**
 * 【视频】素材请求下载器 下载媒体文件POST请求执行器，请求的参数是String, 返回的结果是File
 * 
 * @author antgan
 *
 */
public class VideoDownloadPostRequestExecutor implements RequestExecutor<WxVideoMediaResult, String> {

	private File materialDirFile;

	public VideoDownloadPostRequestExecutor() {
		super();
	}

	public VideoDownloadPostRequestExecutor(File materialDirFile) {
		super();
		this.materialDirFile = materialDirFile;
	}

	@Override
	public WxVideoMediaResult execute(CloseableHttpClient httpclient, String uri, String params)
			throws WxErrorException, ClientProtocolException, IOException {
		WxVideoMediaResult result = null;
		
		//获取Video下载地址
		HttpPost httpPost = new HttpPost(uri);
		if (params != null) {
			httpPost.setEntity(new StringEntity(params,"UTF-8"));
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
			result = mapper.readValue(responseContent, WxVideoMediaResult.class);		
		}
		
		//下载Video
		HttpGet httpGet = new HttpGet(result.getDown_url());
		try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
			Header[] contentTypeHeader = response.getHeaders("Content-Type");
			if (contentTypeHeader != null && contentTypeHeader.length > 0) {
				// 下载媒体文件出错
				if (ContentType.TEXT_PLAIN.getMimeType().equals(contentTypeHeader[0].getValue())) {
					String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
					throw new WxErrorException(WxError.fromJson(responseContent));
				}
			}
			InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response);
			// 视频文件不支持下载
			String fileName = getFileName(response);
			if (StringUtils.isBlank(fileName)) {
				return null;
			}
			String[] name_ext = fileName.split("\\.");
			FileUtils.createMaterialFile(inputStream, name_ext[0], name_ext[name_ext.length-1], materialDirFile);
		}
		
		return result;
	}

	protected String getFileName(CloseableHttpResponse response) {
		Header[] contentDispositionHeader = response.getHeaders("Content-Disposition");
		Pattern p = Pattern.compile(".*filename=(.*)");
		Matcher m = p.matcher(contentDispositionHeader[0].getValue());
		m.matches();
		String fileName = m.group(1);
		return fileName;
	}

}
