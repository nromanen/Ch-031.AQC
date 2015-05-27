package ordering;

import auth.AuthPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import tools.Browser;
import tools.CheckTableValue;

import static org.junit.Assert.assertTrue;

public class TestEditOrderPage {
    private static WebDriver driver;
    private static String BASE_URL = "http://localhost:8080/OMS";


    @Before
    public  void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/lumberjack85/Desktop/oms_git/Ch-031.AQC/OMS_test/chromedriver");
        driver = new ChromeDriver();
        Browser browser = new Browser(driver);
        browser.goToUrl(BASE_URL + "/login.htm");
        AuthPage auth = new AuthPage(driver);
        auth.login("login3", "qwerty");
        browser.findElementByLinkText("Ordering").click();
    }

    @Test
    public  void testEditOrderStatus() throws javax.script.ScriptException{
        Browser browser = new Browser(driver);
        CheckTableValue tableValue = new CheckTableValue(driver);
        if (tableValue.findValue("list", "Status", 0).equals("Created")){
            browser.findElementByCssSelector("a[href='orderItemsOpen.htm?orderId=1]").click();
        }
        WebElement element = browser.findElementByCssSelector("input[value='Add Item']");
        assertTrue("Can edit order", element != null);
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
