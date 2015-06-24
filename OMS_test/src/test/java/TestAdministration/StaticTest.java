package TestAdministration;

import org.junit.Test;
import pages.administration.UsersPage;
import tools.BaseTest;

import static org.junit.Assert.assertEquals;

public class StaticTest extends BaseTest {

    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_PASS = "pass";

    @Test
    public void TestLogoEN() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        userspage.setEnglish();

        assertEquals("Ordering Management System.", userspage.getLogoText());
    }

    @Test
    public void TestPageDescriptionEN() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        userspage.setEnglish();

        assertEquals("This page is appointed for creating new and managing existing users", userspage.getDescriptionText());
    }

    @Test
    public void TestFoundUsersTextEN() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        userspage.setEnglish();

        assertEquals("Found users:", userspage.getFoundUsersText());
    }





    @Test
    public void TestLogoUA() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        userspage.setUkrainian();

        assertEquals("Ordering Management System.", userspage.getLogoText());
    }

    @Test
    public void TestPageDescriptionUA() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        userspage.setUkrainian();

        assertEquals("Дана сторінка призначена для створення та керування користувачами", userspage.getDescriptionText());
    }

    @Test
    public void TestFoundUsersTextUA() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        userspage.setUkrainian();

        assertEquals("Знайдено користувачів:", userspage.getFoundUsersTextUA());
    }
}