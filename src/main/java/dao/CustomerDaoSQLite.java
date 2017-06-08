package dao;


import model.Account;
import model.Customer;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDaoSQLite implements CustomerDao {

    private DatabaseConnection dbCommenct;

    public CustomerDaoSQLite(DatabaseConnection dbCommenct) {
        this.dbCommenct = dbCommenct;
    }

    @Override
    public Customer find(Integer customerId) throws SQLException {
        String query = "SELECT * FROM customers WHERE customerID = ?;";
        PreparedStatement preparedStatement = this.dbCommenct.getConnection().prepareStatement(query);
        return resultSetToCustomer(preparedStatement.executeQuery());
    }

    public void add(Customer customer) throws SQLException {
        String query = "INSERT INTO customers (firstname, lastname, login, password, createdate, isactive, lastlogin) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = this.dbCommenct.getConnection().prepareStatement(query);
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getLogin());
        preparedStatement.setString(4, customer.getPassword());
        preparedStatement.setDate(5, (java.sql.Date) customer.getCreateDate());
        preparedStatement.setBoolean(6, customer.isActive());
        preparedStatement.setDate(7, (java.sql.Date) customer.getLasLogin());
        preparedStatement.executeUpdate(query);
    }

    private Customer resultSetToCustomer(ResultSet resultSet) throws SQLException {
        Integer customerId = null;
        String firstName = null;
        String lastName = null;
        String login = null;
        String password = null;
        Date createDate = null;
        Boolean isActive = null;
        Date lasLogin = null;
        List<Account> accounts = new ArrayList<>();

        while (resultSet.next()) {
            customerId = resultSet.getInt("customerID");
            firstName = resultSet.getString("firstname");
            lastName = resultSet.getString("lastname");
            login = resultSet.getString("login");
            password = resultSet.getString("password");
            createDate = resultSet.getDate("createdate");
            isActive = resultSet.getBoolean("isactive");
            lasLogin = resultSet.getDate("lastlogin");
            accounts = new AccountDaoSQLite(this.dbCommenct).getByCustomerId(customerId);
        }
        return new Customer(firstName, lastName, login, password, createDate, isActive, lasLogin, accounts);
    }
}
