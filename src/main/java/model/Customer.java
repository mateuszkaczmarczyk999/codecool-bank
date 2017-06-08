package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mateuszkaczmarczyk on 07/06/2017.
 */
public class Customer {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Date createDate;
    private boolean isActive;
    private Date lasLogin;
    List<Account> accounts;

    public Customer(Integer customerId, String firstName, String lastName, String login, String password, Date createDate, boolean isActive, Date lasLogin) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isActive = isActive;
        this.lasLogin = lasLogin;
        this.accounts = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String login, String password, Date createDate, boolean isActive, Date lasLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isActive = isActive;
        this.lasLogin = lasLogin;
        this.accounts = new ArrayList<>();
    }

    public Customer(Integer customerId ,String firstName, String lastName, String login, String password, Date createDate, boolean isActive, Date lasLogin, List<Account> accounts) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isActive = isActive;
        this.lasLogin = lasLogin;
        this.accounts = accounts;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getLastLogin() {
        return lasLogin;
    }

    public void setLastLogin(Date lasLogin) {
        this.lasLogin = lasLogin;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
