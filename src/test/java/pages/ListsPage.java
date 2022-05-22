package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Log;

public class ListsPage extends BasePage{

    private final Log log;
    public ListsPage(WebDriver driver,Log log){
        super(driver);
        this.log = log;
    }

    @FindBy(css="a[href=\"/list/new\"]")
    WebElement createListButton;

    //Method to launch the 'New list' form
    public void newList(){
        createListButton.click();
        log.logInfo("Click on the Create button performed");
    }


}
