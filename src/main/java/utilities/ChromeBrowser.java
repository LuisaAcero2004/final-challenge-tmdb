package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

public class ChromeBrowser implements Browser {

    public WebDriver createDriver() {
        String path = Paths.get(System.getProperty("user.dir"), "src/main/resources/drivers/chromedriver.exe").toString();
        System.setProperty("webdriver.chrome.driver", path);

        return new ChromeDriver();
    }


}

