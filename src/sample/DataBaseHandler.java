package sample;
import java.sql.*;

public class DataBaseHandler {

    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/coursework", "root", "Madachan2702");
        System.out.println("Connection success");
        return connection;


    }
}
