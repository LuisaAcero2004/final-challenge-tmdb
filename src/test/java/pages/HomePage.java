package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Log;

public class HomePage extends BasePage{

    private final Log log;
    public HomePage(WebDriver driver,Log log){
        super(driver);
        this.log = log;
    }

    @FindBy(css = "a[href=\"/login\"]")
    private WebElement LoginButton;


    //Method to launch the login page
    public void goLogin(){
        LoginButton.click();
        log.logInfo("Click on Login Button performed");
    }


}
