package utilities.browserFactory;

public class BrowserFactory {
    String browserName;

    public BrowserFactory(String browserName){
        this.browserName = browserName;

    }

    //Method to create the selected browser
    public Browser createBrowser(){
        switch(this.browserName){
            case "Chrome":
                return new ChromeBrowser();
            case "Edge":
                return new EdgeBrowser();
            default:
                throw new IllegalStateException("The driver wasn't found");
        }
    }
}
