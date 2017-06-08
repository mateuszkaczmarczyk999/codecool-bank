package dao;

import model.AccountStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseConnection;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AccountStatusDaoSQLiteTest {
    private DatabaseConnection dbConnect = new DatabaseConnection();
    private AccountStatusDaoSQLite accountStatusDaoSQLite;

    @BeforeEach
    void openConnection() throws SQLException {
        this.dbConnect.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        this.dbConnect.initDatabase();
        this.dbConnect.insertData();
        this.accountStatusDaoSQLite = new AccountStatusDaoSQLite(this.dbConnect);
    }

    @AfterEach
    void dropTables() throws SQLException {
        FileReader reader = new FileReader();
        String[] createTables = reader.getStringFromFile("/sql/dropTables.sql").split(";");
        Statement statement = this.dbConnect.getConnection().createStatement();
        for (String query : createTables) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
        this.dbConnect.closeConnection();
    }

    @Test
    void testFindAccountStatus() throws SQLException {
        Integer testId = 1;
        AccountStatus expextedTransactionType = new AccountStatus(1, "testName", "testDescription");
        AccountStatus actualTransactiontype = accountStatusDaoSQLite.find(testId);
        assertAll("FoundTransactionTypeData",
                () -> assertEquals(expextedTransactionType.getStatusId(), actualTransactiontype.getStatusId()),
                () -> assertEquals(expextedTransactionType.getName(), actualTransactiontype.getName()),
                () -> assertEquals(expextedTransactionType.getDescription(), actualTransactiontype.getDescription())
        );
    }

}