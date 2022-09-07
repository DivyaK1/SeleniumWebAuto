package com.opencart.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.Constants;
import com.opencart.utils.ElementUtil;

public class CheckOutPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private By prodNameLink= By.xpath("//div[@class='table-responsive']//child::tbody//td[2]/a");
	private By prodNameLink= By.partialLinkText("MacBook Pro");
	//private By quantity = By.xpath("//div[@class='table-responsive']//child::tbody//td[4]//input[@class='form-control']");
	private By quantity = By.xpath("(//input[@class='form-control'])[1]");
	private By remove = By.xpath("//div[@class='table-responsive']//child::tbody//td[4]//button[@type='button']");
	private By price = By.xpath("(//div[@class='table-responsive']//child::tbody//td)[last()-1]");
	private By totalPrice = By.xpath("(//div[@class='table-responsive']//child::tbody//td)[last()]");
	private By emptyMsg= By.xpath("(//p[contains(text(),'empty!')])[2]");
	private By checkOutHeader = By.tagName("h1");
	
	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	 public String getCheckOutHeaderName() {
			return eleUtil.waitForElementVisible(checkOutHeader, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		}
	 
	public Map<String, String> checkOutProdData() {
		Map<String,String> cartData= new HashMap<String,String>();
		String product= eleUtil.doGetText(prodNameLink);
		System.out.println("product : "+product);
		cartData.put("prodName", product);
		String prodCount = eleUtil.getAttributeValue(quantity, "value");
		//float qty = Float.parseFloat(prodCount);
		cartData.put("quantity", prodCount);
		String prodPrice= eleUtil.doGetText(price);
		cartData.put("price", prodPrice);
		String prodTotPrice= eleUtil.doGetText(totalPrice);
		cartData.put("Totprice", prodTotPrice);
		return cartData;	
	}
	public String removeQty() {
		eleUtil.doClick(remove);
		String msg=eleUtil.waitForElementVisible(emptyMsg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
//		String msg= eleUtil.doGetText(emptyMsg);
		System.out.println(msg);
		return msg;
	}
}
