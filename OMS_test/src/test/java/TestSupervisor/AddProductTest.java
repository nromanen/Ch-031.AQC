package TestSupervisor;

import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.DBUnitConfig;
import tools.Navigation;
import tools.TableRow;
/**
 * This test case is designed for testing the Add Product functionality.
 * @author Olya.
 */
public class AddProductTest extends DBUnitConfig {
    private WebDriver driver;
    private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME = "Test Product";
    private static final String PRODUCT_NAME_DELETE = "lemon";
    private static final String PRODUCT_DESCRIPTION = "Function test";
    private static final String PRODUCT_PRICE = "65.0";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";

    public AddProductTest(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        navigation = new Navigation(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    /**
     * This test verify that new product with name PRODUCT_NAME, description PRODUCT_DESCRIPTION
     * and price PRODUCT_PRICE is created and is visible in product table on Item Management page.
     */
    public void testAddProducts() throws Exception {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.setProductNameValue(PRODUCT_NAME);
        assertEquals(PRODUCT_NAME, addProductPage.getProductNameValue());
        addProductPage.setProductDescriptionValue(PRODUCT_DESCRIPTION);
        assertEquals(PRODUCT_DESCRIPTION,addProductPage.getProductDescriptionValue());
        addProductPage.setProductPriceValue(PRODUCT_PRICE);
        assertEquals(PRODUCT_PRICE,addProductPage.getProductPriceValue());
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(2));
    }

    @After
    public void tearDown() throws Exception {
        navigation.logout();
        driver.quit();
    }
}

