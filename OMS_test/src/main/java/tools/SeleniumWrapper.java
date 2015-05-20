
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

/**
 * Created by diagon on 14.05.15.
 */
public class SeleniumWrapper {

    private WebDriver driver;

    public SeleniumWrapper(WebDriver d) {
        driver = d;
    }

    ///////////////////////////////////////////////////////////////////////////
    // BY ID
    ///////////////////////////////////////////////////////////////////////////

    public WebElement findById(String input_id) {
        return driver.findElement(By.id(input_id));
    }

    public void findByIdAndClick(String input_id) {
        driver.findElement(By.id(input_id)).click();
    }

    public void findByIdAndSendKeys(String input_id, String send_keys) {
        driver.findElement(By.id(input_id)).clear();
        driver.findElement(By.id(input_id)).sendKeys(send_keys);
    }


    ///////////////////////////////////////////////////////////////////////////
    // BY NAME
    ///////////////////////////////////////////////////////////////////////////

    public WebElement findByName(String input_id) {
        return driver.findElement(By.name(input_id));
    }

    public void findByNameAndClick(String input_id) {
        driver.findElement(By.name(input_id)).click();
    }

    public void findByNameAndSendKeys(String input_id, String send_keys) {
        driver.findElement(By.name(input_id)).clear();
        driver.findElement(By.name(input_id)).sendKeys(send_keys);
    }


    ///////////////////////////////////////////////////////////////////////////
    // BY LINKTEXT
    ///////////////////////////////////////////////////////////////////////////

    public WebElement findByLinkText(String input_id) {
        return driver.findElement(By.linkText(input_id));
    }

    public void findByLinkTextAndClick(String input_id) {
        driver.findElement(By.linkText(input_id)).click();
    }


    ///////////////////////////////////////////////////////////////////////////
    // BY CSS
    ///////////////////////////////////////////////////////////////////////////

    public WebElement findByCSS(String input_id) {
        return driver.findElement(By.cssSelector(input_id));
    }

    public void findByCSSAndClick(String input_id) {
        driver.findElement(By.cssSelector(input_id)).click();
    }


    ///////////////////////////////////////////////////////////////////////////
    // ETC
    ///////////////////////////////////////////////////////////////////////////

    public void closeAlert(String msg) {
        assertTrue(closeAlertAndGetItsText().matches(msg));
    }

    private String closeAlertAndGetItsText() {
        boolean acceptNextAlert = true;
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}