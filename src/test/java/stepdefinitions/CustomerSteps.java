package stepdefinitions;

import pages.CustomerAccountPage;
import pages.CustomerLoginPage;
import  utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CustomerSteps {
    private WebDriver driver = DriverManager.getDriver();
    private CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
    private CustomerAccountPage customerAccountPage = new CustomerAccountPage(driver);
    private int initialBalance;

    @Given("a customer {string} with an account exists")
    public void a_customer_with_an_account_exists(String customerName) {
        // Precondition: We assume the bank manager has already created an account for this user.
        System.out.println("Precondition: Account for '" + customerName + "' is assumed to exist.");
    }

    @When("the user selects {string} from the dropdown and clicks Login")
    public void the_user_selects_from_the_dropdown_and_clicks_login(String customerName) {
        customerLoginPage.selectUser(customerName);
        customerLoginPage.clickLogin();
    }

    @Given("the customer's initial balance is displayed")
    public void the_customer_s_initial_balance_is_displayed() {
        initialBalance = customerAccountPage.getBalance();
        System.out.println("Initial balance is: " + initialBalance);
    }

    @When("the user navigates to the {string} tab")
    public void the_user_navigates_to_the_tab(String tabName) {
        if (tabName.equalsIgnoreCase("Deposit")) {
            customerAccountPage.navigateToDeposit();
        } else if (tabName.equalsIgnoreCase("Withdrawl")) {
            customerAccountPage.navigateToWithdrawl();
        }
    }

    @When("enters the amount {string} to deposit")
    public void enters_the_amount_to_deposit(String amount) {
        customerAccountPage.enterAmount(amount);
    }
    
    @Then("the account balance should remain unchanged")
    public void the_account_balance_should_remain_unchanged() {
        
        int newBalance = customerAccountPage.getBalance();
        
        Assert.assertEquals(newBalance, initialBalance, "Balance should not have changed for an invalid deposit.");
        
        System.out.println("As expected, the balance remained at: " + newBalance);
    }

    @When("clicks the {string} button on the transaction page")
    public void clicks_the_deposit_button_on_the_transaction_page(String buttonName) {
        customerAccountPage.clickTransactionButton();
    }

    @Then("the message {string} is displayed")
    public void the_message_is_displayed(String expectedMessage) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 wait.until(ExpectedConditions.textToBePresentInElement(customerAccountPage.getMessageSpanElement(), expectedMessage));
    	String actualMessage = customerAccountPage.getTransactionStatusMessage();
    	Assert.assertEquals(actualMessage, expectedMessage);
        
    }

    @Then("the account balance is updated correctly after deposit")
    public void the_account_balance_is_updated_correctly_after_deposit() {
        int newBalance = customerAccountPage.getBalance();
        // Assuming the deposit was 500 as per the feature file
        Assert.assertEquals(newBalance, initialBalance + 1000, "Balance was not updated correctly after deposit.");
    }

    @When("enters the amount {string} to withdraw")
    public void enters_the_amount_to_withdraw(String amount) {
        customerAccountPage.enterAmount(amount);
    }

    @Then("the account balance is updated correctly after withdrawal")
    public void the_account_balance_is_updated_correctly_after_withdrawal() {
        int newBalance = customerAccountPage.getBalance();
        // Assuming the withdrawal was 300 as per the feature file
        Assert.assertEquals(newBalance, initialBalance - 300, "Balance was not updated correctly after withdrawal.");
    }

    @Given("the customer has a balance")
    public void the_customer_has_a_balance() {
        initialBalance = customerAccountPage.getBalance();
        // Ensure there is some balance to test against
        if (initialBalance <= 0) {
            customerAccountPage.navigateToDeposit();
            customerAccountPage.enterAmount("1000");
            customerAccountPage.clickTransactionButton();
            initialBalance = customerAccountPage.getBalance();
        }
    }

    @When("enters an amount {string} greater than the balance to withdraw")
    public void enters_an_amount_greater_than_the_balance_to_withdraw(String amount) {
        // The amount in the feature file is a placeholder; a more robust test
        // would calculate an amount greater than the actual balance.
        String amountToWithdraw = String.valueOf(initialBalance + 500);
        customerAccountPage.enterAmount(amountToWithdraw);
    }

    @Then("the error message {string} is displayed")
    public void the_error_message_is_displayed(String expectedMessage) {
        Assert.assertTrue(customerAccountPage.getTransactionStatusMessage().contains(expectedMessage), "Error message was not as expected.");
    }
}