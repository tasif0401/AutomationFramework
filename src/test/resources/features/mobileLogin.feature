
Feature: Mobile Login
  Scenario Outline: Mobile login using credentials
    Given I open the app on mobile with config "<config>"
    When I perform mobile login with pass "<password>"
 	Then I should see mobile Account Summary dashboard
 	When user navigates to Add Beneficiary page within ADCB "<AccountNumber>"


    Examples:
      | config           | username | password | AccountNumber |
      | android.properties | user1    | qwerty123    | 403351010001 |
