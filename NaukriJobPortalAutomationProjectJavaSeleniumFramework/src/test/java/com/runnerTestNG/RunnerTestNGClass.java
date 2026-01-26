package com.runnerTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ITestListeners.ExtentReport_Test;
import com.ITestListeners.ITestListenersClass;
import com.aventstack.extentreports.ExtentTest;
import com.base.BaseClass;
import com.pageObjectManager.PageObjectManager;

@Listeners(ITestListenersClass.class)
public class RunnerTestNGClass extends BaseClass {

	PageObjectManager pageObjectManager = new PageObjectManager();

	@BeforeSuite
	public void extentReportStart() {
		extentReportStart(System.getProperty("user.dir") + "/ExtentReports/");
	}

	// @Parameters({"browser","url"})
	@BeforeTest
	public void setUp() {
		launchBrowser(pageObjectManager.getFileReaderManager().getDataProperty("browser"));
		launchUrl(pageObjectManager.getFileReaderManager().getDataProperty("url"));
//		launchBrowser(browser);
//		launchUrl(url);
	}

	@Test(priority = 1)
	public void LoginPageTest() {
		ExtentReport_Test.extenttest = extentReports
				.createTest("LOGIN - VALID CREDENTIALS", "NAUKRI WEB APPLICATION TEST : "
						+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).assignCategory("REGRESSION").assignAuthor("DHANUSHKUMAR").assignDevice("chrome")
				.info("VERIFY USER CAN UPDATE PROFILE SUMMARY SUCCESSFULLY");
		ExtentReport_Test.extenttest.info("User enter username : "+ pageObjectManager.getFileReaderManager().getDataProperty("username"));
		pageObjectManager.getLoginPageModule().setUsername();
		ExtentReport_Test.extenttest.info("User enter Password : "+ pageObjectManager.getFileReaderManager().getDataProperty("password"));
		pageObjectManager.getLoginPageModule().setPassword();
		ExtentReport_Test.extenttest.info("User Click on Show Password Button : "+ pageObjectManager.getFileReaderManager().getDataProperty("password"));
		pageObjectManager.getLoginPageModule().clickOnShowPassword();
		ExtentReport_Test.extenttest.info("User Click on Login Button.");
		pageObjectManager.getLoginPageModule().clickOnLoginBtn();
		ExtentReport_Test.extenttest.pass("Login Test Successfully");
		localWait(5000);
	}

//	@Test(priority = -1)
//	public void LoginPageTestWithInvalidCredantial() {
//		ExtentReport_Test.extenttest = extentReports
//				.createTest("NAUKRI WEB APPLICATION TEST : "
//						+ Thread.currentThread().getStackTrace()[1].getMethodName().toString())
//				.info("LOGIN TEST WITH INVALID CREDENTIAL");
//		pageObjectManager.getLoginPageModuleWithInvalidData().setUsername();
//		pageObjectManager.getLoginPageModuleWithInvalidData().setPassword();
//		pageObjectManager.getLoginPageModuleWithInvalidData().clickOnShowPassword();
//		pageObjectManager.getLoginPageModuleWithInvalidData().clickOnLoginBtn();
//		localWait(5000);
//		pageObjectManager.getLoginPageModuleWithInvalidData().getErrorMessage();
//	}

	@Test(priority = 2)
	public void ProfileUpdateTest() {
		ExtentReport_Test.extenttest = extentReports
				.createTest("PROFILE UPDATE MODULE","NAUKRI WEB APPLICATION TEST : "
						+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).assignCategory("REGRESSION").assignAuthor("DHANUSH KUMAR").assignDevice("chrome")
				.info("VERIFY THE USER CAN UPDATE PROFILE TEST");
		ExtentReport_Test.extenttest.info("User Should click on Naukri Profile Button");
		pageObjectManager.getProfileUpdatePageModule().clickOnProfile();
		ExtentReport_Test.extenttest.info("User Should click on Naukri View and Update Profile Button");
		pageObjectManager.getProfileUpdatePageModule().clickOnViewAnUpdateProfile();
		ExtentReport_Test.extenttest.pass("PROFILE Updated Successfully");
		localWait(5000);	
	} 

	@Test(priority = 3)
	public void basicDetailsTest() {
		ExtentReport_Test.extenttest = extentReports
				.createTest("BASIC DETAILS MODULE","NAUKRI WEB APPLICATION TEST : "
						+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).assignCategory("REGRESSION").assignAuthor("DHANUSH KUMAR").assignDevice("chrome")
				.info("VERIFY THE USER ENTER BASIC DETAILS TEST");
		ExtentReport_Test.extenttest.info("User Click on Basic Details Edit Button");
		pageObjectManager.getBasicDetailsPageModule().clickOnEditBtn();
		ExtentReport_Test.extenttest.info("User Should Enter Name on NameTextFiled : "+pageObjectManager.getFileReaderManager().getDataProperty("name"));
		pageObjectManager.getBasicDetailsPageModule().enterName();
		ExtentReport_Test.extenttest.info("User Should Enter Total Work Expericence : "+pageObjectManager.getFileReaderManager().getDataProperty("year")+" "+pageObjectManager.getFileReaderManager().getDataProperty("month"));
		pageObjectManager.getBasicDetailsPageModule().totalExperience();
		ExtentReport_Test.extenttest.info("User Should Enter Current Salary : "+pageObjectManager.getFileReaderManager().getDataProperty("currentSalary"));
		pageObjectManager.getBasicDetailsPageModule().currentSalary();
		ExtentReport_Test.extenttest.info("User Should Enter SalarBreakDown : "+pageObjectManager.getFileReaderManager().getDataProperty("salarybreakdown"));
		pageObjectManager.getBasicDetailsPageModule().salaryBreakdown();
		ExtentReport_Test.extenttest.info("User Should Enter Current Location : "+pageObjectManager.getFileReaderManager().getDataProperty("city")+" "+","+pageObjectManager.getFileReaderManager().getDataProperty("state"));
		pageObjectManager.getBasicDetailsPageModule().CurrentLocation();
		ExtentReport_Test.extenttest.info("User Should Select Notice Period");
		pageObjectManager.getBasicDetailsPageModule().noticePeriod();
		ExtentReport_Test.extenttest.info("User Should Click on Save Button");
		pageObjectManager.getBasicDetailsPageModule().clickOnSaveBtn();
		ExtentReport_Test.extenttest.pass("BASIC DETAILS Updated Successfully");
		localWait(5000);	
	}
	
