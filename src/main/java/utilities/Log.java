package utilities;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Log {


    private static final Logger logger = LogManager.getLogger("Testing Logger");

    public void logInfo(String logMessage){
        logger.info(logMessage);
    }

    public void logStartCaseEx(String testName){
        logger.info("*** Starting Test case '{}' execution ***",testName);
    }

    public void logFinishCaseEx(String testName)
    {
        logger.info("*** Finishing Test case '{}' execution ***",testName);
    }


}
