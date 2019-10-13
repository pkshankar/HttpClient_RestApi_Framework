package com.qa.test;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.excelsheet.SheetName;
import com.qa.location.FileLocation;
import com.qa.util.TestUtil;

public class GetTest extends TestBase {

	String baseUrl, serviceUrl, url;

	@DataProvider
	public String[][] getData() {

		return TestUtil.getDataExcelString(FileLocation.CAPITAL_FILE_LOCATION, SheetName.CAPITAL_INFO_SHEETNAME);
	}

//	@BeforeMethod
//	public void setUp() {
//
//		baseUrl = prop.getProperty("capitalInfoBaseUrl");
//		serviceUrl = prop.getProperty("capitalInfoServiceUrl");
//
//	}

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("weatherInfoBaseUrl");
		serviceUrl = prop.getProperty("weatherInfoServiceUrl");

	}

	@Test(dataProvider = "getData")
	public void getCapitalTest(String capitalName) {

		url = baseUrl + serviceUrl + "/" + capitalName;
		CloseableHttpResponse closeableHttpResponse = RestClient.getCall(url);
		try {
			String jsonStringResponse = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(dataProvider = "getData")
	public void getWeatherData(String cityName) {

		url = baseUrl + serviceUrl + "/" + cityName;
		CloseableHttpResponse closeableHttpResponse = RestClient.getCall(url);
		try {
			String jsonStringResponse = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			System.out.println(jsonStringResponse);
			System.out.println("*******************************************");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
