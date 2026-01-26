package com.ITestListeners;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.base.BaseClass;

public class ExtentReport_Test {
	
	public static ExtentTest extenttest ;
	
	@BeforeSuite
	public void extentTestReportStartUp() {
		BaseClass base = new BaseClass() {
		};
		base.extentReportStart(null);
	}

	@AfterSuite
	public void extentTestReportEnd() {
		BaseClass base = new BaseClass() {
		};
		base.extentReportTearDown();
	}
	
	
	
	
}
