package baseTest;

import static org.junit.Assert.*;
import org.junit.Test;
import baseTest.TestConfig;
import pages.auth.LoginPage;

public class TestHomePage extends TestConfig {
	private String headerLinkTextFromSRS = "Ordering Management System.";
	private String userNameLinkFromSRS = "User Name:*";
	private String passwordLinkFromSRS = "Password:*";
	private String rememberMeLinkFromSRS = "Remember me";
	private String loginLinkFromSRS = "Login";
	private String resetLinkFromSRS = "Reset";

	LoginPage loginPage = new LoginPage(browser.getDriver());//browser.getDriver().get(BASEURL);
	
	@Test
	public void testHeader() {
		// LoginPage loginPage = new LoginPage(browser.getDriver());
		assertEquals(headerLinkTextFromSRS, loginPage.getHeaderItemText());
	}

	@Test
	public void testIsUserNameLineText() {
		assertEquals(userNameLinkFromSRS, loginPage.getUserNameItemText());
	}

	@Test
	public void testIsUserNameInputLine() {
		assertTrue(loginPage.isUserNameLine());
	}

	@Test
	public void testIsPasswordLineCorrectText() {
		assertEquals(passwordLinkFromSRS, loginPage.getPasswordItemText());
	}

	@Test
	public void testIsPasswordInputLine() {
		assertTrue(loginPage.isPasswordLine());
	}

	@Test
	public void testIsRememberMe() {
		assertEquals(rememberMeLinkFromSRS, loginPage.getRememberMeItemText());
	}

	public void isRememberMeCheckBox() {
		assertTrue(loginPage.isRememberMeCheckBox());
	}

	@Test
	public void testIsLogin() {
		assertTrue(loginPage.isLoginButton());
		assertEquals(loginLinkFromSRS, loginPage.getLoginItemText());

	}

	@Test
	public void testIsReset() {
		assertTrue(loginPage.isResetButton());
		assertEquals(resetLinkFromSRS, loginPage.getResetItemText());
	}
	
}