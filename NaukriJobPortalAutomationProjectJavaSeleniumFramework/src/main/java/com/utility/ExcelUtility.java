package com.utility;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static Object[][] getExcelData(String filePath, String sheetName) {

		Object[][] data = null;

		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getLastRowNum(); // Excluding header
			int colCount = sheet.getRow(0).getLastCellNum();

			data = new Object[rowCount][colCount];

			for (int i = 1; i <= rowCount; i++) {
				Row row = sheet.getRow(i);

				for (int j = 1; j < colCount; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = cell.toString();
				}
			}
			workbook.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
//	
//	import org.testng.annotations.DataProvider;
//	import utilities.ExcelUtils;
//
//	public class DataProviderClass {
//
//	    @DataProvider(name = "loginData")
//	    public Object[][] getLoginData() {
//
//	        String filePath = "TestData/TestData.xlsx";
//	        String sheetName = "LoginData";
//
//	        return ExcelUtils.getExcelData(filePath, sheetName);
//	    }
//	}
//	
//	import org.openqa.selenium.By;
//	import org.openqa.selenium.WebDriver;
//	import org.openqa.selenium.chrome.ChromeDriver;
//	import org.testng.annotations.Test;
//	import dataproviders.DataProviderClass;
//
//	public class LoginTest {
//
//	    @Test(dataProvider = "loginData",
//	          dataProviderClass = DataProviderClass.class)
//	    public void loginTest(String username, String password) {
//
//	        WebDriver driver = new ChromeDriver();
//	        driver.get("https://example.com/login");
//
//	        driver.findElement(By.id("username")).sendKeys(username);
//	        driver.findElement(By.id("password")).sendKeys(password);
//	        driver.findElement(By.id("loginBtn")).click();
//
//	        driver.quit();
//	    }
//	}

//	
//	import org.openqa.selenium.By;
//	import org.openqa.selenium.WebDriver;
//	import org.openqa.selenium.chrome.ChromeDriver;
//	import org.testng.annotations.Test;
//	import dataproviders.DataProviderClass;
//
//	public class LoginTest {
//
//	    @Test(dataProvider = "loginData",
//	          dataProviderClass = DataProviderClass.class)
//	    public void loginTest(String username, String password) {
//
//	        WebDriver driver = new ChromeDriver();
//	        driver.get("https://example.com/login");
//
//	        driver.findElement(By.id("username")).sendKeys(username);
//	        driver.findElement(By.id("password")).sendKeys(password);
//	        driver.findElement(By.id("loginBtn")).click();
//
//	        driver.quit();
//	    }
//	}

	// loginTest(admin, admin123) → PASS
//	loginTest(user1, pass123)   → PASS
//	loginTest(user2, pass456)   → PASS

}
