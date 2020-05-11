package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

    public Connection createConnection() throws SQLException{

        String dbname = "lms";
        String dbuser = "root";
        String dbpass = "!@#$asad1234";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ dbname, dbuser, dbpass);

        return connection;

    }

}
