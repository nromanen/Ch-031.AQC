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
 * This test case is designed for testing the Edit Product functionality.
 * @author Olya.
 */
public class EditProductTest extends DBUnitConfig {

    private static final String EDIT_PRODUCT_DESCRIPTION = "NoDescription";
    private static final String EDIT_PRODUCT_PRICE = "111.0";
    private WebDriver driver;
    private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME = "lemon";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";
    private static final String EDIT_PRODUCT_NAME = "1NotName";
    private static final String PRODUCT_DESCRIPTION = "Very yellow";
    private static final String PRODUCT_PRICE = "14.0";

    public EditProductTest(String name) {
        super(name);
    }


    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        beforeData = new IDataSet[]{getDataFromFile("data/productData.xml")};
        super.setUp();
        navigation = new Navigation(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();

    }

    @Test
    public void testEditName() throws Exception {
        AddProductPage addProductPage = itemManagementPage.clickEditLinkOnProduct(PRODUCT_NAME);
        addProductPage.setProductNameValue(EDIT_PRODUCT_NAME);
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(EDIT_PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(2));
    }

    @Test
    public void testEditDescription() throws Exception {
        AddProductPage addProductPage = itemManagementPage.clickEditLinkOnProduct(PRODUCT_NAME);
        addProductPage.setProductDescriptionValue(EDIT_PRODUCT_DESCRIPTION);
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(EDIT_PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(2));
    }

    @Test
    public void testEditPrice() throws Exception {
        AddProductPage addProductPage = itemManagementPage.clickEditLinkOnProduct(PRODUCT_NAME);
        addProductPage.setProductPriceValue(EDIT_PRODUCT_PRICE);
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(EDIT_PRODUCT_PRICE, row.getNthColumnValue(2));
    }

    @After
    public void tearDown() throws Exception {
        navigation.logout();
        driver.quit();
        super.tearDown();
    }
}