package TestSupervisor;

import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.ColoredString;
import tools.DBUnitConfig;
import tools.Navigation;

import java.awt.*;

/**
 * This test case is designed for validation of error messages on Add Product page.
 * @author Olya.
 */
public class ProductValidationErrorMessagePageTest extends DBUnitConfig {

    private static WebDriver driver = new FirefoxDriver();
    private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";
    private static final String PRODUCT_PRICE_RANGE_VALUE = "1765";
    private static final String PRODUCT_PRICE_CHARACTERS_VALUE ="#4rr";

    public ProductValidationErrorMessagePageTest(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        beforeData = new IDataSet[] {getDataFromFile("data/productData.xml")};
        super.setUp();
        navigation = new Navigation(driver);
        navigation.goToUrl(HOME_PAGE);
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = navigation.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    /**
     * This test verify that when product name field is empty, then after clicking on OK button the following
     * message should appear in red color: "Please enter product name!"
     */
    public void testProductNameErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductNameErrorMessage();
        String expectedMessage = "Please enter product name!";
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test
    /**
     * When product price field is empty, then after clicking on OK button the following
     * message should appear in red color: "Please enter product price!"
     */
    public void testProductPriceEmptyErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter product price!";
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test // A test fails!
    /**
     * When entered invalid characters (symbols) on product price field, , then after clicking on OK button
     * the following error message will appear in red color "Please enter only numbers".
     */
    public void testProductPriceCharactersErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.setProductPriceValue(PRODUCT_PRICE_CHARACTERS_VALUE);
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter only numbers!"; //the following error message is appear: "Please enter double value!"
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test // A test fails!
    /**
     * This test verify that when entered text is >999 or <1 on product price field, then after clicking on OK button
     * the following error message will appear in red color "Please enter price in range of 1-999".
     */
    public void testProductPriceRangeErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.setProductNameValue("checking");
        addProductPage.setProductPriceValue(PRODUCT_PRICE_RANGE_VALUE);
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter price in range of 1-999!";    // No error message!
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        navigation.logout();
    }
}
