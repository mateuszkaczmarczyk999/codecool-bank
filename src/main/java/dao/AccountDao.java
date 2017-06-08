package dao;


import model.Account;

import java.sql.SQLException;

public interface AccountDao {
    Account find(Integer accountId) throws SQLException;
    void addOrUpdate(Account account);
}
