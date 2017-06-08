package dao;


import model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseConnection;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerDaoSQLiteTest {

    private DatabaseConnection dbConnect = new DatabaseConnection();
    private CustomerDaoSQLite customerDaoSQLite;

    @BeforeEach
    void openConnection() throws SQLException {
        this.dbConnect.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        this.dbConnect.initDatabase();
        this.dbConnect.insertData();
        this.customerDaoSQLite = new CustomerDaoSQLite(this.dbConnect);
    }
    @AfterEach
    void dropTable() throws SQLException {
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
    void testFindCustomerById() throws SQLException {
        Integer testId = 1;
        Date expectedCreateDate = new Date(1496926140916L);
        Date expectedLastLoginDate = new Date(1496926140916L);
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

    @Test
    void testInsertNewCustomer() throws SQLException {
        Date createDate = new Date(1496926140916L);
        Date lastastLoginDate = null;
        Customer testCustomer = new Customer("Maciej", "Nowak", "nomad", "123456", createDate, true, lastastLoginDate);
        Integer expectedRowsCountInsert = 1;
        assertEquals(expectedRowsCountInsert, customerDaoSQLite.addOrUpadte(testCustomer));
    }

    @Test
    void testUpdateCustomerData() throws SQLException {
        Integer expectedRowsCountUpdate = 1;
        Integer testId = 1;
        Customer actualCustomer = customerDaoSQLite.find(testId);
        actualCustomer.setActive(false);
        assertEquals(expectedRowsCountUpdate, customerDaoSQLite.addOrUpadte(actualCustomer));
    }
}