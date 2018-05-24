
Feature: Comparing car models and colours


	Scenario: Reading from an excel file and retrieving car information
	Given I have an excel file with registration plate numbers
	And I am on the DVLA page
	When I enter the registration in the DVLA website 
	Then I get the vehicle information and it matches with the file
