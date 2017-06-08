package dao;


import model.AccountStatus;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStatusDaoSQLite implements AccountStatusDao{
    DatabaseConnection dbConnect = new DatabaseConnection();

    @Override
    public AccountStatus find(Integer id) throws SQLException {
        String query = "SELECT * FROM account_status WHERE accountstatusID = ?;";
        PreparedStatement preparedStatement = this.dbConnect.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        return resultSetToAccountStatus(preparedStatement.executeQuery());
    }

    private AccountStatus resultSetToAccountStatus (ResultSet resultSet) throws SQLException {
        Integer id = null;
        String name = null;
        String description = null;
        while (resultSet.next()) {
            id = resultSet.getInt("accountstatusID");
            name = resultSet.getString("name");
            description = resultSet.getString("description");
        }
        return new AccountStatus(id, name, description);
    }

    @Override
    public DatabaseConnection getDbConnect() {
        return dbConnect;
    }
}
