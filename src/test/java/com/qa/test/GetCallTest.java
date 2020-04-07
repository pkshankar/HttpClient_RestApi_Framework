package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class GetCallTest {
	
	@Test
	public void getTest() throws Throwable, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://reqres.in/api/users?page=2");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		String responseString = EntityUtils.toString(response.getEntity());
		JSONObject jObj = new JSONObject(responseString);
		
		System.out.println("PAGE : " + jObj.getJSONArray("data").getJSONObject(0).get("email"));
		
		
		
	}

}
