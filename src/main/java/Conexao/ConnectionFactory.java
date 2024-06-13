package Conexao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/sistemadereserva";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static java.sql.Connection conn;

    public static java.sql.Connection getConnection() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}