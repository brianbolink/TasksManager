package db;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 王一航 on 2017/3/27.
 */
public class DBUtil {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/tasksmanager";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static void getConnetcion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while(resultSet.next()){
            System.out.println("Username : " + resultSet.getString("username"));
            System.out.println("Password : " + resultSet.getString("password"));
        }
    }
}
