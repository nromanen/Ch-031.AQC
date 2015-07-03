package baseTest;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.auth.LoginPage;
import tools.BaseDBTest;


public class TestHomePage extends BaseDBTest {
	private static String HEADER_TEXT_FROM_SRS = "Ordering Management System.";
	private static String USER_NAME_TEXT_FROM_SRS = "User Name:*";
	private static String PASSWORD_TEXT_FROM_SRS = "Password:*";
	private static String REMEMBER_ME_LINK_TEXT_FROM_SRS = "Remember me";
	private static String LOGIN_TEXT_FROM_SRS = "Login";
	private static String RESET_TEXT_FROM_SRS = "Reset";
	private static LoginPage LOGIN_PAGE;
	@BeforeClass
	public static void initialize(){
		BaseDBTest.initialize();
		LOGIN_PAGE = new LoginPage(browser.getDriver());		
	}
	
	@Test
	public void testHeader() {
		assertEquals(HEADER_TEXT_FROM_SRS, LOGIN_PAGE.getHeaderItemText());
	}

	@Test
	public void testIsUserNameLineText() {
		assertEquals(USER_NAME_TEXT_FROM_SRS, LOGIN_PAGE.getUserNameItemText());
	}

	@Test
	public void testIsUserNameInputLine() {
		assertTrue(LOGIN_PAGE.isUserNameLine());
	}

	@Test
	public void testIsPasswordLineCorrectText() {
		assertEquals(PASSWORD_TEXT_FROM_SRS, LOGIN_PAGE.getPasswordItemText());
	}

	@Test
	public void testIsPasswordInputLine() {
		assertTrue(LOGIN_PAGE.isPasswordLine());
	}

	@Test
	public void testIsRememberMe() {
		assertEquals(REMEMBER_ME_LINK_TEXT_FROM_SRS, LOGIN_PAGE.getRememberMeItemText());
	}

	public void isRememberMeCheckBox() {
		assertTrue(LOGIN_PAGE.isRememberMeCheckBox());
	}

	@Test
	public void testIsLogin() {
		assertTrue(LOGIN_PAGE.isLoginButton());
		assertEquals(LOGIN_TEXT_FROM_SRS, LOGIN_PAGE.getLoginItemText());

	}

	@Test
	public void testIsReset() {
		assertTrue(LOGIN_PAGE.isResetButton());
		assertEquals(RESET_TEXT_FROM_SRS, LOGIN_PAGE.getResetItemText());
	}
	
}