package utilities.browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.nio.file.Paths;

public class EdgeBrowser implements Browser  {

    //Edge factory
    public WebDriver createDriver() {
        String path = Paths.get(System.getProperty("user.dir"), "src/test/resources/drivers/msedgedriver.exe").toString();
        System.setProperty("webdriver.edge.driver", path);
        return new EdgeDriver();
    }


}
