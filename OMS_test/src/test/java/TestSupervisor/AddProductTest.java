package TestSupervisor;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.*;


import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * This test case is designed for testing the Add Product functionality.
 * @author Olya.
 */
public class AddProductTest extends DBUnitConfig {
    private WebDriver driver = new FirefoxDriver();
    private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME = "Test Product";
    private static final String PRODUCT_DESCRIPTION = "Function test";
    private static final String PRODUCT_PRICE = "65.0";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";

    public AddProductTest(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        navigation = new Navigation(driver);
        LoginPage loginPage = new LoginPage(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        ItemManagementPage itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    /**
     * This test verify that new product with name PRODUCT_NAME, description PRODUCT_DESCRIPTION
     * and price PRODUCT_PRICE is created and is visible in product table on Item Management page.
     */
    public void testAddProducts() throws Exception {
        AddProductPage addProductPage = new ItemManagementPage(driver).goToAddProduct();
        addProductPage.setProductNameValue(PRODUCT_NAME);
        assertEquals(PRODUCT_NAME,addProductPage.getProductNameValue());
        addProductPage.setProductDescriptionValue(PRODUCT_DESCRIPTION);
        assertEquals(PRODUCT_DESCRIPTION,addProductPage.getProductDescriptionValue());
        addProductPage.setProductPriceValue(PRODUCT_PRICE);
        assertEquals(PRODUCT_PRICE,addProductPage.getProductPriceValue());
        addProductPage.clickOkButton();
        ItemManagementPage itemManagementPage = new ItemManagementPage(driver);
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(2));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(3));
    }

    @After
    public void tearDown() throws Exception {
        navigation.logout();
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getConnection().createDataSet());
    }
}

