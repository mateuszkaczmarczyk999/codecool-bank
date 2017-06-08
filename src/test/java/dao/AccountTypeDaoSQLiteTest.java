package dao;


import model.AccountType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseConnection;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AccountTypeDaoSQLiteTest {

    private DatabaseConnection dbConnect = new DatabaseConnection();
    private AccountTypeDaoSQLite accountTypeDaoSQLite;

    @BeforeEach
    void openConnection() throws SQLException {
        this.dbConnect.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        this.dbConnect.initDatabase();
        this.dbConnect.insertData();
        this.accountTypeDaoSQLite = new AccountTypeDaoSQLite(this.dbConnect);
    }

    @AfterEach
    void dropTables() throws SQLException {

        FileReader reader = new FileReader();
        String[] createTables= reader.getStringFromFile("/sql/dropTables.sql").split(";");
        Statement statement = this.dbConnect.getConnection().createStatement();
        for (String query: createTables) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
        this.dbConnect.closeConnection();
    }

    @Test
    void testFindAccountType() throws SQLException {
        Integer testId = 1;
        AccountType expectedAccountType = new AccountType(1, "Saving Account", "Saving Description");
        AccountType actualAccountType = accountTypeDaoSQLite.find(testId);
        assertAll("FoundAccountTypeData",
                () -> assertEquals(expectedAccountType.getTypeId(), actualAccountType.getTypeId()),
                () -> assertEquals(expectedAccountType.getName(), actualAccountType.getName()),
                () -> assertEquals(expectedAccountType.getDescription(), actualAccountType.getDescription())
        );
    }

}