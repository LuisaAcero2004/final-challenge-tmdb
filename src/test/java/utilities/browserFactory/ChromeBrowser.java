package utilities.browserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.nio.file.Paths;

public class ChromeBrowser implements Browser {

    //Chrome factory
    public WebDriver createDriver() {
        String path = Paths.get(System.getProperty("user.dir"), "src/test/resources/drivers/chromedriver.exe").toString();
        System.setProperty("webdriver.chrome.driver", path);

        return new ChromeDriver();
    }


}

