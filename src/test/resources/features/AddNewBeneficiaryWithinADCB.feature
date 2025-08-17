Feature: Add New Beneficiary within ADCB
 
  Scenario: Add a new beneficiary
    When user navigates to Add Beneficiary page within ADCB
#    And user enters beneficiary details with name "Test User", account "123456", bank "Bank A"
#    Then beneficiary should be added successfully

Examples:
  | Name        | AccountNumber | BankName   |
  | TestUser1   | 123456        | Bank A     |
  | TestUser2   | 789012        | Bank B     |