package com.soecode.wxtools.util.http;

import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;
import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

public class KfHeadImageUploadRequestExecutor implements RequestExecutor<WxError, File> {

	public KfHeadImageUploadRequestExecutor() {}

	@Override
	public WxError execute(CloseableHttpClient httpclient, String uri, File file)
			throws WxErrorException, IOException {
		HttpPost httpPost = new HttpPost(uri);

		if (file != null) {
			HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("media", file).setMode(HttpMultipartMode.RFC6532).build();
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
		}
		try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
			String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
			return WxError.fromJson(responseContent);
		}
	}

}
