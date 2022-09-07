package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.constants.Constants;
import com.opencart.utils.ElementUtil;

public class AccountsPage{

	private WebDriver driver;
	private ElementUtil eleUtil;

	
	private By logoutLink = By.linkText("Logout");
	private By sectionsHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageURL() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	
	public List<String> getAccountsPageSectionsList() {
		List<WebElement> secList = eleUtil.getElements(sectionsHeaders);
		List<String> secValList = new ArrayList<String>();
		for (WebElement e : secList) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public LoginPage clickOnLogout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
		return new LoginPage(driver);
	}

	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	
	public ResultSearchPage doSearch(String searchValue) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchValue);
			eleUtil.doClick(searchIcon);
			return new ResultSearchPage(driver);
		}
		return null;
	}
	
	
}
