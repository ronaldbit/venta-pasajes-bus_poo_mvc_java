package controlador;

import vista.LoginForm;
import vista.AdministradorForm;
import vista.VendedorForm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Conexion;

public class ControladorLogin {

    public static void login(String usuario, String password, JFrame loginFrame) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión a la base de datos
            con = Conexion.getConnection();

            // Consulta SQL para validar el usuario y la contraseña
            String sql = "SELECT USUARIO, CONTRASEÑA, ROL FROM usuario WHERE USUARIO = ? AND CONTRASEÑA = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Verificar si se encontró un usuario con las credenciales proporcionadas
            if (rs.next()) {
                String nombreUsuario = rs.getString("USUARIO");
                String rol = rs.getString("ROL");

                JOptionPane.showMessageDialog(null, "Bienvenido, " + nombreUsuario + "!");
                loginFrame.dispose();

                // Redirección según rol
                if (rol.equals("admin")) {
                    System.out.println("Abrir Panel de Administrador");
                    AdministradorForm adminForm = new AdministradorForm();
                    adminForm.setVisible(true);
                } else if (rol.equals("user")) {
                    System.out.println("Abrir Panel de Ventas");
                    VendedorForm vendedorForm = new VendedorForm();
                    vendedorForm.setVisible(true);
                } else {
                    System.out.println("Acceso restringido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Error al validar el usuario: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}