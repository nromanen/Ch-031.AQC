package tools;

import entity.AbstractEntity;
import entity.Product;
import org.dbunit.Assertion;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import javax.persistence.Table;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a set of util methods to make the work with database in tests easier.
 * @author Olya.
 */
public class DbUtils extends DBUnitConfig {

    public DbUtils(String name) {
        super(name);
    }

    /**
     * Persist and write to file n random products.
     * @param numberOfProducts sets the number of generated products.
     * @throws Exception if products could not be written in file.
     */
    public void persistAndWriteToFileRandomProducts(int numberOfProducts) throws Exception {
        persistAndWriteToFile(EntityUtils.generateRandomProducts(numberOfProducts));
    }

    /**
     * Persist and write to file a list of database entities.
     * @param entities the list of database entities.
     * @throws Exception if the list contains entities that don't represent a database table.
     */
    private void persistAndWriteToFile(List<? extends AbstractEntity> entities) throws Exception {
        Annotation ann = entities.get(0).getClass().getDeclaredAnnotation(Table.class);
        if (ann == null) {
            throw new RuntimeException("Not a table entity");
        }
        String tableName = ((Table) ann).name();
        String fileName = tableName + ".xml";
        em.getTransaction().begin();
        for (AbstractEntity entity : entities) {
            if (!em.contains(entity)) {
                em.persist(entity);
                em.flush();
            }
        }
        em.getTransaction().commit();

        IDataSet databaseDataSet = getConnection().createDataSet();
        FilteredDataSet fds = new FilteredDataSet(new String[]{tableName}, databaseDataSet);
        File file = new File(fileName);
        file.createNewFile();
        FlatXmlDataSet.write(fds, new FileOutputStream(file));
    }

    /**
     * Fetch database data after executing code.
     * Load expected data from an XML dataset.
     * Assert actual database table match expected table.
     * @param fileName - file name where expected dataset is written.
     * @param tableName - the name of the database table where we get the actual dataset.
     * @throws Exception if expected table do not match with database actual table.
     */
    public void assertTables(String fileName, String tableName) throws Exception {
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable(tableName);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(fileName));
        ITable expectedTable = expectedDataSet.getTable(tableName);
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }
}
