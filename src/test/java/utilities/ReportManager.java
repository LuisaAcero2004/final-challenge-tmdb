package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private ExtentReports extent;
    private ExtentSparkReporter spark;
    private ExtentTest test;

    //Method to create report
    public void setUpReport(String reportName){
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(reportName);
        extent.attachReporter(spark);

    }

    //Method to create test in report
    public ExtentTest createTest(String testName){
        this.test = extent.createTest(testName);
        return test;
    }

    //Methods to log to report
    public void logPassR(String logMessage){
        test.pass(logMessage);
    }

    public void logFailR(String logMessage, String path){
        test.fail(logMessage, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    public void logSkipR(String logMessage){
        test.skip(logMessage);
    }

    public void logInfoR(String logMessage){
        test.info(logMessage);
    }

    //Finish report
    public void finishReport(){
        extent.flush();
    }

    public void setTest(ExtentTest test) {
        this.test = test;
    }
}
