package com.interFaceElements;

public interface LoginPageInterFaceElements {

	String username_css = "#usernameField";
	String password_css = "#passwordField";
	String loginBtn_xpath = "//button[text()='Login']";
	String errorMessage_xpath = "//div[text()='Invalid details. Please check the Email ID - Password combination.']";
	String showPasswordBtn_xpath = "//small[text()='Show']";
	String forgetPassword_xpath = "//small[text()='Forgot Password?']";
	String useOtpToLogin_xpath = "//*[text()='Use OTP to Login']";
	String registerForFree_xpath = "//a[text()='Register for Free']";

}
