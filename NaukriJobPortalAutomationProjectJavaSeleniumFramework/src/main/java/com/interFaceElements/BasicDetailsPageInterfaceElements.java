package com.interFaceElements;

public interface BasicDetailsPageInterfaceElements {
	
	String ClickOnEditBtn_xpath = "//em[text()='editOneTheme']";
	String name_css = "#name";
	String yearInputTag_css = "#exp-years-droopeFor";
	String yearDropDown_xpath = "//*[@id='exp-years-droopeFor']/following::ul/li";
	String monthInputTag_css = "#exp-months-droopeFor";
	String monthDropdown_xpath = "//*[@id='exp-months-droopeFor']/following::ul/li";
	String currentSalary_css = "#totalAbsCtc_id";
	String salaryBreakdownInputTag_css = "#salaryBreakDownDDFor";
	String salaryBreakdown_xpath = "//*[@id='salaryBreakDownDDFor']/following::ul/li";
	String currentLocationIndia_xpath = "//label[text()='India']";
	String currentLocationOutside_xpath = "//label[text()='Outside India']";
	String enterCurrentLocation_css = "#locationSugg";
	String stateInputTag_css = "#location-droopeFor";
	String stateDropdown_xpath = "//*[@id='location-droopeFor']/following::ul/li";
	String selectNoticePeriodServingNoticePeriod_xpath = "//input[@id='hid_noticePeriod']/following::span[6]";
	String selectNoticePeriod15DayorLess_xpath = "//input[@id='hid_noticePeriod']/following::span[1]";
	String clickOnSaveBtn_css = "#saveBasicDetailsBtn";
}
