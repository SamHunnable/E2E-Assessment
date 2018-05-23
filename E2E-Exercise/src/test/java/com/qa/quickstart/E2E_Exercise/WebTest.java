package com.qa.quickstart.E2E_Exercise;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class WebTest {
	
	WebDriver driver = null;
	Actions action = null;
	
	@Before
	private void setup() {
		System.setProperty("webdriver.chrome.driver", Constants.pathToWebDriver);
		driver = new ChromeDriver();
		Actions action = new Actions(driver);
	}
	
	@After
	private void tearDown() {
		driver.quit();
	}
	
	
	@Test
	public void i_can_access_the_TestData_file() throws Throwable {
		driver.Navigate.To("https://vehicleenquiry.service.gov.uk/");
		DVLAStartPage startPage = PageFactory.initElements(driver, DVLAStartPage.class);
		startPage.registrationSearch(driver, action);
	
	    
	}
}