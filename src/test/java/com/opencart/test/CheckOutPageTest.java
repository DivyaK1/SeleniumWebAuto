package com.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.Constants;

public class CheckOutPageTest extends BaseTest {
	@BeforeClass
	public void checkPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		rsPage = accPage.doSearch("Macbook");
		prodPage = rsPage.selectProduct("MacBook Pro");
		prodPage = prodPage.enterQty("2").addtoCart();
		checkoutPage= prodPage.onShoppingCartClick();
	}
	@Test
    public void checkOutProdDataTest() {
    	Map<String,String>checkoutInfoMap=checkoutPage.checkOutProdData();
    	softAssert.assertEquals(checkoutInfoMap.get("prodName"), "MacBook Pro");
    	softAssert.assertEquals(checkoutInfoMap.get("price"), "$2,000.00");
    	softAssert.assertEquals(checkoutInfoMap.get("quantity"), "2");
    	softAssert.assertEquals(checkoutInfoMap.get("Totprice"), "$4,000.00");
    	softAssert.assertAll();
    }
	@AfterClass
	public void removeQtyTest() {
		String actualMsg = checkoutPage.removeQty();
		Assert.assertEquals(actualMsg, Constants.CHECK_OUT_PAGE_EMPTY_MSG);
		
	}
}
