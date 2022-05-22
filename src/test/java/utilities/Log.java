package utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Log {

    private static final Logger logger = LogManager.getLogger("Testing Logger");
    private ReportManager report;

    public void setReport(ReportManager report) {
        this.report = report;
    }

    //Methods to log information,errors,warnings and tests status to logger file and report
    public void logInfo(String logMessage){
        try{
            report.logInfoR(logMessage);
            logger.info(logMessage);
        }catch(Exception e){
            logger.info(logMessage);
        }
    }


    public void logTestPass(String logMessage){
        logger.info(logMessage);
        report.logPassR(logMessage);
    }
    public void logTestFail(String logMessage,String path){
        logger.error(logMessage);
        report.logFailR(logMessage,path);
    }

    public void logTestSkip(String logMessage){
        logger.warn(logMessage);
        report.logSkipR(logMessage);
    }




}
