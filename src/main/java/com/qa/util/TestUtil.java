package com.qa.util;

import java.io.FileInputStream;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static String[][] getDataExcelString(String sheetLocation, String sheetName) {

		String[][] capitalArr = null;

		try {
			FileInputStream fis = new FileInputStream(sheetLocation);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			int lastRowNum = sh.getLastRowNum();
			int lastCellNum = sh.getRow(0).getLastCellNum();// 5
			capitalArr = new String[lastRowNum][lastCellNum];// 2*5
			for (int rw = 0; rw < lastRowNum; rw++) {

				for (int cl = 0; cl < lastCellNum; cl++) {

					capitalArr[rw][cl] = sh.getRow(rw).getCell(cl).getStringCellValue();

				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return capitalArr;
	}

	public static String pojoToJson(Object obj) {

		String jsonRequestString = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonRequestString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("JSON REQUEST STRING " + jsonRequestString);
		return jsonRequestString;
	}

	public static CloseableHttpClient createBasicAuth(String userName, String password) {

		CredentialsProvider creds = new BasicCredentialsProvider();
		System.out.println("INSIDE TEST UTIL");
		System.out.println("USERNAME "+ userName);
		System.out.println("PWD "+ password);
		System.out.println(prop.getProperty("goRestBasicAuthUrl"));
		creds.setCredentials(new AuthScope(prop.getProperty("goRestBasicAuthUrl"), 80),
				new UsernamePasswordCredentials(userName, password));
		return HttpClients.custom().setDefaultCredentialsProvider(creds).build();
	}

}
