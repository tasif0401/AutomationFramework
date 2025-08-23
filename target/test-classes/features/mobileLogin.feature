
Feature: Mobile Login

  Scenario Outline: Mobile login using credentials
    Given I open the app on mobile with "<config>"
    When I perform mobile login with "<password>"
    Then I should see mobile Account Summary dashboard    
 	When user navigates to Add Beneficiary page within ADCB
  	When User naviagtes to Existing Beneficiary page
   	And user should validate the Added Beneficiary Name
   	
      Examples:  
       | config             |
       | android.lambdatest.properties |
 	
   	 
	 Scenario Outline: Fund Transfer Exsiting Baneficiary 
 		When user Searches for Existing Beneficiary with Name "<Name>"
 		And user selects Existing Beneficiary with Name
 		And user Select the source Account with Positive balance
 		Then user Should enter the Transfer Deatils 
 		And user should navigate to Fund Transfer Review page
 		And user should validate the Fund Transfer Review page
 		Then user should navigate to Fund Transfer Success page
 		And user should validate the Fund Transfer Success page
 		
 	Scenario: Account Summmary Dashboard validate Amount is Deductead or not
 		When user navigates to Account Summary Dashboard
 		Then user should validate the Amount is Deducted or not in source account
