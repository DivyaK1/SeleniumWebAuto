package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.Constants;
import com.opencart.utils.ElementUtil;

public class ResultSearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ResultSearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	private By searchResult = By.cssSelector(".product-layout.product-grid");
	private By productLink;
	
	public int doSearchCount() {
		return eleUtil.waitForElementsVisible(searchResult, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}
    public ProductInfoPage selectProduct(String productName) {
    	productLink= By.linkText(productName);
    	eleUtil.doClick(productLink);
    	return new ProductInfoPage(driver);
	}
}
