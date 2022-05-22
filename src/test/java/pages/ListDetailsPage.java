package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Log;

public class ListDetailsPage extends BasePage{

    private final Log log;
    public ListDetailsPage(WebDriver driver,Log log){
        super(driver);
        this.log = log;
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

    //Method to fill the list information and crate it
    public void createNewList(String name, String description){
        listName.sendKeys(name);
        log.logInfo("List name entered in the text box");
        listDescription.sendKeys(description);
        log.logInfo("List description entered in the text box");
        nextButton.click();
        log.logInfo("Click on Next button performed");
        explicitWait(searchMovieBar);
    }

    //Method to search a movie and select it
    public void addMovieList(String nameMovie) {
        searchMovieBar.sendKeys(nameMovie);
        log.logInfo("Movie name entered in the text box");
        explicitWait(movieOptionsList);
        WebElement selectedMovie = movieOptionsList.findElement(By.cssSelector("[alt=\""+ nameMovie+"\"]"));
        selectedMovie.click();
        log.logInfo("Click on the selected movie performed");
    }

    //Method to delete a movie
    public void deleteMovieList(String idMovie){
        implicitWait(10);
        String deleteMovieSelector = "li[data-media-id=\"" + idMovie + "\"] span[class=\"glyphicons_v2 circle-remove\"]";
        WebElement deleteMovieButton = driver.findElement(By.cssSelector(deleteMovieSelector));
        implicitWait(10);
        deleteMovieButton.click();
        log.logInfo("Click on the Delete button performed");
    }

    //Method to get the list name from the header of the webpage
    public String getListName(){
        String headerName;
        headerName = listNameHeader.getText();
        log.logInfo("Getting List name from the header");
        return headerName;
    }

    //Method to get the name of the added movie
    public String getMovieName(){
        String headerMovieName;
        implicitWait(10);
        String movieName = "li[data-media-type=\"movie\"] h4";
        WebElement movieNameHeader = driver.findElement(By.cssSelector(movieName));
        log.logInfo("Getting Movie name text");
        headerMovieName = movieNameHeader.getText();
        return headerMovieName;
    }

    //Method to get the text of 'empty list'
    public String getResultsContent() {
        String listContent;
        try{
            implicitWait(1);
            listContent = emptyListResults.getText();
        }catch (Exception e){
            driver.navigate().refresh();
            listContent = emptyListResults.getText();
        }
        log.logInfo("Getting the empty list text");
        return listContent;
    }

}
