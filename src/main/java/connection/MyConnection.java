package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

public final class MyConnection {

    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "05042000";

    private final static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/quan_ly_san_pham";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION_URL, DB_USER, DB_PASSWORD);
    }
}
