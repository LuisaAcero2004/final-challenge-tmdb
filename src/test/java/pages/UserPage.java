package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Log;

public class UserPage extends BasePage{

    private final Log log;
    public UserPage(WebDriver driver,Log log){
        super(driver);
        this.log = log;
    }

    @FindBy(css= "li a[href$=lists]")
    WebElement listsButton;

    //Method to launch the lists
    public void goLists(){
        listsButton.click();
        log.logInfo("Click on the Lists button performed");
    }

}
