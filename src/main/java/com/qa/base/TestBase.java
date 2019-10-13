package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

	public static Properties prop;

	public TestBase() {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\pkshank\\eclipse-workspace\\HttpClient_RestApi_Framework\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
