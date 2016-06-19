package com.soecode.wxtools.util.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.StringUtils;
import com.soecode.wxtools.util.file.FileUtils;

/**
 * 【永久】素材请求下载器
 * 下载媒体文件POST请求执行器，请求的参数是String, 返回的结果是File
 * @author antgan
 *
 */
public class MediaDownloadPostRequestExecutor implements RequestExecutor<File, String> {

	private File materialDirFile;

	public MediaDownloadPostRequestExecutor() {
		super();
	}

	public MediaDownloadPostRequestExecutor(File materialDirFile) {
		super();
		this.materialDirFile = materialDirFile;
	}

	@Override
	public File execute(CloseableHttpClient httpclient, String uri, String params)
			throws WxErrorException, ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(uri);

		if (params != null) {
			httpPost.setEntity(new StringEntity(params,"UTF-8"));
		}

		try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
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
			File localFile = FileUtils.createMaterialFile(inputStream, name_ext[0], name_ext[name_ext.length-1], materialDirFile);
			return localFile;
		}
	}

	protected String getFileName(CloseableHttpResponse response) {
		Header[] contentDispositionHeader = response.getHeaders("Content-disposition");
		Pattern p = Pattern.compile(".*filename=\"(.*)\"");
		Matcher m = p.matcher(contentDispositionHeader[0].getValue());
		m.matches();
		String fileName = m.group(1);
		return fileName;
	}

}
