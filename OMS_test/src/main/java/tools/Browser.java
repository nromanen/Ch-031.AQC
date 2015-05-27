package tools;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Browser {
    private WebDriver driver;

    private static final int TIME_OUT_SECONDS = 10;


    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl () {
    	return driver.getCurrentUrl();
    }

    public WebElement findElementByName(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
        return element;
    }

    public WebElement findElementById(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
        return element;
    }

    public WebElement findElementByLinkText(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
        return element;
    }

    public WebElement findElementByCssSelector(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        return element;
    }

    public WebElement findElementByXpath(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return element;
    }

    public WebElement findElementByClassName(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
        return element;
    }

    public WebElement findElementByPartialLinkText(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locator)));
        return element;
    }

    public WebElement findElementByTagName(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName(locator)));
        return element;
    }

    public List<WebElement> findElementsByTagName(String locator) {
        List<WebElement> element = (new WebDriverWait(driver, this.TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(locator)));
        return element;
    }

    public void alertAccept(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }



}
