package com.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.Constants;
import com.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registerPage=loginPage.goToRegisterPage();
	}
	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}


	public String getRandomEmail() {
		Random random = new Random();
		String email = "automation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}



	@Test(dataProvider = "getRegisterTestData")
	public void registerUserTest(String firstname, String lastname, 
			String telephone, String password, String subscribe) {
		Assert.assertTrue(
				registerPage.registerUser(firstname, lastname, getRandomEmail(), telephone,  password,  subscribe));
				//registerPage.registerUser(firstname, lastname, email, telephone,  password,  subscribe));
				
	}
}
