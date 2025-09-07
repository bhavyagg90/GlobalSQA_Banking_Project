package stepdefinitions;

import pages.*;
import utils.ConfigReader;
import utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BankManagerSteps {
    private WebDriver driver = DriverManager.getDriver();
    private ConfigReader configReader = new ConfigReader();
    private HomePage homePage = new HomePage(driver);
    private BankManagerLoginPage managerLoginPage = new BankManagerLoginPage(driver);
    private AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
    private OpenAccountPage openAccountPage = new OpenAccountPage(driver);

    @Given("the user is on the bank's home page")
    public void the_user_is_on_the_bank_s_home_page() {
        driver.get(configReader.getUrl());
    }
    
    @When("the user clicks the {string} button without filling the fields")
    public void the_user_clicks_the_button_without_filling_the_fields(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add Customer")) {
            addCustomerPage.clickAddCustomer();
        }else if(buttonName.equalsIgnoreCase("Process")) {
        	openAccountPage.clickProcess();
        }
    }

    @Then("no account creation success alert should be displayed")
    public void no_account_creation_success_alert_should_be_displayed() {
        try {
            // Wait for a very short time (2 seconds) to see if an alert appears.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());

            // If an alert DOES appear, it's a failure for this negative test.
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            Assert.fail("An unexpected alert appeared with text: " + alertText);

        } catch (TimeoutException e) {
            // This is the EXPECTED outcome: no alert appeared. The test passes this part.
            System.out.println("As expected, no alert was displayed.");
            
            // As a final check, confirm we are still on the "Open Account" page.
            Assert.assertTrue(openAccountPage.isProcessButtonDisplayed(), "User was navigated away from the Open Account page.");
        }
    }

    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Bank Manager Login")) {
            homePage.clickBankManagerLogin();
        } else if (buttonName.equalsIgnoreCase("Customer Login")) {
            homePage.clickCustomerLogin();
        }
    }

    @Given("the user navigates to the {string} page")
    public void the_user_navigates_to_the_page(String tabName) {
        if (tabName.equalsIgnoreCase("Add Customer")) {
            managerLoginPage.navigateToAddCustomer();
        } else if (tabName.equalsIgnoreCase("Open Account")) {
            managerLoginPage.navigateToOpenAccount();
        }
    }

    @When("the user enters First Name {string}, Last Name {string}, and Post Code {string}")
    public void the_user_enters_first_name_last_name_and_post_code(String firstName, String lastName, String postCode) {
        addCustomerPage.enterCustomerDetails(firstName, lastName, postCode);
    }

    @When("clicks the {string} button")
    public void clicks_the_add_customer_button(String buttonName) {
        if(buttonName.equalsIgnoreCase("Add Customer")) {
            addCustomerPage.clickAddCustomer();
        }
    }
    
    @Then("no success alert should be displayed and the user should remain on the page")
    public void no_success_alert_should_be_displayed_and_the_user_should_remain_on_the_page() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            Assert.fail("An unexpected alert appeared with text: " + alertText);

        } catch (TimeoutException e) {
            System.out.println("As expected, no alert was displayed.");
            
            Assert.assertTrue(addCustomerPage.isPageDisplayed(), "User was navigated away from the Add Customer page.");
        }
    }

    @Then("a success alert with the text {string} should be displayed")
    public void a_success_alert_with_the_text_should_be_displayed(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertTrue(alertText.contains(expectedText), "Alert text is not as expected.");
        alert.accept();
    }

    @Given("a customer with name {string} exists")
    public void a_customer_with_name_exists(String customerName) {
        // For this test, we assume the customer was added in a previous scenario.
        // In a real-world scenario, you might use an API to create this customer
        // to ensure the test is self-contained.
        System.out.println("Precondition: Customer '" + customerName + "' is assumed to exist.");
    }

    @When("the user selects the customer {string}")
    public void the_user_selects_the_customer(String customerName) {
        openAccountPage.selectCustomer(customerName);
    }

    @When("selects the currency {string}")
    public void selects_the_currency(String currency) {
        openAccountPage.selectCurrency(currency);
    }



    @When("clicks the {string} button on the Open Account page")
    public void clicks_the_process_button_on_the_open_account_page(String buttonName) {
        if (buttonName.equalsIgnoreCase("Process")) {
            openAccountPage.clickProcess();
        }
    }
}