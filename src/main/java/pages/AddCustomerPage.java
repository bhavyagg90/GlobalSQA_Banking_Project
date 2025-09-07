package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
 WebDriver driver;

 @FindBy(xpath = "//input[@placeholder='First Name']")
 private WebElement firstNameInput;

 @FindBy(xpath = "//input[@placeholder='Last Name']")
 private WebElement lastNameInput;

 @FindBy(xpath = "//input[@placeholder='Post Code']")
 private WebElement postCodeInput;

 @FindBy(xpath = "//button[@type='submit']")
 private WebElement addCustomerButton;

 public AddCustomerPage(WebDriver driver) {
     this.driver = driver;
     PageFactory.initElements(driver, this);
 }

 public void enterCustomerDetails(String firstName, String lastName, String postCode) {
     firstNameInput.sendKeys(firstName);
     lastNameInput.sendKeys(lastName);
     postCodeInput.sendKeys(postCode);
 }

 public void clickAddCustomer() {
     addCustomerButton.click();
 }
 
 public boolean isPageDisplayed() {
     return addCustomerButton.isDisplayed();
 }
}