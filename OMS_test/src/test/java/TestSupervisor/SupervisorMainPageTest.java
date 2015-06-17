package TestSupervisor;

import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.DBUnitConfig;
import tools.Navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This test case is designed for testing of the Item Management view.
 * @author Olya.
 */
public class SupervisorMainPageTest extends DBUnitConfig {
    private static WebDriver driver = new FirefoxDriver();
    private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";

    public SupervisorMainPageTest(String name) {
        super(name);
    }

    static Logger log = LoggerFactory.getLogger(SupervisorMainPageTest.class);

    @Before
    public void setUp() throws Exception {
        beforeData = new IDataSet[] {getDataFromFile("data/productData.xml")};
        super.setUp();
        navigation = new Navigation(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    /**
     * This test verify that "Search by" drop-down list on ItemManagement page
     * have two options – "Name" and "Description" (according to SRS).
     */
    public void testFilterByValues() throws Exception {
        log.info("------TestFilterByValues------");
        List<String> expectedValues = new ArrayList<String>();
        expectedValues.add("Name");
        expectedValues.add("Description");
        List<String> actualValues = itemManagementPage.getFilterValues();
        assertEquals(expectedValues.size(), actualValues.size());
        for (String actualValue : actualValues) {
            assertTrue(expectedValues.contains(actualValue));
        }
        log.info("----TestFilterByValues pass----");
    }

    @Test
    /**
     * This test verify that in "Search by" drop-down list on ItemManagement page
     * by default set value to "Name" (according to SRS).
     */
    public void testFilterByDefaultValue() throws Exception {
        String expected = "Name";
        String actual = itemManagementPage.getFilterCurrentValue();
        assertEquals(expected, actual);
    }

    @Test
    /**
     * This test verify that columns of product table on Item Management page have
     * such labels: "Name", "Description", "Price", "Edit" and "Delete" (according to SRS).
     */
    public void testProductTableHeaders() throws Exception {
        List<String> expected = Arrays.asList("Name", "Description", "Price", "Edit", "Delete");
        List<String> actual = itemManagementPage.getProductTableHeadersNames();
        assertEquals(expected.size(), actual.size());
        for (String actualName : actual) {
            assertTrue(expected.contains(actualName));
        }
    }

    @Test
    /**
     * This test verify a correct work of "Show 10 items/Show 5 items" links (according to SRS).
     */
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
    /**
     * This test verify that after clicking on AddProduct link Supervisor can access "Add Product" page.
     */
    public void testClickAddProduct() throws Exception {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        assertNotNull(addProductPage);
    }


    @After
    public void tearDown() throws Exception {
        super.tearDown();
        navigation.logout();
        }
}