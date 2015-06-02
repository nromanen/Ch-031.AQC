package TestAdministration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.administration.UsersPage;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import tools.BaseDBTest;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class UserFilterTest2 extends BaseDBTest {
    private String column;
    private String match;
    private String value;
    private int expected;

    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_PASS = "pass";

    public UserFilterTest2(String column, String match, String value, int expected) throws Exception {
        super("UserFilterTest");

        this.column = column;
        this.match = match;
        this.value = value;
        this.expected = expected;
    }


    @Parameterized.Parameters
    public static Collection Filters() {
        return Arrays.asList(new Object[][] {
                { "All Columns", "equals", "", 5 },
                
                { "All Columns", "equals", "Alice", 1 },
                { "All Columns", "not equals to", "Alice", 4 },
                { "All Columns", "starts with", "Ev", 2 },
                { "All Columns", "contains", "A", 3 },
                { "All Columns", "does not contain", "A", 2 },

                { "First Name", "equals", "Alice", 1 },
                { "First Name", "not equals to", "Alice", 4 },
                { "First Name", "starts with", "A", 2 },
                { "First Name", "contains", "A", 2 },
                { "First Name", "does not contain", "A", 3 },

                { "Last Name", "equals", "E.", 2 },
                { "Last Name", "not equals to", "E.", 3 },
                { "Last Name", "starts with", "A", 2 },
                { "Last Name", "contains", "A", 2 },
                { "Last Name", "does not contain", "A", 3 },

                { "Login", "equals", "admin", 1 },
                { "Login", "not equals to", "admin", 4 },
                { "Login", "starts with", "User", 4 },
                { "Login", "contains", "s", 4 },
                { "Login", "does not contain", "s", 1 },

                { "Role", "equals", "Administrator", 2 },
                { "Role", "not equals to", "Administrator", 3 },
                { "Role", "starts with", "A", 2 },
                { "Role", "contains", "or", 4 },
                { "Role", "does not contain", "or", 1 },

                { "Region", "equals", "North", 2 },
                { "Region", "not equals to", "North", 3 },
                { "Region", "starts with", "N", 2 },
                { "Region", "contains", "o", 4 },
                { "Region", "does not contain", "o", 1 },

        });
    }


    @Test
    public void testUserFilter() {
        // login and goto page we need
        LoginPage lp = new LoginPage(driver);
        UserInfoPage uip = lp.login(ADMIN_NAME,ADMIN_PASS);
        UsersPage up = uip.gotoUsers();

        up.setFilter(this.column, this.match, this.value);

        int actual = up.getFoundUsers();
        assertEquals(expected, actual);
    }
}