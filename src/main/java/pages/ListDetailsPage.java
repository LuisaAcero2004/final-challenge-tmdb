package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ListDetailsPage extends BasePage{

    public ListDetailsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="name")
    WebElement listName;

    @FindBy(id="description")
    WebElement listDescription;

    @FindBy(id="step_1_submit")
    WebElement nextButton;

    @FindBy(id="list_item_search")
    WebElement searchMovieBar;

    @FindBy(id="list_item_search_listbox")
    WebElement movieOptionsList;

    public void createNewList(String name, String description){
        listName.sendKeys(name);
        listDescription.sendKeys(description);
        nextButton.click();
    }

    public void addMovieList(String nameMovie) throws InterruptedException {
        explicitWait(searchMovieBar);
        searchMovieBar.sendKeys(nameMovie);
        explicitWait(movieOptionsList);
        movieOptionsList.findElement(By.cssSelector("[alt=\""+ nameMovie+"\"]")).click();
    }

    public void deleteMovieList(String idMovie){
        implicitWait(10);
        String deleteMovieSelector = "li[data-media-id=\"" + idMovie + "\"] span[class=\"glyphicons_v2 circle-remove\"]";
        WebElement deleteMovieButton = driver.findElement(By.cssSelector(deleteMovieSelector));
        deleteMovieButton.click();

    }




}
