package com.ITestListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;

public class ITestListenersClass extends BaseClass implements ITestListener {

	// ----------Reusable Methods---------
	private void attachScreenshot(ITestResult result, Status status, String message) {
		try {
			String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
			ExtentReport_Test.extenttest.log(status, result.getMethod().getMethodName() + " : " + message,
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (Exception e) {
			ExtentReport_Test.extenttest.log(status, "Screenshot capture failed: " + e.getMessage());
		}
	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		attachScreenshot(result, Status.PASS, "TEST PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		attachScreenshot(result, Status.FAIL, "TEST FAILED");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
