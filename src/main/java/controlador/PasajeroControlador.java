package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Pasajero;
import util.Conexion;

public class PasajeroControlador {
    
    public List<Pasajero> obtenerPasajerosPorViaje(String VIANRO) {
        List<Pasajero> lista = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros WHERE VIANRO = ?";

        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) { // Usamos PreparedStatement
            
            pstmt.setString(1, VIANRO); // Asignamos el parámetro
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta
            
            while (rs.next()) {
                lista.add(new Pasajero(
                        rs.getString("BOLNRO"), 
                        rs.getString("VIANRO"), 
                        rs.getString("NOMPASA"), 
                        rs.getInt("NROASIE"), 
                        rs.getString("TIPO"), 
                        rs.getDouble("PAGO")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pasajeros: " + e.getMessage());
        }
        return lista;
    }
    
    
    public List<Pasajero> obtenerPasajerosDeBoletos() {
        List<Pasajero> lista = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros"; 

        try (Connection con = new Conexion().getConnection();  
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {  // Cierre automático del ResultSet

            while (rs.next()) {
                lista.add(new Pasajero(
                        rs.getString("BOLNRO"), 
                        rs.getString("VIANRO"), 
                        rs.getString("NOMPASA"), 
                        rs.getInt("NROASIE"), 
                        rs.getString("TIPO"), 
                        rs.getDouble("PAGO")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pasajeros: " + e.getMessage());
            e.printStackTrace();  // Muestra más detalles sobre el error
        }
        return lista;
    }
    
    public List<Pasajero> obtenerPasajerosPorBoleto(String VIANRO) {
        List<Pasajero> lista = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros WHERE BOLNRO = ?";

        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) { // Usamos PreparedStatement
            
            pstmt.setString(1, VIANRO); // Asignamos el parámetro
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta
            
            while (rs.next()) {
                lista.add(new Pasajero(
                        rs.getString("BOLNRO"), 
                        rs.getString("VIANRO"), 
                        rs.getString("NOMPASA"), 
                        rs.getInt("NROASIE"), 
                        rs.getString("TIPO"), 
                        rs.getDouble("PAGO")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pasajeros: " + e.getMessage());
        }
        return lista;
    }
    
    private String generarNuevoBOLNRO() {
        String ultimoBoleto = "000000"; // Valor por defecto si no hay registros
        String sql = "SELECT MAX(BOLNRO) FROM pasajeros"; 

        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next() && rs.getString(1) != null) {
                ultimoBoleto = rs.getString(1); // Obtiene el último número de boleto
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el último BOLNRO: " + e.getMessage());
        }

        // Extraer número, incrementar y formatear
        int nuevoNumero = Integer.parseInt(ultimoBoleto) + 1;
        return String.format("%06d", nuevoNumero); // Mantiene el formato de 6 dígitos
    }

    public boolean registrarPasajero(String idViaje, String nombrePasajero, int nroAsiento, String tipoPasajero, double costoPasaje) { 
        String nuevoBoleto = generarNuevoBOLNRO(); // Genera el nuevo número de boleto
        String sql = "INSERT INTO pasajeros (BOLNRO, VIANRO, NOMPASA, NROASIE, TIPO, PAGO) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nuevoBoleto);
            pstmt.setString(2, idViaje);
            pstmt.setString(3, nombrePasajero);
            pstmt.setInt(4, nroAsiento);
            pstmt.setString(5, tipoPasajero);
            pstmt.setDouble(6, costoPasaje);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0; // Retorna `true` si se insertó correctamente
        } catch (SQLException e) {
            System.out.println("Error al registrar pasajero: " + e.getMessage());
            return false; // Retorna `false` en caso de error
        }
    }

}
