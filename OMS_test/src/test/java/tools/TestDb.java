package tools;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.junit.Assert.assertTrue;

public class TestDb {

    public static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/oms";
    public static final String TEST_DB_USER = "root";
    public static final String TEST_DB_PASSWORD = "root";

    @Before
    public void prepare() throws Exception {
        Operation operation =
                sequenceOf(
                        SetupDb.DELETE_ALL,
                        SetupDb.INSERT_DATA);
       // DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);

        DbSetup dbSetup = new DbSetup(new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD), operation);
        dbSetup.launch();
    }

    @Test
    public void testDb() {
        assertTrue("true", true);
    }
}

