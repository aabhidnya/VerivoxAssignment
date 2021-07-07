package com.verivox.stepdefinitions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.verivox.utility.*;

public class HomePage extends BaseTest {
	
	@FindBy(tagName = "h1")
	public WebElement pageHeader;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getPageHeader() {
		return pageHeader;
	}
	
	public void mouseHoverOnSelectedMenu(String MenuName) {
		WebElement menu = driver.findElement(By.xpath("//input[@name='page-navigation-control']/following-sibling::a[contains(text(),'"+MenuName+"')]"));
		new TestUtils().mouseHoverOnElement(menu);
	}
	
	public void clickOnSubMenu(String subMenu) {
		WebElement element = driver.findElement(By.xpath("//div[@class='page-navigation-dropdown']//a[contains(text(),'"+subMenu+"')]"));
		new TestUtils().waitForElementClickable(element);
		element.click();
		
	}
}
