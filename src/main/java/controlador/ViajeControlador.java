package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Viaje;
import util.Conexion;

public class ViajeControlador {
     //use: vendedorForm, 
    public List<Viaje> obtenerTodos() {
        List<Viaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM viaje";
        
        try (
            Connection con = Conexion.getConnection();  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                lista.add(new Viaje(
                        rs.getString("VIANRO"),
                        rs.getInt("BUSNRO"),
                        rs.getString("RUTCOD"),
                        rs.getString("IDCOD"),
                        rs.getTime("VIAHRS"),
                        rs.getDate("VIAFCH"),
                        rs.getDouble("COSVIA")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener viajes: " + e.getMessage());
        }
        return lista;
    }

    //use: vendedorForm, 
    public List<Viaje> obtenerPorRuta(String codRuta) {
        List<Viaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM viaje WHERE RUTCOD = ?";
        
        try (
            Connection con = Conexion.getConnection(); 
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, codRuta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Viaje(
                        rs.getString("VIANRO"),
                        rs.getInt("BUSNRO"),
                        rs.getString("RUTCOD"),
                        rs.getString("IDCOD"),
                        rs.getTime("VIAHRS"),
                        rs.getDate("VIAFCH"),
                        rs.getDouble("COSVIA")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener viajes por ruta: " + e.getMessage());
        }
        return lista;
    }
    //use: vendedorFormVenta
    public Viaje obtenerPorCodigo(String vianro) {
        String sql = "SELECT * FROM viaje WHERE VIANRO = ?";
        Viaje viaje = null;

        try (Connection con = Conexion.getConnection(); 
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, vianro);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                viaje = new Viaje(
                    rs.getString("VIANRO"),
                    rs.getInt("BUSNRO"),
                    rs.getString("RUTCOD"),
                    rs.getString("IDCOD"),
                    rs.getTime("VIAHRS"),
                    rs.getDate("VIAFCH"),
                    rs.getDouble("COSVIA")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener viaje por código: " + e.getMessage());
        }
        return viaje;
    }

}
