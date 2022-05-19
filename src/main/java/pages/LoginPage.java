package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.SetProperties;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="username")
    WebElement usernameText;

    @FindBy(id="password")
    WebElement passwordText;

    @FindBy(id="login_button")
    WebElement loginButton;

    public void login(String username,String password){
        usernameText.sendKeys(username);
        passwordText.sendKeys(password);
        loginButton.click();
    }
}
