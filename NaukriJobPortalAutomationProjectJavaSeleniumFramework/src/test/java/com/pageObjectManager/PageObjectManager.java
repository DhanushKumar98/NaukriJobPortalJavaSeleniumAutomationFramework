package com.pageObjectManager;

import com.pageObjectModules.BasicDetailsPageObjectModules;
import com.pageObjectModules.KeySkillsPageObjectModules;
import com.pageObjectModules.LoginPageObjectModules;
import com.pageObjectModules.LoginPageObjectModulesWithInvalidCredential;
import com.pageObjectModules.ProfileSummaryPageObjectModules;
import com.pageObjectModules.ProfileUpdatePageObjectModules;
import com.pageObjectModules.ResumeHeadlinePageObjectModules;
import com.pageObjectModules.UploadResumePageObjectModules;
import com.utility.FileReaderManager;

public class PageObjectManager {

	private FileReaderManager fileReaderManager;
	private LoginPageObjectModules loginPageModule;
	private LoginPageObjectModulesWithInvalidCredential loginPageModuleWithInvalidData;
	private ProfileUpdatePageObjectModules profileUpdatePageModule;
	private BasicDetailsPageObjectModules basicDetailsPageModule;
	private ResumeHeadlinePageObjectModules resumeheadlinePageModule;
	private KeySkillsPageObjectModules keySkillsPageModule;
	private ProfileSummaryPageObjectModules profileSummaryPageModule;
	private UploadResumePageObjectModules uploadResumePageModule;

	public FileReaderManager getFileReaderManager() {
		if (fileReaderManager == null) {
			fileReaderManager = new FileReaderManager();
		}
		return fileReaderManager;
	}

	public BasicDetailsPageObjectModules getBasicDetailsPageModule() {
		if (basicDetailsPageModule == null) {
			basicDetailsPageModule = new BasicDetailsPageObjectModules();
		}
		return basicDetailsPageModule;
	}

	public LoginPageObjectModulesWithInvalidCredential getLoginPageModuleWithInvalidData() {
		if (loginPageModuleWithInvalidData == null) {
			loginPageModuleWithInvalidData = new LoginPageObjectModulesWithInvalidCredential();
		}
		return loginPageModuleWithInvalidData;
	}

	public LoginPageObjectModules getLoginPageModule() {
		if (loginPageModule == null) {
			loginPageModule = new LoginPageObjectModules();
		}
		return loginPageModule;
	}

	public ProfileUpdatePageObjectModules getProfileUpdatePageModule() {
		if (profileUpdatePageModule == null) {
			profileUpdatePageModule = new ProfileUpdatePageObjectModules();
		}
		return profileUpdatePageModule;
	}

	public ResumeHeadlinePageObjectModules getResumeheadlinePageModule() {
		if (resumeheadlinePageModule == null) {
			resumeheadlinePageModule = new ResumeHeadlinePageObjectModules();
		}
		return resumeheadlinePageModule;
	}

	public KeySkillsPageObjectModules getKeySkillsPageModule() {
		if (keySkillsPageModule == null) {
			keySkillsPageModule = new KeySkillsPageObjectModules();
		}
		return keySkillsPageModule;
	}

	public ProfileSummaryPageObjectModules getProfileSummaryPageModule() {
		if (profileSummaryPageModule == null) {
			profileSummaryPageModule = new ProfileSummaryPageObjectModules();
		}
		return profileSummaryPageModule;
	}
	

	public UploadResumePageObjectModules getUploadResumePageModule() {
		if (uploadResumePageModule == null) {
			uploadResumePageModule = new UploadResumePageObjectModules();
		}
		return uploadResumePageModule;
	}

}
