package TestAdministration;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.administration.AdministrationPage;
import tools.BaseTest;

public class TestAdministrationPage extends BaseTest{

	private static String ADMINISTRATOR_LOGIN = "admin";
	private static String ADMINISTRATOR_PASSWORD = "pass";
	private static String HEADER_TEXT_FROM_SRS = "Ordering Management System.";
	private static String UNDER_HEADER_TEXT_FROM_SRS = "Simple. Slim. Genius.";
	private static String USER_INFO_TAB_TEXT_FROM_SRS = "User Info";
	private static String ADMINISTRATION_TAB_TEXT_FROM_SRS = "Administration";
	private static String CREATE_NEW_USER_TEXT_FROM_SRS = "Create new user";
	private static String FIELD_FILTER_TEXT_FROM_SRS = "Filed Filter:";
	private static String FIRST_NAME_COLUMN_TEXT_FROM_SRS = "First name";
	private static String LAST_NAME_COLUMN_TEXT_FROM_SRS = "Last name";
	private static String LOGIN_COLUMN_TEXT_FROM_SRS = "Login name";
	private static String ROLE_COLUMN_TEXT_FROM_SRS = "Role";
	private static String REGION_COLUMN_TEXT_FROM_SRS = "Region";
	private static String EDIT_COLUMN_TEXT_FROM_SRS = "Edit";
	private static String DELETE_COLUMN_TEXT_FROM_SRS = "Delete";
	private static String PAGE_COUNT_LINK_TEXT_FROM_SRS = "Page #: 1 from 1";

	
	private static AdministrationPage ADM_PAGE;
	
	@Before
	public void setUp(){
		super.setUp();
		basePage = new BasePage(driver);
		UserInfoPage userInfoPage = basePage.login(ADMINISTRATOR_LOGIN, ADMINISTRATOR_PASSWORD);
		ADM_PAGE = userInfoPage.selectAdministrationTab();		
	}
	
	@Test
	public void testHeader(){
		assertEquals(HEADER_TEXT_FROM_SRS, ADM_PAGE.getHeaderLinkText());
	}
	
	@Test
	public void testUnderHeaderLink(){
		assertEquals(UNDER_HEADER_TEXT_FROM_SRS,ADM_PAGE.getUnderHeaderLinkText());
	}
	
	@Test
	public void testUserInfoTabText(){
		assertEquals(USER_INFO_TAB_TEXT_FROM_SRS, ADM_PAGE.getUserInfoTabText());
	}
	
	@Test
	public void testAdministrationTab(){
		assertEquals(ADMINISTRATION_TAB_TEXT_FROM_SRS, ADM_PAGE.getAdministrationTabText());
	}
	
	@Test
	public void testCreateNewUser(){
		assertEquals(CREATE_NEW_USER_TEXT_FROM_SRS, ADM_PAGE.getCreateNewUserLinkText());
	}
	
	@Test
	public void testFieldFilterLink(){
		assertEquals(FIELD_FILTER_TEXT_FROM_SRS, ADM_PAGE.getFieldFilterLinkText());
	}
	
	@Test
	public void testFirstNameColumn(){
		assertEquals(FIRST_NAME_COLUMN_TEXT_FROM_SRS,ADM_PAGE.getFirstNameColumnText());
	}
	
	@Test
	public void testLastNameColumn(){
		assertEquals(LAST_NAME_COLUMN_TEXT_FROM_SRS,ADM_PAGE.getLastNameColumnText());
	}
	
	@Test
	public void testLoginColumn(){
		assertEquals(LOGIN_COLUMN_TEXT_FROM_SRS,ADM_PAGE.getLoginColumnText());
	}
	
	@Test
	public void testRoleColumn(){
		assertEquals(ROLE_COLUMN_TEXT_FROM_SRS, ADM_PAGE.getRoleColumnText());
	}
	
	@Test
	public void testRegionColumn(){
		assertEquals(REGION_COLUMN_TEXT_FROM_SRS,ADM_PAGE.getRegionColumnText());
	}
	
	@Test
	public void testEditColumn(){
		assertEquals(EDIT_COLUMN_TEXT_FROM_SRS, ADM_PAGE.getEditColumnText());
	}
	
	@Test
	public void testDeleteColumn(){
		assertEquals(DELETE_COLUMN_TEXT_FROM_SRS, ADM_PAGE.getDeleteColumnText());
	}
	
	@Test
	public void testPageCountLink(){
		assertEquals(PAGE_COUNT_LINK_TEXT_FROM_SRS,ADM_PAGE.getPageCountText());
	}
	
	
}
