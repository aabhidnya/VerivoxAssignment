package com.verivox.stepdefinitions.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.verivox.utility.BaseTest;
import com.verivox.utility.TestUtils;

public class OfferPage extends BaseTest{

	@FindBy(xpath = "//a[contains(@data-description,'AvailabilityCheckButton')]")
	private List<WebElement> in5MinOnlineWechseln;
	
	
	
	public OfferPage() {
		PageFactory.initElements(driver, this);
	}
	
	public int getCountOfSwitchOnlineButton() {
		new TestUtils().waitForAllElementVisibility(in5MinOnlineWechseln);
		return in5MinOnlineWechseln.size();
	}
}
