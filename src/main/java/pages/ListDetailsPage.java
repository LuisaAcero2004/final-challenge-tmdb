package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(className = "header")
    WebElement listNameHeader;

    @FindBy(className = "empty_list")
    WebElement emptyListResults;



    public void createNewList(String name, String description){
        listName.sendKeys(name);
        listDescription.sendKeys(description);
        nextButton.click();
        explicitWait(searchMovieBar);
    }

    public void addMovieList(String nameMovie) {
        searchMovieBar.sendKeys(nameMovie);
        explicitWait(movieOptionsList);
        WebElement selectedMovie = movieOptionsList.findElement(By.cssSelector("[alt=\""+ nameMovie+"\"]"));
        selectedMovie.click();
    }

    public void deleteMovieList(String idMovie){
        implicitWait(10);
        String deleteMovieSelector = "li[data-media-id=\"" + idMovie + "\"] span[class=\"glyphicons_v2 circle-remove\"]";
        WebElement deleteMovieButton = driver.findElement(By.cssSelector(deleteMovieSelector));
        implicitWait(10);
        deleteMovieButton.click();

    }

    public String getListName(){
        String headerName;
        headerName = listNameHeader.getText();

        return headerName;
    }

    public String getMovieName(){
        String headerMovieName;
        implicitWait(10);
        String movieName = "li[data-media-type=\"movie\"] h4";
        WebElement movieNameHeader = driver.findElement(By.cssSelector(movieName));

        headerMovieName = movieNameHeader.getText();
        return headerMovieName;
    }

    public String getResultsContent() {
        String listContent;
        try{
            implicitWait(1);
            listContent = emptyListResults.getText();
        }catch (Exception e){
            driver.navigate().refresh();
            listContent = emptyListResults.getText();
        }

        return listContent;
    }




}
