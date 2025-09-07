
Feature: Bank Manager Functionalities

  Background:
    Given the user is on the bank's home page
    When the user clicks on the "Bank Manager Login" button

  @Positive
  Scenario: Add a new customer successfully
    Given the user navigates to the "Add Customer" page
    When the user enters First Name "John", Last Name "Doe", and Post Code "12345"
    And clicks the "Add Customer" button
    Then a success alert with the text "Customer added successfully" should be displayed
    
  @Negative
  Scenario: Attempt to add a customer with blank fields
  	Given the user navigates to the "Add Customer" page
  	When the user clicks the "Add Customer" button without filling the fields
  	Then no success alert should be displayed and the user should remain on the page

  @Positive
  Scenario: Open a new account for an existing customer
    Given the user navigates to the "Open Account" page
    And a customer with name "Hermoine Granger" exists
    When the user selects the customer "Hermoine Granger"
    And selects the currency "Dollar"
    And clicks the "Process" button on the Open Account page
    Then a success alert with the text "Account created successfully" should be displayed
    
  @Negative
  Scenario: Attempt to open an account without selecting a customer
    Given the user navigates to the "Open Account" page
    When clicks the "Process" button on the Open Account page
    Then no account creation success alert should be displayed 
