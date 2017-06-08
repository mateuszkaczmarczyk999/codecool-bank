package dao;

import model.*;
import util.DatabaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoSQLite implements AccountDao {

    DatabaseConnection dbConnect = new DatabaseConnection();
    AccountTypeDao accountTypeDao = new AccountTypeDaoSQLite();
    AccountStatusDao accountStatusDao = new AccountStatusDaoSQLite();
    CustomerDao customerDao = new CustomerDaoSQLite();

    @Override
    public void addOrUpdate(Account account) throws SQLException {
        String addQuery = "INSERT INTO accounts (customerID, number, accounttypeID, accountstatusID, opendate, balance, debitline, interest) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE accounts SET accounttypeID = ?, accountstatusID = ?, balance = ?, debitline = ?, interest = ? WHERE accountID = ?";

        Integer accountId = account.getAccountId();
        PreparedStatement preparedStatement;

        if (accountId == null) {
            preparedStatement = this.dbConnect.getConnection().prepareStatement(addQuery);
            preparedStatement.setInt(1, account.getCustomer().getCustomerId());
            preparedStatement.setString(2, account.getNumber());
            preparedStatement.setInt(3, account.getAccountType().getTypeId());
            preparedStatement.setInt(4, account.getAccountStatus().getStatusId());
            preparedStatement.setDate(5, (Date) account.getOpenDate());
            preparedStatement.setLong(6, account.getBalance());
            preparedStatement.setLong(7, account.getDebitLine());
            preparedStatement.setInt(8, account.getInterest());
        } else {
            preparedStatement = this.dbConnect.getConnection().prepareStatement(updateQuery);
            preparedStatement.setInt(1, account.getAccountType().getTypeId());
            preparedStatement.setInt(2, account.getAccountStatus().getStatusId());
            preparedStatement.setLong(3, account.getBalance());
            preparedStatement.setLong(4, account.getDebitLine());
            preparedStatement.setInt(5, account.getInterest());
            preparedStatement.setInt(6, account.getAccountId());
        }
        preparedStatement.executeQuery();
    }

    @Override
    public Account find(Integer accountId) throws SQLException {
        String query = "SELECT * FROM accounts WHERE accountID = ?;";
        PreparedStatement preparedStatement = this.dbConnect.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, accountId);
        return resultSetToAccount(preparedStatement.executeQuery());
    }

    private Account resultSetToAccount(ResultSet resultSet) throws SQLException {
        Account account = null;
        Integer accountId = null;
        Integer customerId = null;
        String number = null;
        Integer accountTypeId = null;
        Integer accountStatusId = null;
        Date openDate = null;
        Long balance = null;
        Long debitLine = null;
        Integer interest = null;
        while (resultSet.next()) {
            accountId = resultSet.getInt("accountID");
            customerId = resultSet.getInt("customerID");
            number = resultSet.getString("number");
            accountTypeId = resultSet.getInt("accounttypeID");
            accountStatusId = resultSet.getInt("accountstatusID");
            openDate = resultSet.getDate("opendate");
            balance = resultSet.getLong("balance");
            debitLine = resultSet.getLong("debitline");
            interest = resultSet.getInt("interest");
        }
        AccountType accountType = accountTypeDao.find(accountTypeId);
        AccountStatus accountStatus = accountStatusDao.find(accountStatusId);
        Customer customer = customerDao.find(customerId);
        if (accountType.getName().equals("Credit Account")) {
            account = new CreditAccount(accountId, customer, number, accountType, accountStatus, openDate, balance, debitLine, interest);
        }
        if (accountType.getName().equals("Saving Account")) {
            account = new SavingAccount(accountId, customer, number, accountType, accountStatus, openDate, balance, debitLine, interest);
        }
        return account;
    }

    public List<Account> getByCustomerId(Integer customerId) throws SQLException {
        String query = "SELECT * FROM accounts WHERE customerID = ?;";
        return getBy(query, customerId);
    }

    private List<Account> getBy(String query, Integer customerId) throws SQLException {
        List<Account> listOfAccount = new ArrayList<>();
        PreparedStatement preparedStatement = this.dbConnect.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, customerId);
        ResultSet results = preparedStatement.executeQuery();
        while (results.next()) {
            listOfAccount.add(resultSetToAccount(results));
        }
        return listOfAccount;
    }
}
