package model;


import java.util.Date;

public interface Account {

    Integer getAccountId();
    Integer getCustomerId();
    String getNumber();
    AccountType getAccountType();
    AccountStatus getAccountStatus();
    Date getOpenDate();
    Long getBalance();
    Long getDebitLine();
    Integer getInterest();

    void setAccountType(AccountType accountTypeFromDb);

    void setAccountStatus(AccountStatus accountStatusFromDb);
}
