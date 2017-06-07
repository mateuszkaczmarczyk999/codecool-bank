package dao;


import model.AccountType;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTypeDaoSQLite implements AccountTypeDao {

    DatabaseConnection dbConnect = new DatabaseConnection();

    public AccountType find(Integer id) throws SQLException {
        String query = "SELECT * FROM account_type WHERE accounttypeID = ?;";
        PreparedStatement preparedStatement = this.dbConnect.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        return resultSetToAccountType(preparedStatement.executeQuery());
    }

    private AccountType resultSetToAccountType (ResultSet resultSet) throws SQLException {
        Integer id = null;
        String name = null;
        String description = null;
        while (resultSet.next()) {
            id = resultSet.getInt("accounttypeID");
            name = resultSet.getString("name");
            description = resultSet.getString("description");
        }
        return new AccountType(id, name, description);
    }

    public DatabaseConnection getDbConnect() {
        return dbConnect;
    }

}
