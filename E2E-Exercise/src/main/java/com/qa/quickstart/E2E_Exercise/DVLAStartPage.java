package com.qa.quickstart.E2E_Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DVLAStartPage {
		
		@FindBy(id = "get-started") private WebElement licenseSearch;
		@FindBy(id = "Vrm") private WebElement startButton; 
		

	public void registrationSearch(WebDriver driver, Actions action) {
		driver.get(Constants.DVLAStartPage);
		startButton.click();
		action.click(licenseSearch).sendKeys("G607 DAN").perform();
			
	}
	

}
