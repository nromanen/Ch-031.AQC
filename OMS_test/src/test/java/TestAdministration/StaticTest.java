package TestAdministration;

import org.junit.Test;
import pages.administration.UsersPage;
import pages.auth.UserInfoPage;
import tools.BaseTest;

import static org.junit.Assert.assertEquals;

public class StaticTest extends BaseTest {

    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_PASS = "pass";

    @Test
    public void TestLogoEN() {
        UserInfoPage userinfopage = new UserInfoPage(driver);
        userinfopage.setEnglish();

        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();

        assertEquals("Ordering Management System.", userspage.getLogoText());
    }

    @Test
    public void TestPageDescriptionEN() {
        UserInfoPage userinfopage = new UserInfoPage(driver);
        userinfopage.setEnglish();

        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();

        assertEquals("This page is appointed for creating new and managing existing users", userspage.getDescriptionText());
    }

    @Test
    public void TestFoundUsersTextEN() {
        UserInfoPage userinfopage = new UserInfoPage(driver);
        userinfopage.setEnglish();

        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();

        assertEquals("Found users:", userspage.getFoundUsersText());
    }





    @Test
    public void TestLogoUA() {
        UserInfoPage userinfopage = new UserInfoPage(driver);
        userinfopage.setEnglish();

        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();

        assertEquals("Ordering Management System.", userspage.getLogoText());
    }

    @Test
    public void TestPageDescriptionUA() {
        UserInfoPage userinfopage = new UserInfoPage(driver);
        userinfopage.setEnglish();

        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();

        assertEquals("Дана сторінка призначена для створення та керування користувачами", userspage.getDescriptionText());
    }

    @Test
    public void TestFoundUsersTextUA() {
        UserInfoPage userinfopage = new UserInfoPage(driver);
        userinfopage.setEnglish();

        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();

        assertEquals("Знайдено користувачів:", userspage.getFoundUsersTextUA());
    }
}