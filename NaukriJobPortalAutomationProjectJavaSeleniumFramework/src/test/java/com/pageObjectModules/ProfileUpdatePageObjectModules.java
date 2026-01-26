package com.pageObjectModules;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.interFaceElements.ProfileUpdatePageInterfaceElements;

public class ProfileUpdatePageObjectModules extends BaseClass implements ProfileUpdatePageInterfaceElements{
	
	
	public ProfileUpdatePageObjectModules() {
		PageFactory.initElements(getDriver(),this);
	}
	
	
	@FindBy(xpath = clickOnProfileUpdate_xpath)
	private WebElement clickOnProfileUpdate;
	
	@FindBy(xpath = clickOnViewAndUpdateProfile_xpath)
	private WebElement clickOnViewAndUpdateProfile;
	
	
	public void clickOnProfile() {
		clickSelenium(clickOnProfileUpdate);
	}
	
	public void clickOnViewAnUpdateProfile() {
		clickSelenium(clickOnViewAndUpdateProfile);
	}
	

}
