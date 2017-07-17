import model.Customer;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DBManager {
    Connection connection;

    public void connect(String userName,
                        String password,
                        String serverName,
                        String portNumber,
                        String schemaName)
            throws SQLException, InstantiationException, IllegalAccessException {
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        connectionProps.put("useSSL", "false");

        conn = DriverManager.getConnection(
                "jdbc:mysql://"
                        + serverName
                        + ":" + portNumber + "/" + schemaName,
                connectionProps);

        System.out.println("Connected to database");
        this.connection = conn;
    }


    //Filing table with Users
//    public List<Customer> getCustomers(){
//        PreparedStatement stmt;
//
//        String query = "SELECT customer_frist_name, customer_last_name, customer_phone, customer_address" +
//                "From customer";
//
//        ResultSet rs;
//
//        try{
//            stmt = connection.prepareStatement(query);
//            stmt.setString()
//        }
//    }


}
