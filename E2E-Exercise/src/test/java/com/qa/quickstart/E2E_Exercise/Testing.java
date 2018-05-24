package com.qa.quickstart.E2E_Exercise;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
	ExtentReports extent;
	ExtentTest test;
	TimeUnit tu=TimeUnit.SECONDS; 
	//Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, tu).pollingEvery(1, tu).ignoring(NoSuchElementException.class);
	
	@Before
	private void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", Constants.pathToWebDriver);
		driver = new ChromeDriver();
		action = new Actions(driver);
		ExcelUtils.setExcelFile(Constants.pathToFile, 0);
		System.out.println("Before ran");		
	}
	
	
	@Given("^I have an excel file with registration plate numbers$")
	public void i_have_an_excel_file_with_registration_plate_numbers() throws Throwable {
		
		extent = new ExtentReports(Constants.pathToReport, true);
		test = extent.startTest("License Plate testing");
		test.log(LogStatus.INFO, "Scenario begins");
		
		System.setProperty("webdriver.chrome.driver", Constants.pathToWebDriver);
		driver = new ChromeDriver();
		action = new Actions(driver);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		ExcelUtils.setExcelFile(Constants.pathToFile, 0);
		ExcelUtils.setExcelFile(Constants.pathToFile, 0);
		file = new FileInputStream(Constants.pathToFile);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0);
	}
	
	
	@Given("^I am on the DVLA page$")
	public void i_am_on_the_DVLA_page() throws Throwable {
		driver.get(Constants.DVLAStartPage);
		
		String title = "Get vehicle information from DVLA - GOV.UK";
		assertEquals(title, driver.getTitle());
		if (title.equals(driver.getTitle())) {
			test.log(LogStatus.PASS, "On correct page");
		}
		else {
			test.log(LogStatus.FAIL, "Not on start page");
		}
	}
	
	
	@When("^I enter the registration in the DVLA website$")
	public void i_enter_the_registration_in_the_DVLA_website() throws Throwable {
		
		Thread.sleep(3000);
		DVLAStartPage startPage = PageFactory.initElements(driver, DVLAStartPage.class);
		startPage.goToSearch(action);
		Thread.sleep(3000);
		DVLASearchPage searchPage = PageFactory.initElements(driver, DVLASearchPage.class);
		
		
		for (int i=1 ; i < sheet.getPhysicalNumberOfRows() ; i++) {
		//for (int i=0 ; i < 1 ; i++) {
			plate = sheet.getRow(i).getCell(0);
			licensePlate = plate.getStringCellValue();  
			test.log(LogStatus.INFO, "Searching for license plate");
			searchPage.searchPlates(driver, licensePlate);
			
			ResultPage resultPage = null;
			if (resultPage == null) {
				resultPage = PageFactory.initElements(driver, ResultPage.class);
			}
					
			plate = sheet.getRow(i).getCell(1);			
			String make= plate.getStringCellValue(); 	
					 
			assertEquals(make, resultPage.getMake());
			if (make.equals(resultPage.getMake())) {
				test.log(LogStatus.PASS, "Model matches");
			}
			else {
				test.log(LogStatus.FAIL, "Model does not match");
			}
					
			plate = sheet.getRow(i).getCell(2);
			String colour= plate.getStringCellValue(); 
					
					
					
			assertEquals(colour, resultPage.getColour());
			if (colour.equals(resultPage.getColour())) {
				test.log(LogStatus.PASS, "Colour matches");
			}
			else {
				test.log(LogStatus.FAIL, "Colour does not match");
			}
			
			ExcelUtils.setCellData(resultPage.getMake(), i, 4);
			ExcelUtils.setCellData(resultPage.getColour(), i, 5);
			
			driver.get(Constants.DVLAStartPage);
			Thread.sleep(1500);
			startPage.goToSearch(action);
		}
	}
	
	
	@Then("^I get the vehicle information and it matches with the file$")
	public void i_get_the_vehicle_information() throws Throwable {
		
	
			
		driver.quit();
		workbook.close();
		extent.endTest(test);
		extent.flush();
	}
	
	
	@After
	private void tearDown() throws IOException {
		driver.quit();
		workbook.close();
		System.out.println("After ran");
	}
	
	
}
