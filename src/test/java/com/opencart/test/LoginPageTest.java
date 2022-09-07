package com.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic : this epic is for login page of open cart application")
@Story("LOGIN : design login page with various features")
public class LoginPageTest extends BaseTest {
	
	@Description("login page title test....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority =1)
	public void loginPageTitleTest() {
		String actual= loginPage.getPageTitle();
		Assert.assertEquals(actual, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("login page url test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =2)
	public void loginPageUrlTest() {
		String actURL= loginPage.getPageUrl();
		Assert.assertTrue(actURL.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	@Description("forgot pwd link test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority =3)
	public void forgotPswdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPswdExists());
	}
	@Description("register link exist test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority =4)
	public void registerLinkExistTest() {
		Assert.assertTrue(loginPage.isRegisterExists());
	}
	@Description("user is able to login to open cart application test....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority =5)
	public void logintest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim()).isLogoutLinkExist());
	}
}
