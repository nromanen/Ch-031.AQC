package tools;

import org.dbunit.*;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Properties;

public class DBUnitConfig{
    private Properties prop;
    protected IDataSet[] beforeData;
    private static final Logger logger = LoggerFactory.getLogger(DBUnitConfig.class);
    private IDatabaseTester tester;
    private IOperationListener operationListener;


    public DBUnitConfig() {

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

    protected FlatXmlDataSet getDataFromFile(String fileName)  {
        try {
			return new FlatXmlDataSetBuilder().build(
			       Thread.currentThread().getContextClassLoader()
			               .getResourceAsStream(fileName));
		} catch (DataSetException e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * Returns the test dataset.
     */

    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(beforeData);
    }

    /**
     * Returns the database operation executed in test cleanup.
     */

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE;
    }
    /**
     * Returns the test database connection.
     */

    protected IDatabaseConnection getConnection() throws Exception {
        logger.debug("getConnection() - start");

        final IDatabaseTester databaseTester = getDatabaseTester();
        IDatabaseConnection connection = databaseTester.getConnection();
        // Ensure that users have the possibility to configure the connection's configuration
        setUpDatabaseConfig(connection.getConfig());
        return connection;
    };


    /**
     * Creates a IDatabaseTester for this testCase.<br>
     *
     * @throws Exception
     */
    protected IDatabaseTester newDatabaseTester() throws Exception {
        return new PropertiesBasedJdbcDatabaseTester();
    }

    /**
     * Designed to be overridden by subclasses in order to set additional configuration
     * parameters for the {@link IDatabaseConnection}.
     * @param config The settings of the current {@link IDatabaseConnection} to be configured
     */
    protected void setUpDatabaseConfig(DatabaseConfig config)
    {
        // Designed to be overridden.
    }

    /**
     * Gets the IDatabaseTester for this testCase.<br>
     * If the IDatabaseTester is not set yet, this method calls
     * newDatabaseTester() to obtain a new instance.
     * @throws Exception
     */
    protected IDatabaseTester getDatabaseTester() throws Exception {
        if ( this.tester == null ) {
            this.tester = newDatabaseTester();
        }
        return this.tester;
    }

    /**
     * Returns the database operation executed in test setup.
     */
    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.CLEAN_INSERT;
    }

    public void initDataBase(String... dataFile) {
		if (dataFile.length != 0) {
			IDataSet[] data = new IDataSet[dataFile.length];
			for (int i = 0; i < dataFile.length; i++) {
				data[i] = getDataFromFile(dataFile[i]);
			}
			beforeData = data;
			setUp();
		} else {
			logger.info("Incorrect use of initDataBase(String ...) method");
		}
	}


    ////////////////////////////////////////////////////////////////////////////
    // TestCase class

    public void setUp(){
    	try{
        logger.debug("setUp() - start");
        final IDatabaseTester databaseTester = getDatabaseTester();
        databaseTester.setSetUpOperation( getSetUpOperation() );
        databaseTester.setDataSet( getDataSet() );
        databaseTester.setOperationListener(getOperationListener());
        databaseTester.onSetup();
    	}catch(Exception e){
    		logger.debug("setUp error");
    	}
    }

    public void tearDown()
    {
        logger.debug("tearDown() - start");

        try {
            final IDatabaseTester databaseTester = getDatabaseTester();
            databaseTester.setTearDownOperation( getTearDownOperation() );
            databaseTester.setDataSet( getDataSet() );
            databaseTester.setOperationListener(getOperationListener());
            databaseTester.onTearDown();
        }
        catch (Exception e){
        	logger.debug("Error in DatabaseTester");
        }
        finally {
            tester = null;
        }
    }
    
    

    /**
     * @return The {@link org.dbunit.IOperationListener} to be used by the {@link IDatabaseTester}.
     * @since 2.4.4
     */
    protected IOperationListener getOperationListener()
    {
        logger.debug("getOperationListener() - start");
        if(this.operationListener==null){
            this.operationListener = new DefaultOperationListener(){
                public void connectionRetrieved(IDatabaseConnection connection) {
                    super.connectionRetrieved(connection);
                    // When a new connection has been created then invoke the setUp method
                    // so that user defined DatabaseConfig parameters can be set.
                    setUpDatabaseConfig(connection.getConfig());
                }
            };
        }
        return this.operationListener;
    }
}



