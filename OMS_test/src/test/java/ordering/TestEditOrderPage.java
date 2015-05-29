package ordering;

import org.junit.*;
import pages.auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ordering.AddItemPage;
import pages.ordering.EditOrderPage;
import tools.Browser;
import tools.CheckTableValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEditOrderPage {
    private static WebDriver driver;
    private static String BASE_URL = "http://localhost:8080/OMS";
    EditOrderPage editOrderPage;


    @BeforeClass
    public  static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/lumberjack85/Desktop/oms_git/Ch-031.AQC/OMS_test/chromedriver");
        driver = new ChromeDriver();
        Browser browser = new Browser(driver);
        browser.goToUrl(BASE_URL + "/login.htm");
        pages.auth.LoginPage auth = new LoginPage(driver);
        auth.login("login3", "qwerty");
        browser.findElementByLinkText("Ordering").click();

    }

    @Test
    public  void testEditOrderStatus() throws javax.script.ScriptException{
        Browser browser = new Browser(driver);
        CheckTableValue tableValue = new CheckTableValue(driver);
        if (tableValue.findValue("list", "Status", 0).equals("Created")){
            browser.findElementByXpath("//a[contains(@href,'orderId=1')]").click();
        }
        WebElement element = browser.findElementByCssSelector("input[value=\"Add Item\"]");

        assertTrue("Can edit order", element != null);
    }

    @Test
    public void testAddItem() throws javax.script.ScriptException{
        Browser browser = new Browser(driver);
        browser.findElementByCssSelector("input[value=\"Add Item\"]").click();
        //editOrderPage.addItemClick();

        AddItemPage add1Item = new AddItemPage(driver);
        add1Item.selectFirstItem();
        String itemQuantity = "2";
        add1Item.setItemQuantity(itemQuantity);
        add1Item.clickDoneButton();
        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Item was added, quantity is right", itemQuantity, tableValue.findValue("list", "Quantity", 1));


    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
