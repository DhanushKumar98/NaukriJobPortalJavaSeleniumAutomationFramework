package com.pageObjectModules;

import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.interFaceElements.BasicDetailsPageInterfaceElements;
import com.pageObjectManager.PageObjectManager;

public class BasicDetailsPageObjectModules extends BaseClass implements BasicDetailsPageInterfaceElements {
	
	public BasicDetailsPageObjectModules() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath = ClickOnEditBtn_xpath)
	private WebElement ClickOnEditBtn;
	
	@FindBy(css = name_css)
	private WebElement name;
	
	@FindBy(css = yearInputTag_css)
	private WebElement yearInputTag;
	
	@FindBy(xpath = yearDropDown_xpath)
	private List<WebElement> yearDropDown;
	
	@FindBy(css = monthInputTag_css)
	private WebElement monthInputTag;
	
	@FindBy(xpath = monthDropdown_xpath)
	private List<WebElement> monthDropdown;
	
	@FindBy(css = currentSalary_css)
	private WebElement currentSalary;
	
	@FindBy(css = salaryBreakdownInputTag_css)
	private WebElement salaryBreakdownInputTag;
	
	@FindBy(xpath = salaryBreakdown_xpath)
	private List<WebElement> salaryBreakdown;
	
	@FindBy(xpath = currentLocationIndia_xpath)
	private WebElement currentLocationIndia;
	
	@FindBy(xpath = currentLocationOutside_xpath)
	private WebElement currentLocationOutside;
	
	@FindBy(css = enterCurrentLocation_css)
	private WebElement enterCurrentLocation;
	
	@FindBy(css = stateInputTag_css)
	private WebElement stateInputTag;
	
	@FindBy(xpath = stateDropdown_xpath)
	private List<WebElement> stateDropdown;
	
	@FindBy(xpath = selectNoticePeriodServingNoticePeriod_xpath)
	private WebElement selectNoticePeriodServingNoticePeriod;
	
	@FindBy(xpath = selectNoticePeriod15DayorLess_xpath)
	private WebElement selectNoticePeriod15DayorLess;
	
	@FindBy(css = clickOnSaveBtn_css)
	private WebElement clickOnSaveBtn;
	
	PageObjectManager pageObjectManager = new PageObjectManager();
	
	public void clickOnEditBtn() {
		clickSelenium(ClickOnEditBtn);
	}
	
	public void enterName() {
		clickSelenium(name);
		ActionsPerform(name, null, "doubleclick");
		pressKey(KeyEvent.VK_BACK_SPACE);
		sendkeys(name,pageObjectManager.getFileReaderManager().getDataProperty("name") );
	}
		
	public void totalExperience() {
		selectCustomDropdown(yearInputTag, yearDropDown,pageObjectManager.getFileReaderManager().getDataProperty("year"));
		selectCustomDropdown(monthInputTag, monthDropdown, pageObjectManager.getFileReaderManager().getDataProperty("month"));
	}
	
	public void currentSalary() {
		clickSelenium(currentSalary);
		ActionsPerform(currentSalary, null,"doubleclick");
		pressKey(KeyEvent.VK_BACK_SPACE);
		sendkeys(currentSalary, pageObjectManager.getFileReaderManager().getDataProperty("currentSalary"));
	}
	
	public void salaryBreakdown() {
		selectCustomDropdown(salaryBreakdownInputTag, salaryBreakdown, pageObjectManager.getFileReaderManager().getDataProperty("salarybreakdown"));
	}
	
	public void CurrentLocation() {
		
		clickJS(currentLocationOutside);
		localWait(3000);
		clickSelenium(currentLocationIndia);
		
		clickSelenium(currentLocationIndia);
		localWait(3000);
		ActionsPerform(enterCurrentLocation, null,"doubleclick");
		pressKey(KeyEvent.VK_BACK_SPACE);
		sendkeys(enterCurrentLocation, pageObjectManager.getFileReaderManager().getDataProperty("city"));
		localWait(3000);
		pressKey(KeyEvent.VK_TAB);
		localWait(2000);
		selectCustomDropdown(stateInputTag, stateDropdown, pageObjectManager.getFileReaderManager().getDataProperty("state"));
	}
	
	public void noticePeriod() {
		clickSelenium(selectNoticePeriodServingNoticePeriod);
		localWait(3000);
		clickSelenium(selectNoticePeriod15DayorLess);
	}
	
	public void clickOnSaveBtn() {
		clickSelenium(clickOnSaveBtn);
		localWait(3000);
	}
}
