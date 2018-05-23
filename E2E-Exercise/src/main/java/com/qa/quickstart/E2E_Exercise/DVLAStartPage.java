package com.qa.quickstart.E2E_Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DVLAStartPage {
	
	public DVLAStartPage(WebDriver driver) {
		
		driver.findElement(By.id("Vrm"));
	
	}
	
	public void registrationSearch(WebDriver driver) {
		driver.get(Constants.DVLAStartPage);
	}
	

}
