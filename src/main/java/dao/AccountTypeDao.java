package dao;


import model.AccountType;
import util.DatabaseConnection;

import java.sql.SQLException;

public interface AccountTypeDao {

    AccountType find(Integer id) throws SQLException;
    DatabaseConnection getDbConnect();
}
