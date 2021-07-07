package com.verivox.utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils extends BaseTest {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public <T> void selectByValue(WebElement element, T value) {
		if (value != null && element.isDisplayed()) {
			new Select(element).selectByValue(value.toString());
		} else {
			new Select(element).selectByIndex(0);
		}
	}

	public <T> void selectByVisibleTXT(WebElement element, T value) {
		if (value != null && element.isDisplayed()) {
			new Select(element).selectByVisibleText(value.toString());
		} else {
			new Select(element).selectByIndex(0);
		}
	}

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {

		}
	}
	
	public WebElement searchButtonByText(String buttonName) {
		String xpath = "//button[contains(text(),'"+buttonName+"')]";
		if(driver.findElement(By.xpath(xpath)).isDisplayed()) {
			return driver.findElement(By.xpath(xpath));
		} else {
			throw new ElementNotVisibleException(buttonName+" is not found on the page.");
		}
	}
	
	public void clickOnButtonByText(String buttonName) {
		new TestUtils().searchButtonByText(buttonName).click();
	}

	public void implicitWait(int Second) {
		driver.manage().timeouts().implicitlyWait(Second, TimeUnit.SECONDS);
	}

	public void clickOnLink(WebElement element) {
		if (element.isDisplayed()) {
			element.click();
		}
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}

	public void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void handleAlert() {
		waitForAlert();
		driver.switchTo().alert().accept();
	}

	public void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForAllElementVisibility(List<WebElement> allElement) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfAllElements(allElement));
	}

	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement visibilityOfElementLocated(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void inVisibilityOfElementLocated(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void click(WebElement element) {
		try {
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear(WebElement element) {
		try {
			if (element.isDisplayed()) {
				element.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean maximumLength(WebElement element, String actlength) {

		try {

			String length = element.getAttribute("maxlength");
			length.equalsIgnoreCase(actlength);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollToView(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isWebelementPresent(WebElement element) {
		boolean status = false;
		try {
			if (element.isDisplayed()) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public void mouseHoverOnElement(WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
	}

}
