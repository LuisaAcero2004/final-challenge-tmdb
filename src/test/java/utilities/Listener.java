package utilities;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    ReportManager report;
    Log log;
    Screenshot screenshot = new Screenshot();

    @Override
    public void onStart(ITestContext context){
        //Start report
        report = (ReportManager) context.getAttribute("report");
        report.setUpReport("tests-reports");
    }

    @Override
    public void onFinish(ITestContext context) {
        //Finish report
        report = (ReportManager) context.getAttribute("report");
        report.finishReport();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        report = (ReportManager) context.getAttribute("report");
        log = (Log) context.getAttribute("log");
        //Create test in report
        report.setTest(report.createTest(iTestResult.getName()));
        log.logInfo("*** Starting Test case '" +  iTestResult.getName() + "' execution ***");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult){
        ITestContext context = iTestResult.getTestContext();
        log = (Log) context.getAttribute("log");
        log.logTestPass("Test '" +  iTestResult.getName() + "' execution finished successfully");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult iTestResult){
        String pictureName;
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        //Take screenshot
        pictureName = screenshot.takeScreenshot(driver);
        report = (ReportManager) context.getAttribute("report");
        log = (Log) context.getAttribute("log");
        //Log and send screenshot to report
        log.logTestFail("Test '" +  iTestResult.getName() + "' execution failed", "\\tests-screenshots\\"+pictureName);
        log.logInfo("Screenshot saved successfully as '" + pictureName + "'");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        ITestContext context = iTestResult.getTestContext();
        log = (Log) context.getAttribute("log");
        log.logTestSkip("The test execution was skipped");
    }


}
