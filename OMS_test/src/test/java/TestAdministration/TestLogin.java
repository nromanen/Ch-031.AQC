package TestAdministration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import tools.Browser;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Olesia
 *
 */
public class TestLogin  {

	private static WebDriver driver;
	private static Browser browser;
	private static final String HOME_URL = "http://localhost:8080/OMS/";
	private static final int TIMEOUT = 2;
	
	private static final String USER_NAME_FOR_CUSTOMER = "customer1";
	private static final String PASSWORD_FOR_CUSTOMER = "qwerty";
	private static final String USER_NAME_FOR_SUPERVISOR = "supervisor1";
	private static final String PASSWORD_FOR_SUPERVISOR = "qwerty";
	private static final String USER_NAME_FOR_ADMINISTRATOR = "admin1";
	private static final String PASSWORD_FOR_ADMINISTRATOR = "qwerty";
	private static final String USER_NAME_FOR_MERCHANDISER = "merch1";
	private static final String PASSWORD_FOR_MERCHANDISER = "qwerty";

	private static final String WRONG_USER_NAME_FOR_CUSTOMER = "lohghgbh";
	private static final String WRONG_PASSWORD_FOR_CUSTOMER = "qwerty4544";
	
    private static final String LINK_FOR_ORDERING = "Ordering";  
    private static final String LINK_FOR_ADMINISTRATION = "Administration";
    private static final String LINK_FOR_ITEM_MANAGEMENT = "Item Management";
		
	@Before
	public void setUp() throws Exception {
		
		driver = new FirefoxDriver();
		browser = new Browser(driver);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.get(HOME_URL);
				
	}
	
	@Test
	public void testLoginAsCustomer() throws Exception { 

		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		
		String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
		assertEquals(elementInfoOnInfoPage, "User Info");
		
		String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ORDERING).getText();
		assertEquals(linkOnInfoPage, "Ordering");
	}
	
	@Test
	public void testLoginAsSupervisor() throws Exception { 

		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_SUPERVISOR, PASSWORD_FOR_SUPERVISOR);
		
		String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
		assertEquals(elementInfoOnInfoPage, "User Info");
		
		String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ITEM_MANAGEMENT).getText();
		assertEquals(linkOnInfoPage, "Item Management");
	}
	
	@Test
	public void testLoginAsAdministrator() throws Exception { 

		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_ADMINISTRATOR, PASSWORD_FOR_ADMINISTRATOR);
		
		String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
		assertEquals(elementInfoOnInfoPage, "User Info");
		
		String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ADMINISTRATION).getText();
		assertEquals(linkOnInfoPage, "Administration");
	}
	
	@Test
	public void testLoginAsMerchandiser() throws Exception { 

		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_MERCHANDISER, PASSWORD_FOR_MERCHANDISER);
		
		String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
		assertEquals(elementInfoOnInfoPage, "User Info");
		
		String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ORDERING).getText();
		assertEquals(linkOnInfoPage, "Ordering");
	}
	
	@Test  
	public void testWrongLogin() {
		
		LoginPage login = new LoginPage(driver); 
		login.login(WRONG_USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
	    assertEquals("Your login attempt was not successful, try again.\n\nReason: Bad credentials.", browser.findElementByCssSelector("font").getText());
	}

	@Test  
	public void testWrongPassword() {
		
		LoginPage login = new LoginPage(driver); 
		login.login(USER_NAME_FOR_CUSTOMER, WRONG_PASSWORD_FOR_CUSTOMER);
		assertEquals("Your login attempt was not successful, try again.\n\nReason: Bad credentials.", browser.findElementByCssSelector("font").getText());	
	}

	@Test
	public void testEmptyLogin() {

		LoginPage login = new LoginPage(driver);
		login.login("", PASSWORD_FOR_CUSTOMER);
		assertEquals("Your login attempt was not successful, try again.\n\nReason: Bad credentials.", browser.findElementByCssSelector("font").getText());
	}	
	
	@Test  
	public void testEmptyPassword() {
			
		LoginPage login = new LoginPage(driver); 
		login.login(USER_NAME_FOR_CUSTOMER, "");
		assertEquals("Your login attempt was not successful, try again.\n\nReason: Bad credentials.", browser.findElementByCssSelector("font").getText());		
	}
	
	@After
	public void tearDown() throws Exception {

		driver.quit(); 
	}

}
