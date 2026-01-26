package com.pageObjectModules;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.interFaceElements.UploadResumeInterfaceElements;
import com.pageObjectManager.PageObjectManager;

public class UploadResumePageObjectModules extends BaseClass implements UploadResumeInterfaceElements {
	
	public UploadResumePageObjectModules() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	@FindBy(xpath = clickOnResume_xpath)
	private WebElement clickOnResume;
	
	@FindBy(xpath = clickOnUpdateResumeBtn_xpath)
	private WebElement clickOnUpdateResumeBtn;
	
	PageObjectManager pageObjectManager = new PageObjectManager();
	
	public void clickOnResume() {
		clickSelenium(clickOnResume);
		localWait(3000);
	}
	
	public void uploadResumeBtn() {
		clickSelenium(clickOnUpdateResumeBtn);
		localWait(2000);
		uploadFile(pageObjectManager.getFileReaderManager().getDataProperty("uploadFilePath"));
	}
	
	
	

}
