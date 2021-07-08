package com.verivox.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import io.cucumber.java8.En;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements En {

	public static WebDriver driver;
	static PropertiesReader propertyReader = new PropertiesReader();

	public void initialization() throws Throwable {
		String browserName = propertyReader.getValue("browser");
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(propertyReader.getValue("implicitlyWait")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(Long.parseLong(propertyReader.getValue("setScriptTimeout")),
					TimeUnit.SECONDS);
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(propertyReader.getValue("implicitlyWait")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(Long.parseLong(propertyReader.getValue("setScriptTimeout")),
					TimeUnit.SECONDS);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(propertyReader.getValue("url"));
	}

	public void quitDriver() {
		driver.close();
		driver.quit();
	}

}
