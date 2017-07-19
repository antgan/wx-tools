package com.soecode.wxtools.util.http;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

/**
 * copy from {@link org.apache.http.impl.client.BasicResponseHandler}
 *
 */
public class Utf8ResponseHandler implements ResponseHandler<String> {

	public static final ResponseHandler<String> INSTANCE = new Utf8ResponseHandler();

	public String handleResponse(final HttpResponse response) throws HttpResponseException, IOException {
		final StatusLine statusLine = response.getStatusLine();
		final HttpEntity entity = response.getEntity();
		if (statusLine.getStatusCode() >= 300) {
			EntityUtils.consume(entity);
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
		return entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
	}

}
