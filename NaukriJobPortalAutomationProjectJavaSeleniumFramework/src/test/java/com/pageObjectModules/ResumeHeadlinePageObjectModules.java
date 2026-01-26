package com.pageObjectModules;

import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.interFaceElements.ResumeHeadlineInterfaceElements;
import com.pageObjectManager.PageObjectManager;

public class ResumeHeadlinePageObjectModules extends BaseClass implements ResumeHeadlineInterfaceElements {

	public ResumeHeadlinePageObjectModules() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = editResumeheadlineBtn_xpath)
	private WebElement editResumeheadlineBtn;

	@FindBy(xpath = resumeHeadlinetextBox_xpath)
	private WebElement resumeHeadlinetextBox;

	@FindBy(xpath = clickOnSaveBtn_xpath)
	private WebElement clickOnSaveBtn;

	PageObjectManager pageObjectManager = new PageObjectManager();

	public void clickOnEditResumeHeaddlineBtn() {
		clickSelenium(editResumeheadlineBtn);
	}

	public void fillResumeHeadlineTextField() {
		ActionsPerform(resumeHeadlinetextBox, null, "doubleclick");
		clickSelenium(resumeHeadlinetextBox);
		localWait(2000);
		pressKey(KeyEvent.VK_BACK_SPACE);
		localWait(2000);
		sendkeys(resumeHeadlinetextBox, pageObjectManager.getFileReaderManager().getDataProperty("resumeHeadline"));
	}

	public void clickOnSaveBtn() {
		localWait(3000);
		clickSelenium(clickOnSaveBtn);
	}
}
