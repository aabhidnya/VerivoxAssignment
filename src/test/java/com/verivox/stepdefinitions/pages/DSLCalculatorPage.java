package com.verivox.stepdefinitions.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.verivox.utility.BaseTest;
import com.verivox.utility.TestUtils;

public class DSLCalculatorPage extends BaseTest {
	public static int listOfTariffs=0;
	public static int countInHeader;
	
	@FindBy(tagName="h1")
	private WebElement pageHeader;
	
	@FindBy(xpath="//div[@class='hero-content']//button[text()='Jetzt vergleichen']")
	private WebElement jetztVergleichenButton;
	
	@FindBy(tagName = "app-tariff")
	private List<WebElement> tariffPlans;
	
	@FindBy(xpath = "//app-tariff-speed/div[contains(@class,'internet-speed-download')]//b")
	private List<WebElement> downloadSpeed;
	
	@FindBy(xpath = "//app-tariff-cta/a[contains(text(), 'Zum Angebot')]")
	private List<WebElement> zumAngebotButtonList;
	
	public DSLCalculatorPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void enterAreaCode(String areaCode) { 
		WebElement ele = driver.findElement(By.xpath("//div[@class='hero-content']//input[@name='phonePrefix']"));
		new TestUtils().waitForElementClickable(ele);
		ele.click();
		new TestUtils().clear(ele);
		ele.sendKeys(areaCode);
	}
	
	public void selectBandWidth(int bandWidth) {
		String xpath = "//div[@class='hero-content']//label[*[contains(text(),'"+bandWidth+"')] and text()='MBit/s']";
		WebElement ele = driver.findElement(By.xpath(xpath));
		ele.click();
	}
	
	public void clickOnJetztVergleichenButton() {
		jetztVergleichenButton.click();
	}
	public void getTotalCountFromHeader() {
		String text = driver.findElement(By.cssSelector("h2.summary-tariff")).getText();
		countInHeader = Integer.parseInt((text).replaceAll("[\\D]", ""));
	}
	
	public int getCountOfErmittelteTarife() throws Throwable{
		//new TestUtils().scrollToView(driver.findElement(By.xpath("(//app-tariff)[last()]")));
		Thread.sleep(2000);
		new TestUtils().waitForAllElementVisibility(tariffPlans);
		listOfTariffs = tariffPlans.size();
		return tariffPlans.size();
	}
	
	public List<Integer> getDowloadSpeedForDisplayedPlans() {
		List<Integer> speedValues = new ArrayList<Integer>();
		for (WebElement ele : downloadSpeed) {
			speedValues.add(Integer.parseInt(ele.getText().trim()));
		}
		return speedValues;
	}
	
	public void scrollToViewLoadMoreTaiff() {
		new TestUtils().scrollToView(driver.findElement(By.xpath("(//app-tariff)[last()]")));
	}
	
	//loading all tariff plans and as well as Verifying that the final weitere Tarife laden button displays the expected number of tariffs remaining 
	public void loadAllTariff() throws Throwable {
		new TestUtils().scrollToView(driver.findElement(By.xpath("(//app-tariff)[last()]")));
		int displayedCount = tariffPlans.size();
		int numOnButton = 0;
		if(displayedCount < countInHeader) {     
			int reminder = (countInHeader-displayedCount)/20;   
			int mod = (countInHeader-displayedCount) % 20; 
			for(int i=reminder; i>=0;i--){
				if(i==0) {
					numOnButton=mod;
					WebElement button = driver.findElement(By.xpath("//button[contains(text(),'"+numOnButton+" weitere tarife laden')]"));
					button.click();
					Thread.sleep(4000);
					new TestUtils().scrollToView(driver.findElement(By.xpath("(//app-tariff)[last()]")));
				} else {
					numOnButton = 20;					
					WebElement button = driver.findElement(By.xpath("//button[contains(text(),'"+numOnButton+" weitere tarife laden')]"));
					button.click();
					new TestUtils().scrollToView(driver.findElement(By.xpath("(//app-tariff)[last()]")));
					Thread.sleep(4000);
				}
			}
		}else {
			System.out.println("no item are there to expand");
		}
		
		System.out.println("all tariffs loaded");
	}
	
	public void clickOnToTheRandomOfferButton() {
		Random r = new Random();
		int randomValue;
		randomValue = r.nextInt(zumAngebotButtonList.size()-1);
		System.out.println("random value is "+randomValue);
		zumAngebotButtonList.get(randomValue).click();
	}
	
	public void validateExpectedNumbersOnButton() {
		
	}

}
