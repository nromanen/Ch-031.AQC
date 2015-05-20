package tools;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {
    private WebDriver driver;
    private int time = 10;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public WebElement findElementByName(String locator) {
        WebElement element = (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
        return element;
    }

    public WebElement findElementById(String locator) {
        WebElement element = (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
        return element;
    }

    public WebElement findElementByLinkText(String locator) {
        WebElement element = (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
        return element;
    }

    public WebElement findElementByCssSelector(String locator) {
        WebElement element = (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        return element;
    }

    public WebElement findElementByXpath(String locator) {
        WebElement element = (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return element;
    }


    public WebElement findElementByClassName(String locator) {
        WebElement element = (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
        return element;
    }

    public WebElement findElementByPartialLinkText(String locator) {
        WebElement element = (new WebDriverWait(driver, time))
                .until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locator)));
        return element;
    }



}
