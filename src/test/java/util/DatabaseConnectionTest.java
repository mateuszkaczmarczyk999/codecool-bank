package util;

import org.junit.jupiter.api.*;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DatabaseConnectionTest {

    private DatabaseConnection dbConnector;

    @Test
    public void testOpenConnectionThrowsSQLException() throws SQLException {
        dbConnector = new DatabaseConnection();
        assertThrows(SQLException.class, () -> dbConnector.openConnection(""));
    }

    @Test
    public void testGetConnectionReturnOpenConnection() throws SQLException {
        dbConnector = new DatabaseConnection();
        dbConnector.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        assertFalse(dbConnector.getConnection().isClosed());
        dbConnector.closeConnection();
    }

    @Test
    public void testGetConnectionBeforeOpenConnectionReturnNull() {
        dbConnector = new DatabaseConnection();
        assertEquals(null, dbConnector.getConnection());
    }

    @Test
    public void testCloseConnectionClosingConnection() throws SQLException {
        dbConnector = new DatabaseConnection();
        dbConnector.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        dbConnector.closeConnection();
        assertTrue(dbConnector.getConnection().isClosed());
    }

    @Nested
    @DisplayName("when connection with database established")
    class databaseTests {

        private DatabaseConnection dbConnector;

        @BeforeEach
        public void openConnection() throws SQLException {
            this.dbConnector = new DatabaseConnection();
            this.dbConnector.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        }

        @AfterEach
        public void dropTables() throws SQLException {

            FileReader reader = new FileReader();
            String[] createTables= reader.getStringFromFile("/sql/dropTables.sql").split(";");
            Statement statement = this.dbConnector.getConnection().createStatement();
            for (String query: createTables) {
                if (!query.trim().equals("")) {
                    statement.executeUpdate(query);
                }
            }
            dbConnector.closeConnection();
        }

        @Test
        public void testInitDatabaseCreateDBFile() throws SQLException {
            this.dbConnector.initDatabase();

            List<String> tablesName = new ArrayList<>();
            DatabaseMetaData metaData = this.dbConnector.getConnection().getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", null);
            while (resultSet.next()) {
                tablesName.add(resultSet.getString(3));
            }
            assertTrue(tablesName.contains("transactions"));
            assertTrue(tablesName.contains("transaction_types"));
            assertTrue(tablesName.contains("transaction_statuses"));
        }
    }

}