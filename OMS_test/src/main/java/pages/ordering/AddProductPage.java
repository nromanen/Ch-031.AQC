package pages.ordering;


import org.openqa.selenium.WebDriver;
import tools.Browser;
import tools.ColoredString;
/**
 * This class describe Add Product Page functionality and provides a way to use it.
 * @author Olya.
 */
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

    /**
     * This method clicks on the "Ok" button.
     */
    public void clickOkButton(){
        browser.findElementByCssSelector(okButtonCssLocator).click();
    }

    /**
     * This method clicks on the "Cancel" button.
     */
    public void clickCancelButton(){
        browser.findElementByCssSelector(cancelButtonCssLocator).click();
    }

    /**
     * Sets the value in product name field.
     * @param value the product name value.
     */
    public void setProductNameValue(String value){
        browser.findElementById(productNameValueIdLocator).clear();
        browser.findElementById(productNameValueIdLocator).sendKeys(value);
    }

    /**
     * Sets the value in product description field.
     * @param value the description value.
     */
    public void setProductDescriptionValue (String value){
        browser.findElementById(productDescriptionValueIdLocator).clear();
        browser.findElementById(productDescriptionValueIdLocator).sendKeys(value);
    }

    /**
     * Sets the value in product price field.
     * @param value the price value.
     */
    public void setProductPriceValue (String value){
        browser.findElementById(productPriceValueIdLocator).clear();
        browser.findElementById(productPriceValueIdLocator).sendKeys(value);
    }

    /**
     * Gets the value in product price field.
     * @return product price value.
     */
    public String getProductPriceValue () {
        String elementvalue = browser.findElementById(productPriceValueIdLocator).getAttribute("value");
    return elementvalue;
    }

    /**
     * Gets the value in product description field.
     * @return product description value.
     */
    public String getProductDescriptionValue () {
        String elementvalue = browser.findElementById(productDescriptionValueIdLocator).getAttribute("value");
        return elementvalue;
    }

    /**
     * Gets the value in product name field.
     * @return product name value.
     */
    public String getProductNameValue () {
        String elementvalue = browser.findElementById(productNameValueIdLocator).getAttribute("value");
        return elementvalue;
    }

    /**
     * Returns color and text value of the product name error message.
     * @return {@link tools.ColoredString}
     */
    public ColoredString getProductNameErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(productNameErrorIdLocator).getText());
        coloredString.setColorFromString(browser.findElementById(productNameErrorIdLocator).getCssValue("color"));
        return  coloredString;
    }

    /**
     * Returns color and text value of the product description error message.
     * @return {@link tools.ColoredString}
     */
    public ColoredString getProductDescriptionErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(productDescriptionErrorIdLocator).getText());
        coloredString.setColorFromString(browser.findElementById(productDescriptionErrorIdLocator).getCssValue("color"));
        return coloredString;
    }

    /**
     * Returns color and text value of the product price error message.
     * @return {@link tools.ColoredString}
     */
    public ColoredString getProductPriceErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(productPriceErrorIdLocator).getText());
        coloredString.setColorFromString(browser.findElementById(productPriceErrorIdLocator).getCssValue("color"));
        return coloredString;
    }
}
