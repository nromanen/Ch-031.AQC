package ordering;


import org.openqa.selenium.WebDriver;
import tools.Browser;
import tools.ColoredString;

public class AddProductPage {

    private WebDriver driver;
    private Browser browser;
    private static String productNameErrorIdLocator = "productName.errors";
    private static String productPriceErrorIdLocator = "productPrice.errors";
    private static String productDescriptionErrorIdLocator = "productDescription.errors";
    private static String okButtonCssLocator = "input[type=\"submit\"]";
    private static String cancelButtonCssLocator = "input[type=\"button\"]";
    private static String productNameValueIdLocator = "name";
    private static String productDescriptionValueIdLocator = "description";
    private static String productPriceValueIdLocator = "price";


    public AddProductPage(WebDriver driver) {

        this.driver = driver;
        browser = new Browser(driver);
    }

    public void clickOkButton(){
        browser.findElementByCssSelector(okButtonCssLocator).click();
    }

    public void clickCancelButton(){
        browser.findElementByCssSelector(cancelButtonCssLocator).click();
    }


    public void setProductNameValue(String value){
        browser.findElementById(productNameValueIdLocator).clear();
        browser.findElementById(productNameValueIdLocator).sendKeys(value);
    }

    public void setProductDescriptionValue (String value){
        browser.findElementById(productDescriptionValueIdLocator).clear();
        browser.findElementById(productDescriptionValueIdLocator).sendKeys(value);
    }

    public void setProductPriceValue (String value){
        browser.findElementById(productPriceValueIdLocator).clear();
        browser.findElementById(productPriceValueIdLocator).sendKeys(value);
    }

    public ColoredString getProductNameErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(productNameErrorIdLocator).getText());
        coloredString.setColorFromString(browser.findElementById(productNameErrorIdLocator).getCssValue("color"));
        return  coloredString;
    }

    public ColoredString getProductDescriptionErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(productDescriptionErrorIdLocator).getText());
        coloredString.setColorFromString(browser.findElementById(productDescriptionErrorIdLocator).getCssValue("color"));
        return coloredString;
    }

    public ColoredString getProductPriceErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(productPriceErrorIdLocator).getText());
        coloredString.setColorFromString(browser.findElementById(productPriceErrorIdLocator).getCssValue("color"));
        return coloredString;
    }
}
