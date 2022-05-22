package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import requests.GetRequest;
import requests.PostRequest;
import utilities.Log;
import utilities.ReportManager;
import utilities.SetProperties;

public class APITestsSuite {

    private GetRequest get;
    private PostRequest post;
    private Log log;
    private SetProperties setProperties;
    private ReportManager report;
    private Response response;

    private String listName = "This is the list name";
    private String listDescription = "This is the desc";
    private int idMovie = 424;

    @BeforeClass
    public void beforeClass(ITestContext context){
        //Create log and report objects
        setProperties = new SetProperties();
        log = new Log();

        report = new ReportManager();

        //Start report
        report.setUpReport("tests-reports/"+getClass().getName() +java.time.LocalDateTime.now().toString().replace(":",".")+ ".html");
        log.setReport(report);
        context.setAttribute("report", report);
        context.setAttribute("log", log);

        //Create pages
        get = new GetRequest();
        post = new PostRequest();

        //Create new session
        log.logInfo("Starting Session creation");
        get.generateToken();
        log.logInfo("Token generated");
        post.validateToken(get.getRequest_token());
        log.logInfo("Token validated");
        post.createSession(post.getRequest_token());
        log.logInfo("Session created");
        log.logInfo("Finishing Session creation");
        log.logInfo("*** STARTING TEST CASES SUITE EXECUTION ***");
    }

    @AfterClass
    public void afterClass() {
        report.finishReport();
    }


    @Test(testName = "Create List using API")
    public void createListAPI(){
        response = post.createList(post.getSession_id(),listName,listDescription,"es");
        log.logInfo("list created");
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.", response.jsonPath().getString("status_message"));
        log.logInfo("Assertion performed");
    }

    @Test(dependsOnMethods = {"createListAPI"},testName = "Add a movie to a list using API")
    public void addMovieListAPI(){
        response =  post.addMovie(post.getSession_id(),idMovie,post.getList_id());
        log.logInfo("Movie with id "+idMovie+" added");
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        log.logInfo("Assertion performed");

    }

    @Test(dependsOnMethods = {"createListAPI","addMovieListAPI"},testName = "Delete an existing movie in the list using API")
    public void deleteMovieListAPI(){
        response =  post.deleteMovie(post.getSession_id(), idMovie,post.getList_id());
        log.logInfo("Movie with id "+idMovie+" deleted");
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        log.logInfo("Assertion performed");

    }

}
