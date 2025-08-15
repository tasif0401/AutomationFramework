
Feature: Mobile Login
  Scenario Outline: Mobile login using credentials
    Given I open the app on mobile with config "<config>"
    When I perform mobile login with "<username>" and "<password>"
    Then I should see mobile dashboard

    Examples:
      | config           | username | password |
      | android.properties | user1    | pass1    |
