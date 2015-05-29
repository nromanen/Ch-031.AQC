package tools;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import java.io.IOException;
import java.util.Properties;

public class DBUnitConfig extends DBTestCase {
    private Properties prop;
    protected IDataSet beforeData;
    protected IDataSet initialDataSet;

    public DBUnitConfig(String name) {
        super(name);
        try {
            initialDataSet = new FlatXmlDataSetBuilder().build(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream(("initialData.xml")));
        } catch (DataSetException e) {
            e.printStackTrace();
        }
        prop = new Properties();
        try {
            prop.load(Thread.currentThread()
                      .getContextClassLoader().getResourceAsStream("db.config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("db.driver"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("db.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("db.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("db.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }
 
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(initialDataSet, beforeData);
    }
 
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

}