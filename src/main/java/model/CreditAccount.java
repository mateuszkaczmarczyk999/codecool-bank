package model;


import java.sql.Date;

public class CreditAccount extends AbstractAccount {
    public CreditAccount(Customer customer, String number, AccountType accountType, AccountStatus accountStatus, Date openDate, Long balance, Long debitLine, Integer interest) {
        super(customer, number, accountType, accountStatus, openDate, balance, debitLine, interest);
    }

    public CreditAccount(Integer accountId, Customer customer, String number, AccountType accountType, AccountStatus accountStatus, Date openDate, Long balance, Long debitLine, Integer interest) {
        super(accountId, customer, number, accountType, accountStatus, openDate, balance, debitLine, interest);
    }
}
