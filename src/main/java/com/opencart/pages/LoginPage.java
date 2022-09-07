package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.Constants;
import com.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eu;
	//private AccountsPage accPage;
	// locators
	private By emailId=By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPswdLink =By.linkText("Forgotten Password");
	private By registerLink =By.linkText("Register");
	private By logoutSuccessMesg = By.cssSelector("div#common-success h1");
	//constructors
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eu= new ElementUtil(driver);
	}
	
	//page actions
	@Step("getting login page title of open cart app...")
	public String getPageTitle() {
		return eu.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);		
	}
	@Step("getting login page url of open cart app...")
	public String getPageUrl() {
		return eu.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);		
	}
	@Step("user is able to login with username: {0} and password: {1}")
	public AccountsPage doLogin(String email, String pswd) {	
		eu.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(email);
		eu.doSendKeys(password, pswd);
		eu.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	@Step("isForgotPwdLinkExist...")
	public boolean isForgotPswdExists() {
		return eu.doIsDisplayed(forgotPswdLink);
	}
	@Step("isRegisterLinkExist...")
	public boolean isRegisterExists() {
		return eu.doIsDisplayed(registerLink);
	}
	@Step("fetching success messg for logout...")
	public String getLogoutSuccessMessg() {
		return eu.waitForElementVisible(logoutSuccessMesg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	@Step("navigating to register page after clicking on register link....")
	public RegisterPage goToRegisterPage() {
		eu.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
