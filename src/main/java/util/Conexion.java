package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion { 
    
    private static final String URL = "jdbc:mysql://193.203.166.156:3306/u266581275_utp_poo?autoReconnect=true&useSSL=false";
    private static final String USER = "u266581275_utp_poo";
    private static final String PASSWORD = "w/P4wkpb";
   
    // Método estático para obtener la conexión
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
    
}
