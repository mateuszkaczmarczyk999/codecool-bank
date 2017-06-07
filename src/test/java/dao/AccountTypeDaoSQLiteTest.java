package dao;


import model.AccountType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseConnection;
import util.FileReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AccountTypeDaoSQLiteTest {

    private AccountTypeDaoSQLite accountTypeDaoSQLite = new AccountTypeDaoSQLite();

    @BeforeEach
    public void openConnection() throws SQLException {
        this.accountTypeDaoSQLite.getDbConnect().openConnection ("jdbc:sqlite:src/test/resource/test_database.db");
        this.accountTypeDaoSQLite.getDbConnect().initDatabase();
    }

    @AfterEach
    public void dropTables() throws SQLException {

        FileReader reader = new FileReader();
        String[] createTables= reader.getStringFromFile("/sql/dropTables.sql").split(";");
        Statement statement = this.accountTypeDaoSQLite.getDbConnect().getConnection().createStatement();
        for (String query: createTables) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
//        this.accountTypeDaoSQLite.getDbConnect().closeConnection();
    }

    @Test
    void testFindAccountType() {
        AccountType testAccountType = new AccountType(1, "testName", "TestDescription");
        Integer testId = 1;
        assertAll(
                () -> assertEquals(testAccountType.getTypeId(), this.accountTypeDaoSQLite.find(testId).getTypeId()),
                () -> assertEquals(testAccountType.getName(), this.accountTypeDaoSQLite.find(testId).getName()),
                () -> assertEquals(testAccountType.getDescription(), this.accountTypeDaoSQLite.find(testId).getDescription())
        );
    }

}