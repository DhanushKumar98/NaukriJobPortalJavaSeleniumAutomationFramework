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

	@FindBy(xpath = clickOnDeleteResumeBtn_xpath)
	private WebElement clickOnDeleteResumeBtn;

	@FindBy(xpath = deleteResumeBtn_xpath)
	private WebElement deleteResumeBtn;

	@FindBy(xpath = uploadResumeBtn_xpath)
	private WebElement uploadResumeBtn;

	PageObjectManager pageObjectManager = new PageObjectManager();

	public void deleteExistingResume() {
		clickSelenium(clickOnDeleteResumeBtn);
		clickSelenium(deleteResumeBtn);
	}

	public void clickOnResume() {
		clickSelenium(clickOnResume);
		localWait(3000);
	}

	public void uploadResumeBtn() {
		clickSelenium(uploadResumeBtn);
		localWait(2000);
		uploadFile(pageObjectManager.getFileReaderManager().getDataProperty("uploadFilePath"));
		localWait(3000);
	}
}
