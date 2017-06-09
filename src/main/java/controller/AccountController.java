package controller;


import dao.AccountDao;
import dao.AccountDaoSQLite;
import dao.AccountStatusDao;
import dao.AccountStatusDaoSQLite;
import model.*;
import util.DatabaseConnection;

import java.sql.Date;
import java.sql.SQLException;

public class AccountController {

    private DatabaseConnection dbConnector;
    private AccountStatusDao accountStatusDao;
    private AccountDao accountDao;

    public AccountController(DatabaseConnection dbConnector) {
        this.dbConnector = dbConnector;
        this.accountStatusDao = new AccountStatusDaoSQLite(this.dbConnector);
        this.accountDao = new AccountDaoSQLite(this.dbConnector);
    }

    public void addNewAccount(Integer customerId, AccountType accountType) throws SQLException {
        String number ="0000";
        AccountStatus accountStatus = accountStatusDao.find(1);
        Date creationDateTime = new Date(new java.util.Date().getTime());
        Long balance = 0L;
        Long debitLine = 1000L;

        Account newAccount = new CreditAccount(customerId, number, accountType, accountStatus, creationDateTime, balance, debitLine, 0);
        this.accountDao.addOrUpdate(newAccount);
        System.out.println("Credit Account added");
    }

    public void addNewAccount(Integer customerId, AccountType accountType, Integer interest) throws SQLException {
        String number ="9999";
        AccountStatus accountStatus = accountStatusDao.find(1);
        Date creationDateTime = new Date(new java.util.Date().getTime());
        Long balance = 0L;
        Long debitLine = 0L;

        Account newAccount = new SavingAccount(customerId, number, accountType, accountStatus, creationDateTime, balance, debitLine, interest);
        this.accountDao.addOrUpdate(newAccount);
        System.out.println("Saving Account added");
    }

    public void blockAnAccount(Account accountToBlock) throws SQLException {
        AccountStatus blockedStatus = new AccountStatus(2,"Invalid", "Account is blocked");
        accountToBlock.setAccountStatus(blockedStatus);
        this.accountDao.addOrUpdate(accountToBlock);
        System.out.println("Account blocked");
    }

    public void unBlockAnAccount(Account accountToUnBlock) throws SQLException {
        AccountStatus unBlockedStatus = new AccountStatus(1,"Valid", "Account is valid");
        accountToUnBlock.setAccountStatus(unBlockedStatus);
        this.accountDao.addOrUpdate(accountToUnBlock);
        System.out.println("Account unblocked");
    }
}
