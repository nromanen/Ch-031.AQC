package ordering;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.*;
import pages.auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.auth.UserInfoPage;
import pages.ordering.AddItemPage;
import pages.ordering.EditOrderPage;
import pages.ordering.OrderPage;
import tools.Browser;
import tools.CheckTableValue;
import tools.DBUnitConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEditOrderPage extends DBUnitConfig{
    private static WebDriver driver;
    private static String BASE_URL = "http://localhost:8080/OMS2";
    EditOrderPage editOrderPage;
    String LOGIN = "cust";
    String PASS = "pass";

    public TestEditOrderPage(String name) {
        super(name);
    }


    @Before
    public  void setUp() throws Exception {
        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset.xml"));
        super.setUp();

        System.setProperty("webdriver.chrome.driver", "/home/lumberjack85/Desktop/oms_git/Ch-031.AQC/OMS_test/chromedriver");
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @Test
    public  void testEditOrderStatus() throws javax.script.ScriptException{

        LoginPage lp = new LoginPage(driver);
        UserInfoPage ui = lp.login(LOGIN, PASS);
        OrderPage op = ui.goToOrderingTab();
        EditOrderPage eo = op.goTo1EditOrder();
        assertTrue("Can edit order", eo.isAddItem() == true);
    }

    @Test
    public void testAddItem() throws Exception{
        LoginPage lp = new LoginPage(driver);
        UserInfoPage ui = lp.login(LOGIN, PASS);
        OrderPage op = ui.goToOrderingTab();
        EditOrderPage eo = op.goTo1EditOrder();
        AddItemPage add = eo.addItemClick();
        add.selectFirstItem();
        String itemQuantity = "2";
        add.setItemQuantity(itemQuantity);
        add.clickDoneButton();
        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Item was added, quantity is wrong", itemQuantity, tableValue.findValue("list", "Quantity", 1));


    }

    @After
    public  void tearDown() throws Exception{
        super.tearDown();
        driver.quit();
    }

}
