package com.pageObjectModules;

import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.interFaceElements.ProfileSummaryInterfaceElements;
import com.pageObjectManager.PageObjectManager;

public class ProfileSummaryPageObjectModules extends BaseClass implements ProfileSummaryInterfaceElements {
	
	
	public ProfileSummaryPageObjectModules() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath = profileSummary_xpath)
	private WebElement profileSummary;

	@FindBy(xpath = editProfileSummary_xpath)
	private WebElement editProfileSummary;
	
	@FindBy(css = summaryTextBox_css)
	private WebElement summaryTextBox;
	
	@FindBy(xpath = saveBtn_xpath)
	private WebElement saveBtn;
	
	PageObjectManager pageObjectManager = new PageObjectManager();
	
	public void clickOnEditBtn() {
		clickSelenium(profileSummary);
		localWait(5000);
		clickJS(editProfileSummary);
		localWait(5000);
	}
	
	public void enterProfileSummary() {
		clickSelenium(summaryTextBox);
		pressCtrlKey(KeyEvent.VK_A);
		localWait(3000);
		pressCtrlKey(KeyEvent.VK_C);
		localWait(2000);
		pressKey(KeyEvent.VK_BACK_SPACE);
		localWait(2000);
		pressCtrlKey(KeyEvent.VK_V);
		localWait(2000);
	}
	
	public void clickOnSaveBtn() {
		clickSelenium(saveBtn);
	}
	
	
	
}
