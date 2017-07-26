package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class DBManager {
    private static final String USERNAME = "adv_user";
    private static final String PASSWORD = "1234";
    private static final String SERVER_NAME = "localhost";
    private static final String PORT = "3306";
    private static final String SCHEMA = "rentaldb";
    private static DBManager singletonInstance = null;

    public static DBManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DBManager();
            return singletonInstance;
        }
        return singletonInstance;
    }

    public Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection conn = null;
            Properties connectionProps = new Properties();
            connectionProps.put("user", USERNAME);
            connectionProps.put("password", PASSWORD);
            connectionProps.put("useSSL", "false");

            conn = DriverManager.getConnection("jdbc:mysql://" + SERVER_NAME + ":" + PORT + "/" + SCHEMA, connectionProps);

            return conn;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = getConnection();

        return connection.prepareStatement(query).executeQuery();
    }

    public ResultSet executePreparedStatement(String query, Map<Integer, String> params) throws SQLException {
        Connection connection = getConnection();

        final PreparedStatement statement = connection.prepareStatement(query);
        params.forEach((k, v) -> {
            try {
                statement.setString(k, v);
            } catch (final SQLException e){
                throw new RuntimeException(e);
            }
        });

        return statement.executeQuery();
    }
}
