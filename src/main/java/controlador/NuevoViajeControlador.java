package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import util.Conexion;

public class NuevoViajeControlador {

    // Método para cargar las rutas en el combo box
    public void cargarRutasEnCombo(JComboBox<String> cmbRuta) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT RUTCOD, RUTNOM FROM ruta";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Limpiar el combo antes de cargar nuevos datos
            cmbRuta.removeAllItems();

            // Agregar un elemento por defecto
            cmbRuta.addItem("Seleccione una ruta");

            // Llenar el combo con los datos de la base de datos
            while (resultSet.next()) {
                String codigoRuta = resultSet.getString("RUTCOD");
                String nombreRuta = resultSet.getString("RUTNOM");
                cmbRuta.addItem(codigoRuta + " - " + nombreRuta); // Mostrar código y nombre
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de las rutas.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para cargar los buses en el combo box
    public void cargarBusesEnCombo(JComboBox<String> cmbBus) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT BUSNRO, PLACA FROM bus";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Limpiar el combo antes de cargar nuevos datos
            cmbBus.removeAllItems();

            // Agregar un elemento por defecto
            cmbBus.addItem("Seleccione un bus");

            // Llenar el combo con los datos de la base de datos
            while (resultSet.next()) {
                int numeroBus = resultSet.getInt("BUSNRO");
                String placa = resultSet.getString("PLACA");
                cmbBus.addItem(numeroBus + " - " + placa); // Mostrar número y placa
            }

            // Agregar la opción "Agregar nuevo bus"
            cmbBus.addItem("Agregar nuevo bus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de los buses.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para generar el próximo número de viaje
    public int generarProximoNumeroViaje() {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int proximoNumero = 1;

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT MAX(VIANRO) AS max_numero FROM viaje";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String maxNumero = resultSet.getString("max_numero");
                if (maxNumero != null) {
                    proximoNumero = Integer.parseInt(maxNumero) + 1;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el número de viaje.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return proximoNumero;
    }

    // Método para generar el próximo número de bus
    public int generarProximoNumeroBus() {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int proximoNumero = 1;

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT MAX(BUSNRO) AS max_numero FROM bus";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int maxNumero = resultSet.getInt("max_numero");
                proximoNumero = maxNumero + 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el número de bus.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return proximoNumero;
    }

    // Método para insertar un nuevo viaje
    public void insertarViaje(String rutaCodigo, int busNro, String fecha, String hora, double costo, String chof) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getConnection();
            String sql = "INSERT INTO viaje (VIANRO, BUSNRO, RUTCOD, VIAHRS, VIAFCH, COSVIA, IDCOD) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, generarProximoNumeroViaje());
            statement.setInt(2, busNro);
            statement.setString(3, rutaCodigo);
            statement.setString(4, hora);
            statement.setString(5, fecha);
            statement.setDouble(6, costo);
            statement.setString(7, chof);
            
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Viaje guardado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el viaje.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para insertar un nuevo bus
    public void insertarBus(String placa, int capacidad) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getConnection();
            String sql = "INSERT INTO bus (BUSNRO, PLACA, CAPACIDAD) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, generarProximoNumeroBus());
            statement.setString(2, placa);
            statement.setInt(3, capacidad);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Bus guardado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el bus.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}