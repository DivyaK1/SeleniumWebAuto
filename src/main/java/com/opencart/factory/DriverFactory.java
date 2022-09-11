package com.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionManager;
	public static ThreadLocal<WebDriver>tlDriver = new ThreadLocal<WebDriver>();
	/*
	 * this method is used to initialize webdriver on bases of browser name
	 *@param browserName
	 *@return driver */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		//String browserName = System.getProperty("browser");
		System.out.println("Passed browserName is : "+browserName);
		optionManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver= new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver(optionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			driver= new SafariDriver();
		}
		else {
			System.out.println("Please pass valid browser");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/*
	 * this method is used to initialize the properties*/
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip= null;
		String envName= System.getProperty("env");
		System.out.println("Running tests on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given....hence running it on QA enviornment");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					System.out.println("running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					System.out.println("running it on stage env");
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					System.out.println("running it on dev env");
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					System.out.println("running it on uat env");
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					System.out.println("running it on prod env");
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Please pass the right environment...." + envName);
					throw new FrameworkException("no env is found exception....");
				// break;
				}

			} catch (Exception e) {

			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./" + "screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
