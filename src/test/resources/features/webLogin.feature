Feature: Web Login
  Scenario Outline: Successful login using credentials
    Given I open the browser for web "<config>"
    When I login with username "<username>" and password "<password>"
  #  Then I should see dashboard welcome

	
    
    Examples:
  | config           | username | password | 
  | web.properties   | Admin    | admin123 |
     