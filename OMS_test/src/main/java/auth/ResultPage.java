package auth;

import org.openqa.selenium.WebDriver;

public class ResultPage {
    private WebDriver driver;

    public ResultPage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }
}