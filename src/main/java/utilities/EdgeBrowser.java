package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.nio.file.Paths;

public class EdgeBrowser implements Browser  {

    public WebDriver createDriver() {
        String path = Paths.get(System.getProperty("user.dir"), "src/main/resources/drivers/msedgedriver.exe").toString();
        System.setProperty("webdriver.edge.driver", path);
        return new EdgeDriver();
    }


}
