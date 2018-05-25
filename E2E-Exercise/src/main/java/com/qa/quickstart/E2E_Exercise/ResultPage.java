package com.qa.quickstart.E2E_Exercise;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage {
	
	@FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong") private WebElement make;
	@FindBy(xpath = "//*[@id=\"pr3\"]/div/ul/li[3]/span[2]/strong") private WebElement colour;
	
	
	public String getMake() throws InterruptedException {
		Thread.sleep(3000);
		return make.getText();
	}
	
	public String getColour() {
		return colour.getText();
	}

	
	
	
//	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, SECONDS).pollingEvery(1, SECONDS).ignoring(NoSuchElementException.class);
//	 
//	 
//	 
//	WebElement foo = wait.until(new Function<WebDriver, WebElement>() 
//	{ 
//		public WebElement apply(WebDriver driver) {
//			return driver.findElement(By.id(“exampleID"));
//	 	}
//	 }); 

}
