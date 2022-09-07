
package com.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.opencart.factory.DriverFactory;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.CheckOutPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.ProductInfoPage;
import com.opencart.pages.RegisterPage;
import com.opencart.pages.ResultSearchPage;

public class BaseTest {
	public DriverFactory df;
	public WebDriver driver;
	protected LoginPage loginPage;
	public Properties prop;
	protected AccountsPage accPage;
	protected ResultSearchPage rsPage;
	protected ProductInfoPage prodPage;
	protected SoftAssert softAssert;
	protected CheckOutPage checkoutPage;
	protected RegisterPage registerPage;
	
	@BeforeTest
	public void setup() {
		df= new DriverFactory();
		prop=df.init_prop();
		driver= df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
