package dao;


import model.Account;
import util.DatabaseConnection;

import java.sql.SQLException;

public interface AccountDao {

    Account find(Integer accountId) throws SQLException;
    void addOrUpdate(Account account) throws SQLException;
    DatabaseConnection getDbConnect();
}
