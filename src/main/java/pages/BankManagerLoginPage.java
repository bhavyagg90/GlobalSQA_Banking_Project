package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankManagerLoginPage {
 WebDriver driver;

 @FindBy(xpath = "//button[contains(text(),'Add Customer')]")
 private WebElement addCustomerTab;

 @FindBy(xpath = "//button[contains(text(),'Open Account')]")
 private WebElement openAccountTab;

 public BankManagerLoginPage(WebDriver driver) {
     this.driver = driver;
     PageFactory.initElements(driver, this);
 }

 public void navigateToAddCustomer() {
     addCustomerTab.click();
 }

 public void navigateToOpenAccount() {
     openAccountTab.click();
 }
}