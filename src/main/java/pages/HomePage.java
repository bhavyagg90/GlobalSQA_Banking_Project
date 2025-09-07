package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
 WebDriver driver;

 @FindBy(xpath = "//button[contains(text(),'Customer Login')]")
 private WebElement customerLoginButton;

 @FindBy(xpath = "//button[contains(text(),'Bank Manager Login')]")
 private WebElement bankManagerLoginButton;

 public HomePage(WebDriver driver) {
     this.driver = driver;
     PageFactory.initElements(driver, this);
 }

 public void clickCustomerLogin() {
     customerLoginButton.click();
 }

 public void clickBankManagerLogin() {
     bankManagerLoginButton.click();
 }
}