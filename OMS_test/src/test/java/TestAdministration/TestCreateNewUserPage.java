package TestAdministration;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import pages.BasePage;
import pages.administration.AddUserPage;
import pages.administration.AdministrationPage;
import pages.auth.UserInfoPage;
import tools.BaseTest;

public class TestCreateNewUserPage extends BaseTest{
	
	private static String ADMINISTRATOR_LOGIN = "admin";
	private static String ADMINISTRATOR_PASSWORD = "pass";
	private static String HEADER_TEXT_FROM_SRS = "Ordering Management System.";
	private static String UNDER_HEADER_TEXT_FROM_SRS = "Simple. Slim. Genius.";
	private static String USER_INFO_TAB_TEXT_FROM_SRS = "User Info";
	private static String ADMINISTRATION_TAB_TEXT_FROM_SRS = "Administration";
	private static String CREATE_NEW_USER_LINK_TEXT_FROM_SRS = "Create new user";
	private static String LOGIN_NAME_LINK_TEXT_FROM_SRS = "Login Name:";
	private static String FIRST_NAME_LINK_TEXT_FROM_SRS = "First Name:";
	private static String LAST_NAME_LINK_TEXT_FROM_SRS = "Last Name:";
	private static String PASSWORD_LINK_TEXT_FROM_SRS = "Password:";
	private static String CONFIRM_PASSWORD_LINK_TEXT_FROM_SRS = "Confirm Password:";
	private static String EMAIL_ADDRES_LINK_TEXT_FROM_SRS = "Email Address:";
	private static String ROLE_LINK_TEXT_FROM_SRS = "Role:";
	private static String ADMINISTRATOR_ROLE_TEXT_FROM_SRS = "Administrator";
	private static String MERCHANDISER_ROLE_TEXT_FROM_SRS = "Merchandiser";
	private static String CUSTOMER_ROLE_TEXT_FROM_SRS = "Customer";
	private static String SUPERVISOR_ROLE_TEXT_FROM_SRS = "Supervisor";
	private static String REGION_LINK_TEXT_FROM_SRS = "Region:";
	private static String EAST_REAGION_LINK_TEXT_FROM_SRS = "East";
	private static String NORTH_REGION_LINK_TEXT_FROM_SRS = "North";
	private static String CENTER_REGION_LINK_TEXT_FROM_SRS = "Center";
	private static String WEST_REGION_LINK_TEXT_FROM_SRS = "West";
	private static String SOUTH_REGION_LINK_TEXT_FROM_SRS = "South";
	private static String SUBMIT_BUTTON_TEXT_FROM_SRS = "Create";
	private static String CANCEL_BUTTON_TEXT_FROM_SRS = "Cancel";
	
	private static AddUserPage ADD_USER_PAGE;
	
	@Before
	public void setUp(){
		super.setUp();
		basePage = new BasePage(driver);
		UserInfoPage userInfoPage = basePage.login(ADMINISTRATOR_LOGIN, ADMINISTRATOR_PASSWORD);
		AdministrationPage administrationPage = userInfoPage.selectAdministrationTab();
		ADD_USER_PAGE = administrationPage.selectCreateNewUser();		
	}
	
	@Test
	public void testHeader(){
		assertEquals(HEADER_TEXT_FROM_SRS, ADD_USER_PAGE.getHeaderLinkText());
	}
	
	@Test
	public void testUnderHeader(){
		assertEquals(UNDER_HEADER_TEXT_FROM_SRS, ADD_USER_PAGE.getUnderHeaderLinkText());
	}
	
	@Test
	public void testUserInfoTab(){
		assertEquals(USER_INFO_TAB_TEXT_FROM_SRS, ADD_USER_PAGE.getUserInfoTabText());
	}
	
	@Test
	public void testAdministrationTab(){
		assertEquals(ADMINISTRATION_TAB_TEXT_FROM_SRS, ADD_USER_PAGE.getAdministrationTabText());
	}
	
	@Test
	public void testCreateNewUserLink(){
		assertEquals(CREATE_NEW_USER_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getCreateNewUserLinkText());
	}
	//login name
	@Test
	public void testLoginNameLinkText(){
		assertEquals(LOGIN_NAME_LINK_TEXT_FROM_SRS,ADD_USER_PAGE.getLoginNameLinkText());
	}
	//first name
	@Test
	public void testFirstNameLinkText(){
		assertEquals(FIRST_NAME_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getFirstNameLinkText());
	}	
	//last name
	@Test
	public void testLastNameLinkText(){
		assertEquals(LAST_NAME_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getLastNameLinkText());
	}
	

	//password
	@Test
	public void testPasswordLinkText() {
		assertEquals(PASSWORD_LINK_TEXT_FROM_SRS,
				ADD_USER_PAGE.getPasswordLinkText());
	}
	//confirm password
	@Test
	public void testConfirmPasswordLinkText(){
		assertEquals(CONFIRM_PASSWORD_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getConfirmPasswordLinkText());
	}
	//email address
	@Test
	public void testEmailAdressLinkText(){
		assertEquals(EMAIL_ADDRES_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getEmailAddressLinkText());
	}
	//role
	@Test
	public void testRoleLinkText(){
		assertEquals(ROLE_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getRoleLinkText());
	}
	
	@Test
	public void testRoleList(){
		ADD_USER_PAGE.findRoleListAndClick();
		assertEquals(ADMINISTRATOR_ROLE_TEXT_FROM_SRS, ADD_USER_PAGE.getAdministrationRoleText());
		ADD_USER_PAGE.findRoleListAndClick();
		assertEquals(MERCHANDISER_ROLE_TEXT_FROM_SRS, ADD_USER_PAGE.getMerchandiserRoleText());
		ADD_USER_PAGE.findRoleListAndClick();
		assertEquals(SUPERVISOR_ROLE_TEXT_FROM_SRS, ADD_USER_PAGE.getSupervisorRoleText());
		ADD_USER_PAGE.findRoleListAndClick();
		assertEquals(CUSTOMER_ROLE_TEXT_FROM_SRS, ADD_USER_PAGE.getCustomerRoleText());
	}
	//region
	@Test
	public void testRegionLinkText(){
		assertEquals(REGION_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getRegionLinkText());
	}
	
	@Test
	public void testRegionList(){
		ADD_USER_PAGE.findRegionListAndClick();
		assertEquals(NORTH_REGION_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getNorthRegionText());
		ADD_USER_PAGE.findRegionListAndClick();
		assertEquals(EAST_REAGION_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getEastRegionText());
		ADD_USER_PAGE.findRegionListAndClick();
		assertEquals(SOUTH_REGION_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getSouthRegionText());
		ADD_USER_PAGE.findRegionListAndClick();
		assertEquals(WEST_REGION_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getWestRegionText());
		ADD_USER_PAGE.findRegionListAndClick();
		assertEquals(CENTER_REGION_LINK_TEXT_FROM_SRS, ADD_USER_PAGE.getCenterRegionText());
	}
	//submit button
	@Test
	public void testSubmitButtonText(){
		assertEquals(SUBMIT_BUTTON_TEXT_FROM_SRS, ADD_USER_PAGE.getSubmitButtonText());
	}
	//cancel button
	public void testCancelButtonText(){
		assertEquals(CANCEL_BUTTON_TEXT_FROM_SRS, ADD_USER_PAGE.getCancelButtonText());
	}
	
}
