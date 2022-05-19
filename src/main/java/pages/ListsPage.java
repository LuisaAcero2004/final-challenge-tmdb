package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListsPage extends BasePage{

    public ListsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css="a[href=\"/list/new\"]")
    WebElement createListButton;

    public void newList(){
        createListButton.click();
    }


}