	@Test(priority = 4)
	public void resumeHeadlineTest() {
		ExtentReport_Test.extenttest = extentReports
				.createTest("RESUME HEADLINE MODULE"+"NAUKRI WEB APPLICATION TEST : "+ Thread.currentThread().getStackTrace()[1].toString()).assignCategory("REGRESSION").assignAuthor("DHANUSH KUMAR").assignDevice("chrome")
				.info("VERIFY THE RESUME HEADLINE TEST");
		ExtentReport_Test.extenttest.info("User Should Click on Edit Resume Headline Button");
		pageObjectManager.getResumeheadlinePageModule().clickOnEditResumeHeaddlineBtn();
		ExtentReport_Test.extenttest.info("User Should Fill the Resume Headline");
		pageObjectManager.getResumeheadlinePageModule().fillResumeHeadlineTextField();
		ExtentReport_Test.extenttest.info("User Should Click on Save Button");
		pageObjectManager.getResumeheadlinePageModule().clickOnSaveBtn();	
		ExtentReport_Test.extenttest.pass("RESUME HEADLINE Updated Successfully");
		localWait(5000);	
	}

	@Test(priority = 5)
	public void keySkillsTest() {
		ExtentReport_Test.extenttest = extentReports
				.createTest("KEY SKILLS MODULE"+"NAUKRI WEB APPLICATION TEST : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).assignCategory("REGRESSION").assignAuthor("DHANUSH KUMAR").assignDevice("chrome")
				.info("VERIFY THE KEY SKILLS TEST");
		ExtentReport_Test.extenttest.info("User Should Click on Edit Key Skills Button");
		pageObjectManager.getKeySkillsPageModule().clickOnEditKeySkillsBtn();
		ExtentReport_Test.extenttest.info("User Should Remove the Exisiting Skills");
		pageObjectManager.getKeySkillsPageModule().deleteExistingSkills();
		ExtentReport_Test.extenttest.info("User Should Enter New Skills");
		pageObjectManager.getKeySkillsPageModule().enterNewSkills();
		ExtentReport_Test.extenttest.info("User Should Click on Save Button");
		pageObjectManager.getKeySkillsPageModule().clickOnSaveBtn();
		ExtentReport_Test.extenttest.pass("KEY SKILLS Updated Successfully");
		localWait(5000);
	}
	
	@Test(priority = 6)
	public void profileSummaryTest() {
		ExtentReport_Test.extenttest = extentReports
				.createTest("PROFILE SUMMARY MODULE"+"NAUKRI WEB APPLICATION TEST : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).assignCategory("REGRESSION").assignAuthor("DHANUSH KUMAR").assignDevice("chrome")
				.info("VERIFY THE PROFILE SUMMARY TEST");
		ExtentReport_Test.extenttest.info("User Should Click on Edit Profile Summary Button");
		pageObjectManager.getProfileSummaryPageModule().clickOnEditBtn();
		ExtentReport_Test.extenttest.info("User Should Enter Profile Summary TextField");
		pageObjectManager.getProfileSummaryPageModule().enterProfileSummary();
		ExtentReport_Test.extenttest.info("User Should Click on Save Button");
		pageObjectManager.getProfileSummaryPageModule().clickOnSaveBtn();
		ExtentReport_Test.extenttest.pass("PROFILE SUMMARY Updated Successfully");
		localWait(2000);	
	}
	
	@Test(priority = 7)
	public void uploadResumeTest() {
		ExtentReport_Test.extenttest = extentReports
				.createTest("UPLOAD RESUME MODULE" + "NAUKRI WEB APPLICATION TEST : "+ Thread.currentThread().getStackTrace()[1].getMethodName().toString()).assignCategory("REGRESSION").assignAuthor("DHANUSH KUMAR").assignDevice("chrome")
				.info("VERIFY THE UPLOAD RESUME TEST");
		ExtentReport_Test.extenttest.info("User Should click on Resume Section");
		pageObjectManager.getUploadResumePageModule().clickOnResume();
		ExtentReport_Test.extenttest.info("User Should Upload File Path or Resume File Path");
		pageObjectManager.getUploadResumePageModule().uploadResumeBtn();
		ExtentReport_Test.extenttest.info("RESUME UPLOADED SUCCESSFULLY");
	}
	
	
	
	
	
	
	
	
	
	@AfterTest
	public void tearDown() {
		terminateBrowser();
	}

	@AfterSuite
	public void extentReportEnd() {
		extentReportTearDown();
	}
}
