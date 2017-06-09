package controller;

import dao.AccountDaoSQLite;
import dao.CustomerDaoSQLite;
import model.Account;
import model.Customer;
import util.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerController {
    private DatabaseConnection dbConnect;
    public CustomerController(DatabaseConnection dbConnect) {
        this.dbConnect = dbConnect;
    }

    public Customer login(String username, String password) throws SQLException {
        CustomerDaoSQLite customerDaoSQLite = new CustomerDaoSQLite(dbConnect);
        return customerDaoSQLite.find(username, password);
    }

    public List<Account> displayAccounts(Customer customer) throws SQLException {
        List<Account> accountList = new ArrayList<>();
        AccountDaoSQLite accountDaoSQLite = new AccountDaoSQLite(dbConnect);
        accountList = accountDaoSQLite.getByCustomerId(customer.getCustomerId());
        return accountList;
    }

    public void addNewCustomer(String firstName, String lastName, String login, String password) throws SQLException {
        CustomerDaoSQLite customerDaoSQLite = new CustomerDaoSQLite(dbConnect);
        Customer newCustomer = new Customer(firstName, lastName, login, password, new Date(), true, null);
        customerDaoSQLite.addOrUpadte(newCustomer);
    }

    public void deactivateCustomer(Customer customer) throws SQLException {
        CustomerDaoSQLite customerDaoSQLite = new CustomerDaoSQLite(dbConnect);
        customer.setActive(false);
        customerDaoSQLite.addOrUpadte(customer);
    }

}
