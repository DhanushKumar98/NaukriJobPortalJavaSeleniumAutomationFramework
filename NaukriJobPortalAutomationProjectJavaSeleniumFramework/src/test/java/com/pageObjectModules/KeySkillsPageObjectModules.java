package com.pageObjectModules;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.interFaceElements.KeySkillsPageInterfaceElements;

public class KeySkillsPageObjectModules extends BaseClass implements KeySkillsPageInterfaceElements{
	
	public KeySkillsPageObjectModules() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	@FindBy(xpath = editKeySkillsBtn_xpath)
	private WebElement editKeySkillsBtn;
	
	@FindBy(css = enterskillInputTag_css)
	private WebElement enterskillInputTag;
	
	@FindBy(xpath = enterSkillsDropdown_xpath)
	private List<WebElement> enterSkillsDropdown;
	
	@FindBy(css = clickOnSaveBtn_css)
	private WebElement clickOnSaveBtn;
	
	public void clickOnEditKeySkillsBtn() {
		clickSelenium(editKeySkillsBtn);
	}
	
	public void deleteExistingSkills() {
		clickSelenium(enterskillInputTag);
		localWait(3000);
		pressKeyMultipleTimes(Keys.BACK_SPACE, 40);
	}
	
	public void enterNewSkills() {
		List<String> data = Arrays.asList("Functional Testing","API Testing",
				"Selenium Webdriver",
				"Testng","JIRA","Jenkins","Maven","SQL",
				"Rest Assured","SDET","Regression Testing",
				"Agile Methodology","GIT",
				"Core Java Programming","Postman API","Playwright",
				"Azure DevOps","BDD Cucumber","POM");
		
		sendKeys(enterskillInputTag, data);
	}
	
	public void clickOnSaveBtn() {
		clickSelenium(clickOnSaveBtn);
		localWait(5000);
	}
	
	
	
	
	

}
