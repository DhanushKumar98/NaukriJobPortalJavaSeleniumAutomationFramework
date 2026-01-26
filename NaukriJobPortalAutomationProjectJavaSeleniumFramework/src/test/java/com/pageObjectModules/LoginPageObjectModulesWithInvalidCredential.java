package com.pageObjectModules;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.interFaceElements.LoginPageInterFaceElements;
import com.pageObjectManager.PageObjectManager;

public class LoginPageObjectModulesWithInvalidCredential extends BaseClass implements LoginPageInterFaceElements{
	
	
	public LoginPageObjectModulesWithInvalidCredential() {
		PageFactory.initElements(getDriver(), this);
	}
	

	@FindBy(css = username_css)
	private WebElement username;
	
	@FindBy(css = password_css)
	private WebElement password;
	
	@FindBy(xpath = loginBtn_xpath)
	private WebElement loginBtn;
	
	@FindBy(xpath = errorMessage_xpath)
	private WebElement errorMessage;
	
	@FindBy(xpath = showPasswordBtn_xpath)
	private WebElement showPasswordBtn;
	
	@FindBy(xpath = forgetPassword_xpath)
	private WebElement forgetPassword;
	
	@FindBy(xpath = useOtpToLogin_xpath)
	private WebElement useOtpToLogin;
	
	@FindBy(xpath = registerForFree_xpath)
	private WebElement registerForFree;
	
	PageObjectManager pageObjectManager = new PageObjectManager();
	
	public void setUsername() {
		sendkeys(username, pageObjectManager.getFileReaderManager().getDataProperty("usernamesec"));
	}
	
	public void setPassword() {
		sendkeys(password, pageObjectManager.getFileReaderManager().getDataProperty("passwordsec"));
	}
	
	public void clickOnShowPassword() {
		clickSelenium(showPasswordBtn);
		localWait(5000);
	}
	
	public void clickOnLoginBtn() {
		clickSelenium(loginBtn);
	}
	
	public void getErrorMessage() {
		String expectedResult = "Invalid details. Please check the Email ID - Password combination.";
		String actualResult =  getText(errorMessage);
		
		assertEquals(expectedResult, actualResult, "INVALID DATA INPUT : ERROR MESSAGE IS DISPLAYED");
	}
	
	
	
	
	
	
	
	
	
	
}
