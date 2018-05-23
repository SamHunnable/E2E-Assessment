
Feature: Comparing car models and colours

	Scenario: Reading from excel file
		Given I can access the TestData file
		When I read from the file
		Then I get the assigned data
		
		Scenario: Finding a vehicle
		Given I have an excel file with registration plate numbers
		When I enter the registration in the DVLA website 
		Then I get the vehicle details
		
		Scenario: Getting vehicle make and colour
		Given I have access to a vehicle details page
		When I compare the make and colour to the file
		Then The make and colour match

