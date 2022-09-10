package com.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.Constants;
import com.opencart.utils.ExcelUtil;

public class ProductInfoPageTest  extends BaseTest {
	@BeforeClass
	 public void prodSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		rsPage = accPage.doSearch("Macbook");
		prodPage = rsPage.selectProduct("MacBook Pro"); 
	 }
	@DataProvider
	public Object[][] getProductData() {
		//return  new Object[][] { { "Macbook","MacBook Air", 4 }, { "iMac","iMac", 3 }, { "Apple", "Apple Cinema 30\"", 6}, {"Samsung","Samsung SyncMaster 941BW", 1}};
		Object[][] prodData=  ExcelUtil.getTestData(Constants.PRODUCT_SHEET_DATA);
		return prodData;
	}

	@Test(dataProvider="getProductData",priority =1)
	public void prodImgCountTest(String searchKey, String expectedProductName, String imgCount) {
		rsPage= accPage.doSearch(searchKey);
		prodPage= rsPage.selectProduct(expectedProductName);
		int actImgCount= prodPage.getProductImgCount();
		System.out.println("actImgCount : "+ actImgCount);
		//prodPage.enterQty("5");
		int expImgCount=Integer.parseInt(imgCount);
		System.out.println("expImgCount : "+expImgCount);
		Assert.assertEquals(actImgCount, expImgCount);
	}
	@Test(dataProvider="getProductInfoData",priority =2)
	public void getProductInfoTest(String searchKey, String expectedProductName,String prodCode, String price,String available, String tax) {
		rsPage = accPage.doSearch(searchKey);
		prodPage = rsPage.selectProduct(expectedProductName);
		Map<String, String> actProductInfoMap = prodPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), prodCode);
		softAssert.assertEquals(actProductInfoMap.get("price"), price);
		softAssert.assertEquals(actProductInfoMap.get("Availability"), available);
		softAssert.assertEquals(actProductInfoMap.get("Ex Tax"), tax);
		softAssert.assertAll();
	}
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] 
				{{ "Macbook","MacBook Pro", "Product 18", "$2,000.00","In Stock","$2,000.00"}, { "iMac","iMac", "Product 14", "$100.00","In Stock","$100.00"}};
	}
	@Test(enabled = false)
	public void productInfoDescriptionTest() {
		rsPage = accPage.doSearch("Macbook");
		prodPage = rsPage.selectProduct("MacBook Pro");
		softAssert.assertTrue(prodPage.getProductInfoPageInnerText().contains("Latest Intel mobile architecture"));
		softAssert.assertTrue(prodPage.getProductInfoPageInnerText().contains("new Core 2 Duo MacBook Pro is over 50%"));
		softAssert.assertTrue(prodPage.getProductInfoPageInnerText().contains("Connect. Create. Communicate."));
		softAssert.assertAll();
	}
	@Test(priority =3)
	public void addToCartTest() {
		rsPage = accPage.doSearch("Macbook");
		prodPage = rsPage.selectProduct("MacBook Pro");
		String msg= prodPage.enterQty("2").addtoCart().getSuccessMsg();
		Assert.assertTrue(msg.contains("MacBook Pro"));
		
		
	}
	@Test(priority =4)
	public void viewCartTest() throws InterruptedException  {
//		rsPage = accPage.doSearch("Macbook");
//		prodPage = rsPage.selectProduct("MacBook Pro");
//		prodPage = prodPage.enterQty("2").addtoCart();
		prodPage.removeProduct();
		checkoutPage= prodPage.onShoppingCartClick();
		String actual = checkoutPage.getCheckOutHeaderName();
		Assert.assertTrue(actual.contains("Shopping Cart"));
	}
//	@AfterClass
//	public void emptyCart() {
//		prodPage.removeProduct();
//	}

}
