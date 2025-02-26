package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion { 
    
    /* conectarse a una base de datos de un servidor. Cambiar ip a la de tu servidor.
    private static final String URL = "jdbc:mysql://193.203.166.156:3306/nombreDeLaBaseDeDatos?autoReconnect=true&useSSL=false";
    private static final String USER = "nombreDeLaBaseDeDatos";
    private static final String PASSWORD = "contraseñaDeLaBaseDeDatos"; */

    // Conexion localmente.
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos?autoReconnect=true&useSSL=false";
    private static final String USER = "root"; // Usuario por defecto en WampServer
    private static final String PASSWORD = ""; // La contraseña suele estar vacía por defecto en WampServer
   
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
