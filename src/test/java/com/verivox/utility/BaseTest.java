package com.verivox.utility;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java8.En;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements En {

	public static WebDriver driver;
	// static CaptureScreenshots screenshots;
	static PropertiesReader propertyReader = new PropertiesReader();

	public void initialization() throws Throwable {
		String browserName = propertyReader.getValue("browser");
		if (browserName.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\aabhi\\Downloads\\chromedriver_win32\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(propertyReader.getValue("implicitlyWait")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(Long.parseLong(propertyReader.getValue("setScriptTimeout")),
					TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(Long.parseLong(propertyReader.getValue("implicitlyWait")),
					TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(Long.parseLong(propertyReader.getValue("setScriptTimeout")),
					TimeUnit.SECONDS);

			driver.manage().window().maximize();
			// driver = new RemoteWebDriver(new URL(prop.getProperty("selenium-server-ip")),
			// options);
		}
		driver.manage().deleteAllCookies();
		driver.get(propertyReader.getValue("url"));
	}

	public void quitDriver() {
		driver.close();
		driver.quit();
	}
}
