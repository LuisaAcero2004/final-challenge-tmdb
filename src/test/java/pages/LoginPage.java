package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Log;

public class LoginPage extends BasePage{
    private final Log log;
    public LoginPage(WebDriver driver,Log log){
        super(driver);
        this.log = log;
    }

    @FindBy(id="username")
    WebElement usernameText;

    @FindBy(id="password")
    WebElement passwordText;

    @FindBy(id="login_button")
    WebElement loginButton;

    //Method to login
    public void login(String username,String password){
        usernameText.sendKeys(username);
        log.logInfo("Username entered in the text box");
        passwordText.sendKeys(password);
        log.logInfo("Password entered in the text box");
        loginButton.click();
        log.logInfo("Click on the Login button performed");
    }
}
