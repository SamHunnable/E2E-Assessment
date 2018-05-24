package com.qa.quickstart.E2E_Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DVLASearchPage {

	//@FindBy(id = "Vrm") private WebElement licenseSearch;
	@FindBy(xpath = "//*[@id=\"Vrm\"]") private WebElement licenseSearch;
	@FindBy(name = "Continue") private WebElement next;
	
	
	
	public void searchPlates(WebDriver driver, String licensePlate) {
		Actions action = new Actions(driver);
		
		action.click(licenseSearch).sendKeys(licensePlate).click(next).perform();
	
	}
	
}
