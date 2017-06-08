package dao;


import model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDaoSQLiteTest {
    private CustomerDaoSQLite customerDaoSQLite = new CustomerDaoSQLite();

    @BeforeEach
    void openConnection() throws SQLException {
        this.customerDaoSQLite.getDbConnect().openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        this.customerDaoSQLite.getDbConnect().initDatabase();
        this.customerDaoSQLite.getDbConnect().insertData();
    }
    @AfterEach
    void dropTable() throws SQLException {
        FileReader reader = new FileReader();
        String[] createTables= reader.getStringFromFile("/sql/dropTables.sql").split(";");
        Statement statement = this.customerDaoSQLite.getDbConnect().getConnection().createStatement();
        for (String query: createTables) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
        this.customerDaoSQLite.getDbConnect().closeConnection();
    }

    @Test
    void testFindCustomer() throws SQLException {
        Integer testId = 1;
        Date expectedCreateDate = new Date(1496926140l);
        Date expectedLastLoginDate = new Date(1496926140l);
        Customer expectedCustomer = new Customer(1, "Jan", "Kowalski", "janko", "12345", expectedCreateDate, true, expectedLastLoginDate);
        Customer actualCustomer = this.customerDaoSQLite.find(testId);
        assertAll("testFoundCustomerData",
                () -> assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName()),
                () -> assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName()),
                () -> assertEquals(expectedCustomer.getLogin(), actualCustomer.getLogin()),
                () -> assertEquals(expectedCustomer.getPassword(), actualCustomer.getPassword()),
                () -> assertEquals(expectedCustomer.getCreateDate(), actualCustomer.getCreateDate()),
                () -> assertEquals(expectedCustomer.isActive(), actualCustomer.isActive()),
                () -> assertEquals(expectedCustomer.getLastLogin(), actualCustomer.getLastLogin())
        );
    }

}