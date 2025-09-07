package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage {
    WebDriver driver;

    @FindBy(id = "userSelect")
    private WebElement userDropdown;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectUser(String userName) {
        Select select = new Select(userDropdown);
        select.selectByVisibleText(userName);
    }

    public void clickLogin() {
        loginButton.click();
    }
}