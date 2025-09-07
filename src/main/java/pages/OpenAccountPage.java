package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage {
    WebDriver driver;

    @FindBy(id = "userSelect")
    private WebElement customerDropdown;

    @FindBy(id = "currency")
    private WebElement currencyDropdown;

    @FindBy(xpath = "//button[text()='Process']")
    private WebElement processButton;

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCustomer(String customerName) {
        Select select = new Select(customerDropdown);
        select.selectByVisibleText(customerName);
    }

    public void selectCurrency(String currency) {
        Select select = new Select(currencyDropdown);
        select.selectByVisibleText(currency);
    }

    public void clickProcess() {
        processButton.click();
    }

    public boolean isProcessButtonDisplayed() {
        try {
            return processButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}