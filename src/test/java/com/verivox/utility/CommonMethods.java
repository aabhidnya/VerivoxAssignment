package com.verivox.utility;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CommonMethods {

	public boolean isCookiePolicyPreferencesEnabled(WebDriver driver) throws Throwable {
		try {
			if (driver.findElement(By.xpath("//h2[text()='Cookie-Einstellungen']")).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception x) {
			return false;
		}
	}

	public static void captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(source, new File("./Screenshots/" + screenshotName + ".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}
}
