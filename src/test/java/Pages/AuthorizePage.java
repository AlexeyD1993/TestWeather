package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizePage {
    private WebDriver driver;

    @FindBy(id="sign_in")
    private WebElement signInPage;

    @FindBy(id="user_email")
    private WebElement userName;

    @FindBy(id="user_password")
    private WebElement userPassword;

    @FindBy(name="commit")
    private WebElement signInButton;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public AuthorizePage GotoAuthorize() {
        signInPage.click();
        return this;
    }

    public AuthorizePage TypeEmail(String email) {
        userName.sendKeys(email);
        return this;
    }

    public AuthorizePage TypePassword(String password) {
        userPassword.sendKeys(password);
        return this;
    }

    public AuthorizePage Submit() {
        signInButton.submit();
        return this;
    }

}
