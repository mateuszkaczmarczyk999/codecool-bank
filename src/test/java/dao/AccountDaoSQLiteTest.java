package dao;

import model.*;
import org.junit.jupiter.api.*;

import java.sql.Date;

import util.DatabaseConnection;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDaoSQLiteTest {

    private DatabaseConnection dbConnect = new DatabaseConnection();
    private AccountDaoSQLite accountDaoSQLite;

    @BeforeEach
    void openConnection() throws SQLException {
        this.dbConnect.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        this.dbConnect.initDatabase();
        this.dbConnect.insertData();
        this.accountDaoSQLite = new AccountDaoSQLite(this.dbConnect);
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
        this.accountDaoSQLite.getDbConnect().closeConnection();
    }

    @Test
    void testAddOrUpdateInsertAccountIntoDatabase() throws SQLException {
        Integer testCustomerId = 1;
        Date creationDateTime = new Date(new java.util.Date().getTime());
        Customer newCustomer = new Customer(1, "Jan", "Kowalski", "jkowalski", "xxx", creationDateTime, true, null);
        AccountType accountType = new AccountType(1, "Saving Account", "Description");
        AccountStatus accountStatus = new AccountStatus(1, "Validate", "Description");
        Account newAccount = new SavingAccount(newCustomer, "000999", accountType, accountStatus, creationDateTime, 1000L, 0L, 10);
        this.accountDaoSQLite.addOrUpdate(newAccount);
        Account accountFromDb = this.accountDaoSQLite.find(testCustomerId);
        assertAll("FoundTransactionTypeData",
                () -> assertEquals(accountFromDb.getCustomer().getFirstName(), newCustomer.getFirstName()),
                () -> assertEquals(accountFromDb.getAccountType().getName(), accountType.getName()),
                () -> assertEquals(accountFromDb.getAccountStatus().getName(), accountStatus.getName()),
                () -> assertEquals(accountFromDb.getNumber(), newAccount.getNumber())
        );
    }

    @Test
    void find() {
    }

    @Test
    void getByCustomerId() {
    }

}