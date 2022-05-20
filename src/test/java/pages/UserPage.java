package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class UserPage extends BasePage{

    public UserPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css= "li a[href$=lists]")
    WebElement listsButton;

    public void goLists(){
        listsButton.click();
        log.logInfo("Click on the Lists button performed");
    }

}
