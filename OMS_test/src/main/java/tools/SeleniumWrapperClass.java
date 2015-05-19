package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapperClass {
    private WebDriver driver;

    public SeleniumWrapperClass(WebDriver driver) {
        this.driver = driver;
    }

    public void goToUrl(String url) {
       driver.get(url);
    }

    public WebElement findElement (By locator){
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("Element: " + locator + ", is not available on page - "
                    + driver.getCurrentUrl());
            return null;
        }
    }

    public WebElement findElementByName(String elementLocator) {
        By locator = By.name(elementLocator);
        WebElement webElement = findElement(locator);
        return webElement;
    }

    public WebElement findElementById(String elementLocator) {
        By locator = By.id(elementLocator);
        return findElement(locator);
    }

    public WebElement findElementByLinkText(String elementLocator) {
        By locator = By.linkText(elementLocator);
        return findElement(locator);
    }

    public WebElement findElementByCssSelector(String elementLocator) {
        By locator = By.cssSelector(elementLocator);
        return findElement(locator);
    }

    public WebElement findElementByXpath(String elementLocator) {
        By locator = By.xpath(elementLocator);
        return findElement(locator);
    }

    public WebElement findElementByClassName(String elementLocator) {
        By locator = By.className(elementLocator);
        return findElement(locator);
    }

    public WebElement findPartialLinkText(String elementLocator) {
        By locator = By.partialLinkText(elementLocator);
        return findElement(locator);
    }

    public boolean isElementPresentByPartialLinkText(String elementLocator) {
        WebElement webElement = findPartialLinkText(elementLocator);
        return webElement != null;
        }

    public boolean isElementPresentByXpath(String elementLocator) {
        WebElement webElement = findElementByXpath(elementLocator);
        return webElement != null;
    }

    public boolean isElementPresentByClassName(String elementLocator) {
        WebElement webElement = findElementByClassName(elementLocator);
        return webElement != null;
    }

    public boolean isElementPresentByCssSelector(String elementLocator) {
        WebElement webElement = findElementByCssSelector(elementLocator);
        return webElement != null;
    }

    public boolean isElementPresentByName(String elementLocator) {
        WebElement webElement = findElementByName(elementLocator);
        return webElement != null;
    }

    public boolean isElementPresentByLinkText(String elementLocator) {
        WebElement webElement = findElementByLinkText(elementLocator);
        return webElement != null;
    }

    public boolean isElementPresentById(String elementLocator) {
        WebElement webElement = findElementById(elementLocator);
        return webElement != null;
    }

   }