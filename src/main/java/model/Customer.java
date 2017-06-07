package model;

import java.util.Date;

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

    public Customer(String firstName, String lastName, String login, String password, Date createDate, boolean isActive, Date lasLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isActive = isActive;
        this.lasLogin = lasLogin;
    }

}
