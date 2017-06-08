package dao;


import model.AccountStatus;
import util.DatabaseConnection;

import java.sql.SQLException;

public interface AccountStatusDao {

    AccountStatus find(Integer id) throws SQLException;
    DatabaseConnection getDbConnect();

}
