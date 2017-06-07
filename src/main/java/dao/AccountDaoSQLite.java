package dao;

import model.Account;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoSQLite implements AccountDao {
    DatabaseConnection dbConnect = new DatabaseConnection();

    @Override
    public Account find(Integer accountId) throws SQLException {
        String query = "SELECT * FROM accounts WHERE accountID = ?;";
        PreparedStatement preparedStatement = this.dbConnect.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, accountId);

    }

    @Override
    public void addOrUpdate(Account account) {

    }

    private Account resultSetToAccount(ResultSet resultSet) throws SQLException {
        // tu zaczynają się schody, ponieważ nie wiem na jakiej pddstawie mam określić jaki typ klasy będzie tworzony
    }
}
