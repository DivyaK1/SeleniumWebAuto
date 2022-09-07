package com.opencart.test;


import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.Constants;
import com.opencart.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
@Epic("Epic : this epic is for Accounts page of open cart application")
@Story("ACCPAGE : design Accounts page with various features")
public class AccountsPageTest extends BaseTest{
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		//accPage = new AccountsPage(driver);
	}

	@Test(priority =1)
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		System.out.println("Acc page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority =2)
	public void accPageURLTest() {
		String actURL = accPage.getAccountsPageURL();
		System.out.println("Acc page URL: " + actURL);
		Assert.assertTrue(actURL.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}


	@Test(priority =3)
	public void accPageSectionsTest() {
		List<String> actAccSecList = accPage.getAccountsPageSectionsList();
		System.out.println("Act Accounts Section list: " + actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACCOUNTS_SECTION_LIST);
	}

	@Test(priority =3)
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test (priority=4)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test(priority =3)
	public void logoutTest() {
		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccessMessg(), Constants.LOGOUT_SUCCESS_MESSG);
	}


	@Test(dataProvider="getsearchKey",priority =5 )
	public void doSearchTest(String searchKey) {
		rsPage= accPage.doSearch(searchKey);
		Assert.assertTrue(rsPage.doSearchCount() > 0 ); 
	}

	@DataProvider
	public Object[][] getsearchKey() {
		//return  new Object[][] { {"Macbook"}, {"Samsung"},{"iMac"}, {"Apple"} };
		return new Object[][] { { "iMac" }, { "Apple" }, { "Samsung" } };

	}
	@Test(dataProvider= "getProductNameData",priority =6, enabled= false)
	public void searchProductTest(String searchKey, String expectedProductName) {
		rsPage= accPage.doSearch(searchKey);
		prodPage= rsPage.selectProduct(expectedProductName);
		String actualProductName= prodPage.getProductHeaderName();
		//System.out.println("test: "+ actualProductName);
		Assert.assertEquals(actualProductName, expectedProductName);

	}
	@DataProvider
	public Object[][] getProductNameData() {
		return  new Object[][] { { "Macbook","MacBook Air" }, { "iMac","iMac"}, { "Apple", "Apple Cinema 30\""}, {"Samsung","Samsung SyncMaster 941BW"}};
	}
	
}



