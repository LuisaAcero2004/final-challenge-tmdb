import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;
import utilities.Browser;
import utilities.BrowserFactory;
import utilities.SetProperties;

public class UITestsSuite {

    private HomePage homePage;
    private LoginPage loginPage;
    private UserPage userPage;
    private ListsPage listsPage;
    private ListDetailsPage listDetailsPage;
    private WebDriver driver;
    private SetProperties setProperties;

    private String listName = "This is the list name";
    private String listDescription = "This is the list drtggggui";
    private String movieName = "La lista de Schindler";
    private String idMovie = "424";

    @BeforeSuite
    public void beforeSuite(){
        setProperties = new SetProperties();
        BrowserFactory factory = new BrowserFactory("Edge");
        Browser browser = factory.createBrowser();
        driver = browser.createDriver();
        driver.get(setProperties.getUrlUI());

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);
        listsPage = new ListsPage(driver);
        listDetailsPage = new ListDetailsPage(driver);

        homePage.goLogin();
        loginPage.login(setProperties.getUser(), setProperties.getPassword());

    }

    @AfterSuite
    public void afterSuite(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    public void createList(){
        userPage.goLists();
        listsPage.newList();
        listDetailsPage.createNewList(listName,listDescription);

        Assert.assertEquals(listDetailsPage.getListName(),listName);
    }

    @Test(dependsOnMethods = {"createList"})
    public void addMovieList() throws InterruptedException {
        listDetailsPage.addMovieList(movieName);

        Assert.assertTrue(listDetailsPage.getMovieName().contains(movieName));
    }

    @Test(dependsOnMethods = {"createList","addMovieList"})
    public void deleteMovieList() throws InterruptedException {
        listDetailsPage.deleteMovieList(idMovie);

        Assert.assertEquals(listDetailsPage.getResultsContent(),"There are no items added to this list.");
    }


}
