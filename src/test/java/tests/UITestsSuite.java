package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utilities.ReportManager;
import utilities.browserFactory.Browser;
import utilities.browserFactory.BrowserFactory;
import utilities.Log;
import utilities.SetProperties;

public class UITestsSuite {

    public HomePage homePage;
    public LoginPage loginPage;
    public UserPage userPage;
    public ListsPage listsPage;
    public ListDetailsPage listDetailsPage;
    public WebDriver driver;
    public String browserExplorer;
    private Log log;
    private SetProperties setProperties;
    private ReportManager report;

    private String listName = "This is the list name";
    private String listDescription = "Description of the created list";
    private String movieName = "La lista de Schindler";
    private int idMovie = 424;

    @BeforeClass
    public void beforeClass(ITestContext context){
        //Create log and report objects
        setProperties = new SetProperties();
        log = new Log();
        report = new ReportManager();
        log.setReport(report);

        //Start report
        report.setUpReport("tests-reports/" + getClass().getName() + java.time.LocalDateTime.now().toString().replace(":",".") + ".html");
        context.setAttribute("report", report);
        context.setAttribute("log", log);

        //Selected browser "Edge" or "Chrome"
        browserExplorer = "Chrome";
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

    @AfterClass
    public void afterClass() {
        driver.manage().deleteAllCookies();
        driver.quit();
        log.logInfo("Closing " + browserExplorer + " browser");
        report.finishReport();
    }

    @Test(testName = "Create List")
    public void createListUI(){
        userPage.goLists();
        listsPage.newList();
        listDetailsPage.createNewList(listName,listDescription);
        //Assertion to compare the name of the created list and the variable listName
        Assert.assertEquals(listDetailsPage.getListName(),listName);
        log.logInfo("Assertion performed");
    }

    @Test(dependsOnMethods = {"createListUI"},testName = "Add a movie to a list")
    public void addMovieListUI(){
        listDetailsPage.addMovieList(movieName);
        //Assertion to compare the name of the added movie and the variable movieName
        Assert.assertTrue(listDetailsPage.getMovieName().contains(movieName));
        log.logInfo("Assertion performed");
    }

    @Test(dependsOnMethods = {"createListUI","addMovieListUI"},testName = "Delete an existing movie in the list")
    public void deleteMovieListUI(){
        listDetailsPage.deleteMovieList(Integer.toString(idMovie));
        //Assertion to compare the text after deleting the movie and the 'empty list' text
        Assert.assertEquals(listDetailsPage.getResultsContent(),"There are no items added to this list.");
        log.logInfo("Assertion performed");
    }



}
