package dao;


import model.TransactionType;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTypeDaoSQLite {
    DatabaseConnection dbConnect = new DatabaseConnection();

    public TransactionType find(Integer id) throws SQLException {
        String query = "SELECT * FROM transaction_types WHERE id = ?;";
        PreparedStatement preparedStatement = this.dbConnect.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        return resultSetToTransactionType(preparedStatement.executeQuery());
    }

    private TransactionType resultSetToTransactionType (ResultSet resultSet) throws SQLException {
        Integer id = null;
        String name = null;
        String description = null;
        while (resultSet.next()) {
            id = resultSet.getInt("transactiontypeID");
            name = resultSet.getString("name");
            description = resultSet.getString("description");
        }
        return new TransactionType(id, name, description);
    }

    public DatabaseConnection getDbConnect() {
        return dbConnect;
    }
}
