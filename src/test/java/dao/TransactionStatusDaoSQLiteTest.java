package dao;

import model.TransactionStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class TransactionStatusDaoSQLiteTest {
    private TransactionStatusDaoSQLite transactionStatusDaoSQLite = new TransactionStatusDaoSQLite();

    @BeforeEach
    void openConnection() throws SQLException {
        this.transactionStatusDaoSQLite.getDbConnect().openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        this.transactionStatusDaoSQLite.getDbConnect().initDatabase();
        this.transactionStatusDaoSQLite.getDbConnect().insertData();
    }

    @AfterEach
    void dropTables() throws SQLException {
        FileReader reader = new FileReader();
        String[] createTables = reader.getStringFromFile("/sql/dropTables.sql").split(";");
        Statement statement = this.transactionStatusDaoSQLite.dbConnect.getConnection().createStatement();
        for (String query : createTables) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
        this.transactionStatusDaoSQLite.getDbConnect().closeConnection();
    }

    @Test
    void testFindTransactionStatus() throws SQLException {
        Integer testId = 1;
        TransactionStatus expextedTransactionType = new TransactionStatus(1, "testName", "testDescription");
        TransactionStatus actualTransactiontype = transactionStatusDaoSQLite.find(testId);
        assertAll("FoundTransactionTypeData",
                () -> assertEquals(expextedTransactionType.getStatusId(), actualTransactiontype.getStatusId()),
                () -> assertEquals(expextedTransactionType.getName(), actualTransactiontype.getName()),
                () -> assertEquals(expextedTransactionType.getDescription(), actualTransactiontype.getDescription())
        );
    }

}