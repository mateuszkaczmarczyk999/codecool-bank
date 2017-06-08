package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private FileReader reader = new FileReader();
    private Connection connection;

    public void openConnection(String pathToDatabase) throws SQLException {
        connection = DriverManager.getConnection(pathToDatabase);
        System.out.println("Connection established...");
    }

    public void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Connection terminated...");
    }

    public Connection getConnection() {
        return connection;
    }

    public void initDatabase() throws SQLException {
        String[] createTables= reader.getStringFromFile("/sql/createTables.sql").split(";");
        Statement statement = connection.createStatement();
        System.out.println("Creating Tables...");
        for (String query: createTables)
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        System.out.println("Done!");
    }

    public void insertData() throws SQLException {
        String[] insertData = reader.getStringFromFile("/sql/insertData.sql").split(";");
        Statement statement = connection.createStatement();
        System.out.println("Inserting data...");
        for (String query : insertData) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
        System.out.println("Data insert finished...");
    }

    public void dropTables() throws SQLException {
        String[] dropTables = reader.getStringFromFile("/sql/dropTables.sql").split(";");
        Statement statement = connection.createStatement();
        for (String query : dropTables) {
            if (!query.trim().equals("")) {
                statement.executeUpdate(query);
            }
        }
        System.out.println("Tables dropped");
    }
}
