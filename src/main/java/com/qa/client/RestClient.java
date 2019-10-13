package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class RestClient extends TestBase {

	public static CloseableHttpResponse getCall(String uri) {

		CloseableHttpClient closeableHttpClient = createClient();
		HttpGet httpGet = createHttpGet(uri);
		return executeRequest(closeableHttpClient, httpGet);

	}

	public static CloseableHttpResponse postCall(String uri, String jsonBody) {

//		System.out.println("JSON BODY "+ jsonBody);
//		CloseableHttpClient closeableHttpClient = createClient();
		System.out.println("USERNAME " +prop.getProperty("goRestUserName") );
		System.out.println("pwd " +prop.getProperty("goRestPassword"));
		CloseableHttpClient closeableHttpClient = TestUtil.createBasicAuth(prop.getProperty("goRestUserName"), prop.getProperty("goRestPassword"));
		HttpPost httpPost = createHttpPost(uri);
		try {
			httpPost.setEntity(new StringEntity(jsonBody));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executeRequest(closeableHttpClient, httpPost);

	}

	public static void putCall() {

	}

	public static void deleteCall() {

	}

	public static CloseableHttpClient createClient() {

		return HttpClients.createDefault();

	}

	public static HttpGet createHttpGet(String uri) {

		return new HttpGet(uri);

	}

	public static HttpPost createHttpPost(String uri) {

		return new HttpPost(uri);

	}

	public static CloseableHttpResponse executeRequest(CloseableHttpClient closeableHttpClient,
			HttpUriRequest httpUriRequest) {

		CloseableHttpResponse closeableHttpResponse = null;

		try {
			closeableHttpResponse = closeableHttpClient.execute(httpUriRequest);
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return closeableHttpResponse;
	}

	public static int getResponseCode(CloseableHttpResponse closeableHttpResponse) {

		return closeableHttpResponse.getStatusLine().getStatusCode();

	}

	public static Header[] getHeaders(CloseableHttpResponse closeableHttpResponse) {

		Header[] headers = closeableHttpResponse.getAllHeaders();
		return headers;
	}
}
