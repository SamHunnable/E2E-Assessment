package com.qa.quickstart.E2E_Exercise;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DVLAStartPage {
		
		
		//@FindBy(id = "Vrm") private WebElement startButton; 
		@FindBy(xpath = "//*[@id=\"get-started\"]/a") private WebElement startButton; 
		
		

	public void goToSearch(Actions action) {
		action.click(startButton).perform();
				
	}
	

}
