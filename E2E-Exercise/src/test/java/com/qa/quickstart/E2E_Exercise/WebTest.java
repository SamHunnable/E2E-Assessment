package com.qa.quickstart.E2E_Exercise;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	FileInputStream file;
	String licensePlate;
	Cell plate;
	
	
	
		

	@Before
	public void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", Constants.pathToWebDriver);
		driver = new ChromeDriver();
		action = new Actions(driver);
		ExcelUtils.setExcelFile(Constants.pathToFile, 0);
		file = new FileInputStream(Constants.pathToFile);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0);
		
	}

	@Test
	public void i_can_access_the_TestData_file() throws InterruptedException {
		driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
		
		plate = sheet.getRow(0).getCell(0);
		licensePlate = plate.getStringCellValue();  
		System.out.println(licensePlate);
		
		DVLAStartPage startPage = PageFactory.initElements(driver, DVLAStartPage.class);
		startPage.goToSearch(action);
		Thread.sleep(5000);
		DVLASearchPage searchPage = PageFactory.initElements(driver, DVLASearchPage.class);
		searchPage.searchPlates(driver, licensePlate);
		Thread.sleep(5000);
		assertEquals("MAZDA", driver.findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong")).getText());
	}
	
	@After
	public void tearDown() throws IOException {
		driver.quit();	
		workbook.close();
		
	}
}