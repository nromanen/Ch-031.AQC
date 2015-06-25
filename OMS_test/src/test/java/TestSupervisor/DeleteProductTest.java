package TestSupervisor;

import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.ItemManagementPage;
import tools.DBUnitConfig;
import tools.Navigation;

/**
 * This test case is designed for testing the Delete Product functionality.
 * @author Olya
 */
public class DeleteProductTest extends DBUnitConfig {

    private WebDriver driver;
    private Navigation navigation;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME_DELETE = "lemon";
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";

    public DeleteProductTest(String name) {
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
    public void testDeleteProduct() throws Exception {
        itemManagementPage.clickDeleteLinkOnProductAndDismiss(PRODUCT_NAME_DELETE);
        assertNotNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
        itemManagementPage.clickDeleteLinkOnProductAndAccept(PRODUCT_NAME_DELETE);
        assertNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
    }

    @After
    public void tearDown() throws Exception {
        navigation.logout();
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getConnection().createDataSet());
    }
}