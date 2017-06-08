package dao;


import model.TransactionStatus;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionStatusDaoSQLite implements TransactionStatusDao{
    DatabaseConnection dbConnect = new DatabaseConnection();

    public TransactionStatus find(Integer id) throws SQLException {
        String query = "SELECT * FROM transaction_statuses WHERE transactionstatusID = ?;";
        PreparedStatement preparedStatement = this.dbConnect.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        return resultSetToTransactionStatus(preparedStatement.executeQuery());
    }

    private TransactionStatus resultSetToTransactionStatus (ResultSet resultSet) throws SQLException {
        Integer id = null;
        String name = null;
        String description = null;
        while (resultSet.next()) {
            id = resultSet.getInt("transactionstatusID");
            name = resultSet.getString("name");
            description = resultSet.getString("description");
        }
        return new TransactionStatus(id, name, description);
    }

    public DatabaseConnection getDbConnect() {
        return dbConnect;
    }
}
