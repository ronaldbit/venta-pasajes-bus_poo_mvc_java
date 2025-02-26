package controlador;

import modelo.Bus;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusControlador {
    
    public int obtenerCapacidadBus(int idBus) {
        int capacidad = 0;
        String sql = "SELECT CAPACIDAD FROM bus WHERE BUSNRO = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBus);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                capacidad = rs.getInt("CAPACIDAD");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener capacidad del bus: " + e.getMessage());
        }

        return capacidad;
    }
}
