package tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Olya on 13.07.2015.
 */
public class WebDriverFactory {

    public static WebDriver getDriver(String webdriverName) {
        if ("firefox".equals(webdriverName))
            return new FirefoxDriver();
        else if ("chrome".equals(webdriverName))
            return getChromeDriver();
        else throw new IllegalArgumentException("Invalid driver name. Please use 'firefox' or 'chrome'!");
    }


    public static WebDriver getChromeDriver() {
        String operationSystem = System.getProperty("os.name").toLowerCase();
        if (operationSystem.indexOf("win") >= 0){
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");}
        else
        {System.setProperty("webdriver.chrome.driver", "chromedriver");}
        return new ChromeDriver();
    }
}
