package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerAccountPage {
    WebDriver driver;

    @FindBy(xpath = "//button[contains(text(),'Deposit')]")
    private WebElement depositTab;

    @FindBy(xpath = "//button[contains(text(),'Withdrawl')]")
    private WebElement withdrawlTab;

    @FindBy(xpath = "//input[@placeholder='amount']")
    private WebElement amountInput;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement transactionButton;

    @FindBy(xpath = "//span[@ng-show='message']")
    private WebElement messageSpan;

    @FindBy(xpath = "//strong[contains(text(),'Welcome')]")
    private WebElement welcomeMessage;
    
    // The second 'strong' element holds the balance
    @FindBy(xpath = "//div[contains(@class,'center')]/strong[2]")
    private WebElement balanceAmount;

    public CustomerAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }
    
    public WebElement getMessageSpanElement() {
        return messageSpan;
    }

    public void navigateToDeposit() {
        depositTab.click();
    }
    
    public void navigateToWithdrawl() {
        withdrawlTab.click();
    }
    
    public void enterAmount(String amount) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(amountInput));
    	amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void clickTransactionButton() {
        transactionButton.click();
    }
    
    public String getTransactionStatusMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(messageSpan));
        return messageSpan.getText();
    }
    
    public int getBalance() {
        return Integer.parseInt(balanceAmount.getText());
    }
}