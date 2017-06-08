package dao;


import model.AccountType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AccountTypeDaoSQLiteTest {

    private AccountTypeDaoSQLite accountTypeDaoSQLite = new AccountTypeDaoSQLite();

    @BeforeEach
    void openConnection() throws SQLException {
        this.accountTypeDaoSQLite.getDbConnect().openConnection ("jdbc:sqlite:src/test/resource/test_database.db");
        this.accountTypeDaoSQLite.getDbConnect().initDatabase();
        this.accountTypeDaoSQLite.getDbConnect().insertData();
    }

    @AfterEach
    void dropTables() throws SQLException {

        FileReader reader = new FileReader();
        String[] createTables= reader.getStringFromFile("/sql/dropTables.sql").split(";");
        Statement statement = this.accountTypeDaoSQLite.getDbConnect().getConnection().createStatement();
        for (String query: createTables) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
        this.accountTypeDaoSQLite.getDbConnect().closeConnection();
    }

    @Test
    void testFindAccountType() throws SQLException {
        Integer testId = 1;
        AccountType expectedAccountType = new AccountType(1, "testName", "testDescription");
        AccountType actualAccountType = accountTypeDaoSQLite.find(testId);
        assertAll("FoundAccountTypeData",
                () -> assertEquals(expectedAccountType.getTypeId(), actualAccountType.getTypeId()),
                () -> assertEquals(expectedAccountType.getName(), actualAccountType.getName()),
                () -> assertEquals(expectedAccountType.getDescription(), actualAccountType.getDescription())
        );
    }

}