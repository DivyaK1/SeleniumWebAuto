package com.opencart.pages;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.constants.Constants;
import com.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String>prodInfo;

	private By productHeader = By.tagName("h1"); //By.cssSelector("div#content h1");
	private By productImg = By.xpath("//ul[@class='thumbnails']//img");
	private By qty = By.cssSelector("input#input-quantity");
	private By addToCart = By.cssSelector("button#button-cart");
	private By successMsg = By.cssSelector("div.alert-success.alert ");
	private By prodData =  By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By prouductPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By cartData = By.cssSelector("div#cart  span#cart-total");
	private By remove= By.xpath("//table[@class='table table-striped']//child::button");
	private By shoppingCart = By.xpath("(//div[@id='top-links']/ul/li)[4]/a");
	private By blackBtn= By.xpath("//div[@id='cart']/button[@type='button']");
	////*[@id="cart"]/ul/li[1]/table/tbody/tr/td[5]/button
	private By myAccLogout= By.xpath("((//div[@id='top-links']/ul/li)[2]/ul/li)[last()]/a");
	private By loginLink =By.linkText("Login");
	private By logoutSuccessMesg = By.cssSelector("div#common-success h1");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
    
    public String getProductHeaderName() {
		return eleUtil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
    
    public int getProductImgCount() {
    	return eleUtil.waitForElementsVisible(productImg, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
    }
    public ProductInfoPage enterQty(String value) {
    	 eleUtil.doSendKeys(qty, value);
    	 return this;
    }
    public ProductInfoPage addtoCart() {
//    	eleUtil.waitForElementToBeClickableWithPolling(addToCart, Constants.DEFAULT_ELEMENT_TIME_OUT, Constants.DEFAULT_POLLING_TIME);
    	 eleUtil.doClick(addToCart);
//    	String name= eleUtil.doGetText(prodNameCart);
//    	 System.out.println("name : "+name);
    	 return this;
    }
    
    public String getSuccessMsg() {
    	 return eleUtil.waitForElementPresence(successMsg, Constants.DEFAULT_ELEMENT_TIME_OUT ).getText();
    }
    
    
    public Map<String, String> getProductInfo() {
    	prodInfo = new HashMap<String,String>();
    	prodInfo.put("prodName", getProductHeaderName());
    	getProdData();
    	getProdPrice();
    	prodInfo.forEach((k,v)-> System.out.println(k + ":" + v));
    	return prodInfo;
    }
    
    private void getProdData() {
    	List<WebElement>prodDataList= eleUtil.getElements(prodData);
    	for(WebElement e: prodDataList) {
    		 String meta[] = e.getText().split(":");
    		 String metaKey = meta[0].trim();
    		 String meatValue = meta[1].trim();
    		  prodInfo.put(metaKey, meatValue);
    	}
    	// prodInfo.forEach((k,v)->System.out.println("===="+k + ":" + v));
    }
    
    private void getProdPrice() {
    	List<WebElement>prodPriceList= eleUtil.getElements(prouductPriceData);
    	String price= prodPriceList.get(0).getText().trim();
    	//System.out.println("===============price : "+price);
    	prodInfo.put("price", price);
    	String exTax = prodPriceList.get(1).getText().trim();
    	String temp[]= exTax.split(":");
    	String exTaxKey= temp[0].trim();
    	String exTaxValue= temp[1].trim();
    	//System.out.println("===============exTax"+exTaxKey+" : "+exTaxValue);
    	prodInfo.put("price", price);
    	prodInfo.put(exTaxKey, exTaxValue);
    }

    public String getProductInfoPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String pageInnerText = js.executeScript("return document.documentElement.innerText").toString();
		System.out.println("==============================\n" + pageInnerText + "\n=============");
		return pageInnerText;
	}
    
    public String shoppingCartDetails() {
    	return eleUtil.doGetText(cartData);
    }
  /*  
    public boolean menuBarLogoutExists() {
    	return eleUtil.waitForElementVisible(myAccLogout, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
    	//eleUtil.doActionsClick(myAccLogout).isDisplayed();
    }
    public LoginPage clickOnLogout() {
    	if (menuBarLogoutExists()) {
			eleUtil.doClick(myAccLogout);
			//eleUtil.doClick(loginLink);
		}
		return new LoginPage(driver);
    	
	}
    @Step("fetching success messg for logout...")
	public String getLogoutSuccessMessg() {
		return eleUtil.waitForElementVisible(logoutSuccessMesg,20).getText();
	}*/
    
    public void removeProduct() {
    	eleUtil.doClick(blackBtn);
    	eleUtil.waitForElementVisible(remove, Constants.DEFAULT_ELEMENT_TIME_OUT).click();
    	//eleUtil.doActionsClick(remove);
    }
    public CheckOutPage onShoppingCartClick() {
    	eleUtil.doActionsClick(shoppingCart);
    	return new CheckOutPage(driver);
    }
}
