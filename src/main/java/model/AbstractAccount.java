package model;


import java.sql.Date;

public abstract class AbstractAccount implements Account{

    private Integer accountId;
    private Integer customerId;
    private String number;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Date openDate;
    private Long balance;
    private Long debitLine;
    private Integer interest;

    public AbstractAccount(Integer customerId, String number, AccountType accountType, AccountStatus accountStatus, Date openDate, Long balance, Long debitLine, Integer interest) {
        this.customerId = customerId;
        this.number = number;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.openDate = openDate;
        this.balance = balance;
        this.debitLine = debitLine;
        this.interest = interest;
    }

    public AbstractAccount(Integer accountId, Integer customerId, String number, AccountType accountType, AccountStatus accountStatus, Date openDate, Long balance, Long debitLine, Integer interest) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.number = number;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.openDate = openDate;
        this.balance = balance;
        this.debitLine = debitLine;
        this.interest = interest;
    }

    @Override
    public Integer getAccountId() {
        return accountId;
    }

    @Override
    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public Date getOpenDate() {
        return openDate;
    }

    @Override
    public Long getBalance() {
        return balance;
    }

    @Override
    public Long getDebitLine() {
        return debitLine;
    }

    @Override
    public Integer getInterest() {
        return interest;
    }

    @Override
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
