
Feature: Customer Functionalities

  Background:
    Given a customer "Hermoine Granger" with an account exists
    And the user is on the bank's home page
    When the user clicks on the "Customer Login" button
    And the user selects "Hermoine Granger" from the dropdown and clicks Login

  @Positive
  Scenario: Customer successfully deposits money
    Given the customer's initial balance is displayed
    When the user navigates to the "Deposit" tab
    And enters the amount "1000" to deposit
    And clicks the "Deposit" button on the transaction page
    Then the message "Deposit Successful" is displayed
    And the account balance is updated correctly after deposit
    
  @Negative
  Scenario: Customer attempts to deposit a zero amount
    Given the customer's initial balance is displayed
    When the user navigates to the "Deposit" tab
    And enters the amount "0" to deposit
    And clicks the "Deposit" button on the transaction page
    Then the account balance should remain unchanged

  @Negative
  Scenario: Customer attempts to deposit a negative amount
    Given the customer's initial balance is displayed
    When the user navigates to the "Deposit" tab
    And enters the amount "-100" to deposit
    And clicks the "Deposit" button on the transaction page
    Then the account balance should remain unchanged
  @Positive
  Scenario: Customer successfully withdraws money
    Given the customer's initial balance is displayed
    When the user navigates to the "Withdrawl" tab
    And enters the amount "300" to withdraw
    And clicks the "Withdraw" button on the transaction page
    Then the message "Transaction successful" is displayed
    And the account balance is updated correctly after withdrawal

  @Negative
  Scenario: Customer attempts to withdraw more than the balance
    Given the customer has a balance
    When the user navigates to the "Withdrawl" tab
    And enters an amount "999999" greater than the balance to withdraw
    And clicks the "Withdraw" button on the transaction page
    Then the error message "Transaction Failed" is displayed