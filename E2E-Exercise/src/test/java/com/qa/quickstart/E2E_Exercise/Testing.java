package com.qa.quickstart.E2E_Exercise;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Testing {
	
	WebDriver driver;
	Actions action;
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	FileInputStream file;
	String licensePlate;
	Cell plate;
	
	@Before
	private void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", Constants.pathToWebDriver);
		driver = new ChromeDriver();
		Actions action = new Actions(driver);
		ExcelUtils.setExcelFile(Constants.pathToFile, 0);
		System.out.println("Before ran");
			
	}
	
	@Given("^I can access the TestData file$")
	public void i_can_access_the_TestData_file() throws Throwable {	
		file = new FileInputStream(Constants.pathToFile);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0);	
	}

	@When("^I read from the file$")
	public void i_read_from_the_file() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", Constants.pathToWebDriver);
		driver = new ChromeDriver();
		Actions action = new Actions(driver);
		ExcelUtils.setExcelFile(Constants.pathToFile, 0);
		
		plate = sheet.getRow(0).getCell(0);
		licensePlate = plate.getStringCellValue();  
		//assertNotNull(null, licensePlate);
	}

	@Then("^I get the assigned data$")
	public void i_get_the_assigned_data() throws Throwable { 
	    assertEquals("G607 DAN", licensePlate);
	    driver.quit();
		workbook.close();
	}

	@Given("^I have an excel file with registration plate numbers$")
	public void i_have_an_excel_file_with_registration_plate_numbers() throws Throwable {
		
		
		System.setProperty("webdriver.chrome.driver", Constants.pathToWebDriver);
		driver = new ChromeDriver();
		Actions action = new Actions(driver);
		ExcelUtils.setExcelFile(Constants.pathToFile, 0);
	
		file = new FileInputStream(Constants.pathToFile);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0);	
		
		plate = sheet.getRow(0).getCell(0);
		licensePlate = plate.getStringCellValue();  
		
		assertEquals("G607 DAN", licensePlate);
		
		
	    
	}

	@When("^I enter the registration in the DVLA website$")
	public void i_enter_the_registration_in_the_DVLA_website() throws Throwable {
		
		driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
		Thread.sleep(5000);
		DVLAStartPage startPage = PageFactory.initElements(driver, DVLAStartPage.class);
		startPage.registrationSearch(driver, action);
		Thread.sleep(5000);
		DVLASearchPage searchPage = PageFactory.initElements(driver, DVLASearchPage.class);
		searchPage.searchPlates(driver, licensePlate);
		Thread.sleep(5000);
		
	}

	@Then("^I get the vehicle details$")
	public void i_get_the_vehicle_details() throws Throwable {
	    
		assertEquals("MAZDA", driver.findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong")).getText());
		driver.quit();
		workbook.close();
	    
	}

	@Given("^I have access to a vehicle details page$")
	public void i_have_access_to_a_vehicle_details_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("^I compare the make and colour to the file$")
	public void i_compare_the_make_and_colour_to_the_file() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("^The make and colour match$")
	public void the_make_and_colour_match() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	
	
	
	
	@After
	private void tearDown() throws IOException {
		driver.quit();
		workbook.close();
		System.out.println("After ran");
	}
	
}
