package tools;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.io.IOException;

/**
 * @author Olya
 *
 */
public class ScreenShotUtils extends TestWatcher {
    private WebDriver driver;
    private static final String SCREEN_SHOT_CATALOG = "logs/";

    public ScreenShotUtils() {
    }

    @Override
    protected void failed(Throwable e, Description description) {
        Browser browser = new Browser(driver);
        StringBuilder fileNameBuilder = new StringBuilder(SCREEN_SHOT_CATALOG);
        fileNameBuilder.append(description.getClassName()).append("_").append(description.getMethodName()).append(".png");
        try {
            browser.screenShot(fileNameBuilder.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}

