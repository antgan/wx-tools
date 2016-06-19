package com.soecode.wxtools.util.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.StringUtils;
import com.soecode.wxtools.util.file.FileUtils;

/**
 * 【临时】素材请求下载器
 * 下载媒体文件GET请求执行器，请求的参数是String, 返回的结果是File
 * 
 * @author antgan
 *
 */
public class MediaDownloadGetRequestExecutor implements RequestExecutor<File, Map<String,String>> {

	private File tmpDirFile;

	public MediaDownloadGetRequestExecutor() {
		super();
	}

	public MediaDownloadGetRequestExecutor(File tmpDirFile) {
		super();
		this.tmpDirFile = tmpDirFile;
	}

	@Override
	public File execute(CloseableHttpClient httpclient, String uri, Map<String,String> params)
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
			File localFile = FileUtils.createTmpFile(inputStream, name_ext[0], name_ext[name_ext.length-1], tmpDirFile);
			return localFile;
		}
	}

	protected String getFileName(CloseableHttpResponse response) {
		Header[] contentDispositionHeader = response.getHeaders("Content-disposition");
		Pattern p = Pattern.compile(".*filename=\"(.*)\"");
		Matcher m = p.matcher(contentDispositionHeader[0].getValue());
		m.matches();
		String fileName = m.group(m.groupCount());
		return fileName;
	}

}
