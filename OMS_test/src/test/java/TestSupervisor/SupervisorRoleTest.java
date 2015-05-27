package TestSupervisor;

import pages.auth.LoginPage;
import pages.administration.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tools.Browser;
import tools.ColoredString;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SupervisorRoleTest {
    WebDriver driver;
    String supervisorLogin = "login2";
    String supervisorPassword = "qwerty";
    ItemManagementPage itemManagementPage;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        Browser browser = new Browser(driver);
        browser.goToUrl("http://localhost:8080/OMS/login.htm");
        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(supervisorLogin, supervisorPassword);
        itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    public void testFilterByValues() throws Exception {
        List<String> expectedValues = new ArrayList<String>();
        expectedValues.add("Name");
        expectedValues.add("Description");
        List<String> actualValues = itemManagementPage.getFilterValues();
        assertEquals(expectedValues.size(), actualValues.size());
        for (String actualValue : actualValues) {
            assertTrue(expectedValues.contains(actualValue));
        }
    }

    @Test
    public void testFilterByDefaultValue() throws Exception {
        String expected = "Name";
        String actual = itemManagementPage.getFilterCurrentValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testProductTableHeaders() throws Exception {
        List<String> expected = Arrays.asList("Name", "Description", "Price", "Edit", "Delete");
        List<String> actual = itemManagementPage.getProductTableHeadersNames();
        assertEquals(expected.size(), actual.size());
        for (String actualName : actual) {
            assertTrue(expected.contains(actualName));
        }
    }

    @Ignore
    public void testProductListShowItemsDefaultValue() throws Exception {
        String expectedShowItemsLabel = "Show 10 items";
        int expectedNumberOfProducts = 5;
        String actualShowItemsLabel = itemManagementPage.getShowItemText();
        int actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
        assertEquals(expectedShowItemsLabel, actualShowItemsLabel);
        assertEquals(expectedNumberOfProducts, actualNumberOfProduct);
    }

    @Test
    public void testClickShowItems() throws Exception {
        itemManagementPage.clickShowItemLink();
        String expectedShowItemsLabel = "Show 5 items";
        int expectedNumberOfProducts = 10;
        String actualShowItemsLabel = itemManagementPage.getShowItemText();
        int actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
        assertEquals(expectedShowItemsLabel, actualShowItemsLabel);
        assertEquals(expectedNumberOfProducts, actualNumberOfProduct);

        itemManagementPage.clickShowItemLink();
        expectedShowItemsLabel = "Show 10 items";
        expectedNumberOfProducts = 5;
        actualShowItemsLabel = itemManagementPage.getShowItemText();
        actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
        assertEquals(expectedShowItemsLabel, actualShowItemsLabel);
        assertEquals(expectedNumberOfProducts, actualNumberOfProduct);
    }

//    @Test
//    public void testClickCreateReport() throws Exception {
//        ReportPage reportPage = itemManagementPage.goToCreateReport();
//        assertNotNull(reportPage);
//    }

    @Test
    public void testClickAddProduct() throws Exception {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        assertNotNull(addProductPage);

    }

    @Test
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
    public void testProductPriceEmptyErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter product price!";
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test // Error!!!!
    public void testProductPriceCharactersErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        Browser wc = new Browser(driver);
        wc.findElementById("price").sendKeys("#4rr");
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter only numbers!";    // "Please enter double value!"
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test // Error!!!!
    public void testProductPriceRangeErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        Browser wc = new Browser(driver);
        wc.findElementById("price").sendKeys("1765");
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter price in range of 1-999!";    // No error message!!!!!
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }


}