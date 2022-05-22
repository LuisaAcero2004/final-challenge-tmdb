package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Screenshot{

    public String takeScreenshot(WebDriver driver) throws IOException {
        //Take screenshot and save as a file
        TakesScreenshot scrShot = ((TakesScreenshot)driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Destination directory
        File DestFile = new File( Paths.get(System.getProperty("user.dir"), "tests-screenshots\\").toString());
        //Copy screenshot to selected directory
        FileUtils.copyFileToDirectory(SrcFile,DestFile);
        return SrcFile.getName();
    }
}
