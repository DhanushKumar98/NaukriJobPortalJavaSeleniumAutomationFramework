package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;

public class FileReaderManager {
	protected static FileInputStream fileInputStream;
	protected static Properties property;
	
	public static void setUpProperty() {
		try {
			File file = new File(System.getProperty("user.dir") + "/src/main/resources/property/config.properties");
			fileInputStream = new FileInputStream(file);
			property = new Properties();
			property.load(fileInputStream);
		}catch(Exception e) {
			Assert.fail("ERROR : OCCURS DURING FILE NOT FOUND"+ e.getMessage());
		}
	}
	
	public static String getDataProperty(String keyData) {
		setUpProperty();
		String value = property.getProperty(keyData);
		return value;
	}
	
	
	// For Verification Purpose
	public static void main(String [] args) {
		String data = getDataProperty("name");
		System.out.println(data);
	}
	
	
	
	
	
	
	

}
