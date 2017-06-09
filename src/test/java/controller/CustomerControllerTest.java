package controller;

import model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseConnection;
import util.FileReader;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by cezary on 08.06.2017.
 */
class CustomerControllerTest {
    DatabaseConnection dbConnect = new DatabaseConnection();
    CustomerController customerController;

    @BeforeEach
    void openConnection() throws SQLException {
        dbConnect.openConnection("jdbc:sqlite:src/test/resource/test_database.db");
        dbConnect.initDatabase();
        dbConnect.insertData();
        customerController = new CustomerController(dbConnect);
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
    // works once every 2-3 times (I have no idea waht's going on)
    void testLoginSuccessful() throws SQLException {
        String testLogin = "janko";
        String testPassword = "12345";
        Customer testCustomer = customerController.login(testLogin, testPassword);
        assertEquals(testLogin, testCustomer.getLogin());
    }

    @Test
    void testLoginUnsuccessful() throws SQLException {
        String testFalseLogin = "test";
        String testFalsePassword = "paparapa";
        Customer testCustomer = customerController.login(testFalseLogin, testFalsePassword);
        assertNull(testCustomer);
    }

}