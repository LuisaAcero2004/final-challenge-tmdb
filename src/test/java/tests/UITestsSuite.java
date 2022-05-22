package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;
import utilities.ReportManager;
import utilities.browserFactory.Browser;
import utilities.browserFactory.BrowserFactory;
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
    private String browserExplorer;

    //Data required to create the list and add the movie
    private String listName = "This is the name List";
    private String listDescription = "This is the list drtgg55555gui";
    private String movieName = "La lista de Schindler";
    private String idMovie = "424";

    @BeforeSuite
    public void beforeSuite(ITestContext context){
        //Selected browser
        browserExplorer = "Edge";
        //Create log and report objects
        setProperties = new SetProperties();
        log = new Log();
        ReportManager report = new ReportManager();
        log.setReport(report);
        context.setAttribute("report", report);
        context.setAttribute("log", log);
        //Create driver
        BrowserFactory factory = new BrowserFactory(browserExplorer);
        Browser browser = factory.createBrowser();
        driver = browser.createDriver();
        context.setAttribute("WebDriver", driver);
        //Create pages
        homePage = new HomePage(driver,log);
        loginPage = new LoginPage(driver,log);
        userPage = new UserPage(driver,log);
        listsPage = new ListsPage(driver,log);
        listDetailsPage = new ListDetailsPage(driver,log);
        //Start driver and launch TMBD webpage
        driver.get(setProperties.getUrlUI());
        log.logInfo("Opening \"" + setProperties.getUrlUI() + "\" in " + browserExplorer + " browser");
        //Start login
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

    @Test(testName = "Create List")
    public void createList(){
        userPage.goLists();
        listsPage.newList();
        listDetailsPage.createNewList(listName,listDescription);
        //Assertion to compare the name of the created list and the variable listName
        Assert.assertEquals(listDetailsPage.getListName(),listName);
        log.logInfo("Assertion performed");
    }

    @Test(dependsOnMethods = {"createList"},testName = "Add a movie to a list")
    public void addMovieList(){
        listDetailsPage.addMovieList(movieName);
        //Assertion to compare the name of the added movie and the variable movieName
        Assert.assertTrue(listDetailsPage.getMovieName().contains(movieName));
        log.logInfo("Assertion performed");
    }

    @Test(dependsOnMethods = {"createList","addMovieList"},testName = "Delete an existing movie in the list")
    public void deleteMovieList(){
        listDetailsPage.deleteMovieList(idMovie);
        //Assertion to compare the text after deleting the movie and the 'empty list' text
        Assert.assertEquals(listDetailsPage.getResultsContent(),"There are no items added to this list.");
        log.logInfo("Assertion performed");
    }


}
