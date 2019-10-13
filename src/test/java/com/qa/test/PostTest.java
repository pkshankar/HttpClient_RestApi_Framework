package com.qa.test;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.excelsheet.SheetName;
import com.qa.location.FileLocation;
import com.qa.pojo.CreateUser;
import com.qa.util.TestUtil;

public class PostTest extends TestBase {

	String baseUrl, serviceUrl, url;

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("goRestPostBaseUrl");
		serviceUrl = prop.getProperty("goRestPostServiceUrl");
		url = baseUrl + serviceUrl;
		System.out.println("URL "+ url);

	}

	@DataProvider
	public String[][] getData() {

		return TestUtil.getDataExcelString(FileLocation.CREATE_USER_FILE_LOCATION, SheetName.CREATE_USER_SHEETNAME);

	}

	@Test(dataProvider = "getData")
	public void createUserGoRest(String first_name, String last_name, String gender, String email, String status) {

		String jsonResponse = null;
		CreateUser createUser = new CreateUser(first_name, last_name, gender, email, status);
		String jsonRequestString = TestUtil.pojoToJson(createUser);
		CloseableHttpResponse closeableHttpResponse = RestClient.postCall(url, jsonRequestString);
		try {
			jsonResponse = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jsonResponse);
		System.out.println("****************************");

	}
}
