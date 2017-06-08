package dao;

import model.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import util.DatabaseConnection;
import util.FileReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoSQLiteTest {

    private DatabaseConnection dbConnect = new DatabaseConnection();
    private AccountDaoSQLite accountDaoSQLite;
    private AccountTypeDaoSQLite accountTypeDaoSQLite;
    private AccountStatusDaoSQLite accountStatusDaoSQLite;

    @BeforeEach
    void openConnection() throws SQLException {
        dbConnect.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        dbConnect.initDatabase();
        dbConnect.insertData();
        this.accountDaoSQLite = new AccountDaoSQLite(dbConnect);
        this.accountTypeDaoSQLite = new AccountTypeDaoSQLite(dbConnect);
        this.accountStatusDaoSQLite = new AccountStatusDaoSQLite(dbConnect);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        dbConnect.closeConnection();
    }

    @Test
    void testAddOrUpdateInsertAccountIntoDatabase() throws SQLException {
        Integer testAccountId = 2;
        Date creationDateTime = new Date(new java.util.Date().getTime());
        AccountType accountType = new AccountType(1, "Saving Account", null);
        AccountStatus accountStatus = new AccountStatus(1, "Valid", null);
        Account newAccount = new SavingAccount(1, "00 9999 8888", accountType, accountStatus, creationDateTime, 1000L, 0L, 10);
        this.accountDaoSQLite.addOrUpdate(newAccount);
        Account accountFromDb = this.accountDaoSQLite.find(testAccountId);
        assertAll("-",
                () -> assertEquals(accountFromDb.getAccountType().getName(), accountType.getName()),
                () -> assertEquals(accountFromDb.getAccountStatus().getName(), accountStatus.getName()),
                () -> assertEquals(accountFromDb.getNumber(), newAccount.getNumber())
        );
    }

    @Test
    void testAddOrUpdateUpdateAccountInDatabase() throws SQLException {
        Integer testAccountTypeId = 2;
        AccountType accountTypeFromDb = this.accountTypeDaoSQLite.find(testAccountTypeId);
        Integer testAccountStatusId = 2;
        AccountStatus accountStatusFromDb = this.accountStatusDaoSQLite.find(testAccountStatusId);
        Integer testAccountId = 1;
        Account accountFromDb = this.accountDaoSQLite.find(testAccountId);
        accountFromDb.setAccountType(accountTypeFromDb);
        accountFromDb.setAccountStatus(accountStatusFromDb);
        this.accountDaoSQLite.addOrUpdate(accountFromDb);
        Account accountFromDbAfterUpdate = this.accountDaoSQLite.find(testAccountId);
        assertAll("-",
                () -> assertEquals(accountTypeFromDb.getName(), accountFromDbAfterUpdate.getAccountType().getName()),
                () -> assertEquals(accountStatusFromDb.getName(), accountFromDbAfterUpdate.getAccountStatus().getName())
        );
    }

    @Test
    void testgetByCustomerIdReturnListOfCustomerAccounts() throws SQLException {
        Integer testCustomerId = 1;
        Integer testAccountId = 1;
        Account accountFromDb = this.accountDaoSQLite.find(testAccountId);
        List<Account> customerAccounts = this.accountDaoSQLite.getByCustomerId(testCustomerId);
        for (Account customerAccount : customerAccounts) {
            assertAll("-",
                    () -> assertEquals(accountFromDb.getNumber(), customerAccount.getNumber()),
                    () -> assertEquals(testCustomerId, customerAccount.getCustomerId())
            );
        }
    }

    @Test
    void testGetDbConnect() throws SQLException {
        List<String> tablesName = new ArrayList<>();
        this.accountDaoSQLite.getDbConnect().dropTables();
        DatabaseMetaData metaData = this.accountDaoSQLite.getDbConnect().getConnection().getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, "%", null);
        while (resultSet.next()) {
            tablesName.add(resultSet.getString(3));
        }
        assertAll("-",
                () -> assertFalse(tablesName.contains("accounts")),
                () -> assertFalse(tablesName.contains("customers"))
        );
    }

}