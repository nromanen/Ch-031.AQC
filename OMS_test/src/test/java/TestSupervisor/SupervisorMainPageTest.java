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
import tools.Browser;
import tools.ColoredString;
import tools.DBUnitConfig;
//import tools.Navigation;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This test is designed for testing of the Item Management view
 */
public class SupervisorMainPageTest extends DBUnitConfig {
    private static WebDriver driver = new FirefoxDriver();
    //private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";

    public SupervisorMainPageTest(String name) {
        super(name);
    }

    /*@Before
    public void setUp() throws Exception {
        beforeData = new IDataSet[] {getDataFromFile("productData.xml")};
        super.setUp();
        navigation = new Navigation(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
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

    @Test
    public void testClickShowItems() throws Exception {
        String expectedShowItemsLabel = "Show 10 items";
        int expectedNumberOfProducts = 5;
        String actualShowItemsLabel = itemManagementPage.getShowItemText();
        int actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
        assertEquals(expectedShowItemsLabel, actualShowItemsLabel);
        assertEquals(expectedNumberOfProducts, actualNumberOfProduct);

        itemManagementPage.clickShowItemLink();
        expectedShowItemsLabel = "Show 5 items";
        expectedNumberOfProducts = 10;
        actualShowItemsLabel = itemManagementPage.getShowItemText();
        actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
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

    @Test
    public void testClickAddProduct() throws Exception {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        assertNotNull(addProductPage);
    }


    @After
    public void tearDown() throws Exception {
        super.tearDown();
        navigation.logout();
        }*/
}