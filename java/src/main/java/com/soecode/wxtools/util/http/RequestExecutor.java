package com.soecode.wxtools.util.http;

import com.soecode.wxtools.exception.WxErrorException;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;

public interface RequestExecutor<T, E> {

	T execute(CloseableHttpClient httpclient, String uri, E data)
			throws WxErrorException,  IOException;

}
