package util;

import java.sql.*;

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
}
