package com.qa.quickstart.E2E_Exercise;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebTest {

	WebDriver driver;
	Actions action;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		Actions action = new Actions(driver);
		//WebDriverWait wait = new WebDriverWait(WebDriverReference,5);
	}

	@Test
	public void i_can_access_the_TestData_file() throws InterruptedException {
		driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
	
		DVLAStartPage startPage = PageFactory.initElements(driver, DVLAStartPage.class);
		startPage.registrationSearch(driver, action);
		Thread.sleep(5000);
		DVLASearchPage searchPage = PageFactory.initElements(driver, DVLASearchPage.class);
		searchPage.searchPlates(driver);
		Thread.sleep(5000);
		assertEquals("MAZDA", driver.findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong")).getText());
	}
	
	@After
	public void tearDown() {
		driver.quit();	
		
	}
}