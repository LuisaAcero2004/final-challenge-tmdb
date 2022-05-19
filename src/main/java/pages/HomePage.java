package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;


public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "a[href=\"/login\"]")
    private WebElement LoginButton;

    public void goLogin(){

        LoginButton.click();
    }







}
