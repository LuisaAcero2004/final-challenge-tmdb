package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;
import utilities.Browser;
import utilities.BrowserFactory;
import utilities.Log;
import utilities.SetProperties;

public class UITestsSuite {

    private HomePage homePage;
    private LoginPage loginPage;
    private UserPage userPage;
    private ListsPage listsPage;
    private ListDetailsPage listDetailsPage;
    private WebDriver driver;
    private SetProperties setProperties;
    private Log log;
    private String testName;
    private String browserExplorer;

    private String listName = "This is the list name";
    private String listDescription = "This is the list drtgg55555gui";
    private String movieName = "La lista de Schindler";
    private String idMovie = "424";

    @BeforeSuite
    public void beforeSuite(){
        browserExplorer = "Edge";
        setProperties = new SetProperties();
        log = new Log();
        BrowserFactory factory = new BrowserFactory(browserExplorer);
        Browser browser = factory.createBrowser();
        driver = browser.createDriver();
        driver.get(setProperties.getUrlUI());
        log.logInfo("Opening \"" + setProperties.getUrlUI() + "\" in " + browserExplorer + " browser");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);
        listsPage = new ListsPage(driver);
        listDetailsPage = new ListDetailsPage(driver);

        log.logInfo("Starting Login");
        homePage.goLogin();
        loginPage.login(setProperties.getUser(), setProperties.getPassword());
        log.logInfo("Finishing Login");
        log.logInfo("*** STARTING TEST CASES SUITE EXECUTION ***");

    }

    @AfterSuite
    public void afterSuite(){
        driver.manage().deleteAllCookies();
        driver.quit();
        log.logInfo("Closing " + browserExplorer + " browser");
        log.logInfo("*** FINISHING TEST CASES SUITE EXECUTION ***");
    }

    @Test
    public void createList(){
        testName = "Create List";
        log.logStartCaseEx(testName);
        userPage.goLists();
        listsPage.newList();
        listDetailsPage.createNewList(listName,listDescription);
        Assert.assertEquals(listDetailsPage.getListName(),listName);
        log.logInfo("Assertion performed");
        log.logFinishCaseEx(testName);
    }

    @Test(dependsOnMethods = {"createList"})
    public void addMovieList(){
        testName = "Add a movie to a list";
        log.logStartCaseEx(testName);
        listDetailsPage.addMovieList(movieName);

        Assert.assertTrue(listDetailsPage.getMovieName().contains(movieName));
        log.logInfo("Assertion performed");
        log.logFinishCaseEx(testName);
    }

    @Test(dependsOnMethods = {"createList","addMovieList"})
    public void deleteMovieList(){
        testName = "Delete an existing movie in the list";
        log.logStartCaseEx(testName);
        listDetailsPage.deleteMovieList(idMovie);
        Assert.assertEquals(listDetailsPage.getResultsContent(),"There are no items added to this list.");
        log.logInfo("Assertion performed");
        log.logFinishCaseEx(testName);
    }


}
