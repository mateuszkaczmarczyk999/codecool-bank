package model;


import java.util.Date;

public interface Account {

    Integer getAccountId();
    Customer getCustomer();
    String getNumber();
    AccountType getAccountType();
    AccountStatus getAccountStatus();
    Date getOpenDate();
    Long getBalance();
    Long getDebitLine();
    Integer getInterest();

}
