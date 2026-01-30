package com.utility;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderManager {

	// Simple ExcelReader (Beginner Friendly)

	public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
		String value = "";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet(sheetName);
			Row row = sheet.getRow(rowNum);
			Cell cell = row.getCell(colNum);

			value = cell.toString(); // handles string & numeric

			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

		// How to use :
		// String username = ExcelReader.getCellData("TestData/TestData.xlsx",1,1);
		// String password = ExcelReader.getCellData("TestData/TestData.xlsx",1,2);
	}

	public static Map<String,String> getTestData(String filePath,String sheetName,String testCaseName){
		
		Map<String,String>  dataMap = new HashMap<>();
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			
			Row headerRow = sheet.getRow(0);
			
			for(int i = 1;i<=sheet.getLastRowNum();i++) {
				Row currentRow = sheet.getRow(i);
				
				if(currentRow.getCell(0).toString().equalsIgnoreCase(testCaseName)) {
					for(int j = 0;j< headerRow.getLastCellNum();j++) {
						String key = headerRow.getCell(j).toString();
						String value = currentRow.getCell(j).toString();
						dataMap.put(key,value);
					}
					break;
				}
			}
			workbook.close();
			fis.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	//Using This in Selenium Test (Real Project Style)
	// Map<String ,String> testData = ExcelReader.getTestData("TestData/TestData.xlsx","Sheet1","TC01");
	// driver.findElement(By.id("username")).sendKeys(testData.get("username"));
	// driver.findElement(By,id("password")).sendKeys(testData.get("password"));
	
	//Handling Numeric / Boolean / Date Cells (Professional Touch)
    //Replace .toString() with DataFormatter:
	
	//DataFormatter formatter = new DataFormatter();
	//String value = formatter.formatCellValue(cell);

	// Avoids:
	// IllegalStateException: Cannot get STRING value from a NUMERIC cell
	
	
	
	
	
	
	
	
	
	
	

}
