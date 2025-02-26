package controlador;
 
import modelo.Ruta;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList; 
import java.util.List; 

public class RutaControlador {
    //use: vendedorForm,  
    public List<Ruta> obtenerRutas() {
        List<Ruta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ruta";

        try (Connection con = new Conexion().getConnection(); 
             Statement stmt = con.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Ruta(
                        rs.getString("RUTCOD"), 
                        rs.getString("RUTNOM"),
                        rs.getInt("RUTCOS")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener rutas: " + e.getMessage());
        }
        return lista;
    }
    //use: vendedorForm, 
    public String obtenerCodigoPorNombre(String nombre) {
        String sql = "SELECT RUTCOD FROM ruta WHERE RUTNOM = ?";
        String codRuta = null;

        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                codRuta = rs.getString("RUTCOD");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener código de ruta: " + e.getMessage());
        }
        return codRuta;
    }

}

