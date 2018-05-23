package com.qa.quickstart.E2E_Exercise;

import static org.junit.Assert.*;

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
	
	
	@Given("^I can access the TestData file$")
	public void i_can_access_the_TestData_file() throws Throwable {
		driver.get(Constants.DVLAStartPage);
		DVLAStartPage startPage = PageFactory.initElements(driver, DVLAStartPage.class);
		startPage.registrationSearch(driver, action);
	
	    
	}

	@When("^I read from the file$")
	public void i_read_from_the_file() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("^I get the assigned data$")
	public void i_get_the_assigned_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Given("^I have an excel file with registration plate numbers$")
	public void i_have_an_excel_file_with_registration_plate_numbers() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("^I enter the registration in the DVLA website$")
	public void i_enter_the_registration_in_the_DVLA_website() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    ;
	}

	@Then("^I get the vehicle details$")
	public void i_get_the_vehicle_details() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
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

	
}
