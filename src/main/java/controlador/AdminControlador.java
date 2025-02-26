package controlador;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Conexion;

public class AdminControlador {

    private List<Object[]> datosViajesOriginales; // Lista para almacenar los datos originales

    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    private String fechaActual = formatoFecha.format(new Date());

    // Método para obtener la fecha actual
    public String getFechaActual() {
        return fechaActual;
    }
    public String generarNuevoCodigo() {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nuevoCodigo = "C001";

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT IDCOD FROM chofer ORDER BY IDCOD DESC LIMIT 1";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String ultimoCodigo = resultSet.getString("IDCOD");
                if (ultimoCodigo != null && !ultimoCodigo.isEmpty()) {
                    int numero = Integer.parseInt(ultimoCodigo.substring(1));
                    nuevoCodigo = "C" + String.format("%03d", numero + 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el código del chofer.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return nuevoCodigo;
    }

    public void guardarChofer(String codigo, String nombre, String fechaRegistro, String categoria, double salario, String rutaImagen) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = Conexion.getConnection();
            String sql = "INSERT INTO chofer (IDCOD, CHONOM, CHOFIN, CHOCAT, CHOSBA) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.setString(2, nombre);
            statement.setString(3, fechaRegistro);
            statement.setString(4, categoria);
            statement.setDouble(5, salario);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Chofer guardado correctamente.");

                // Guardar la imagen con el codigo
                if (rutaImagen != null) {
                    File archivoOrigen = new File(rutaImagen);
                    String rutaImagenes = obtenerRutaImagenes(); // Obtener la ruta de la carpeta "imagenes"
                    File archivoDestino = new File(rutaImagenes + "/" + codigo + ".jpg");

                    // Copiar la imagen a la carpeta "imagenes"
                    try {
                        java.nio.file.Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    } catch (java.io.IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al guardar la imagen.");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el chofer.", "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void cargarDatosChoferes(DefaultTableModel model) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT IDCOD, CHONOM, CHOFIN, CHOCAT, CHOSBA FROM chofer";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Limpiar la tabla antes de cargar nuevos datos
            model.setRowCount(0);

            // Llenar la tabla con los datos de la base de datos
            while (resultSet.next()) {
                String codigo = resultSet.getString("IDCOD");
                String nombre = resultSet.getString("CHONOM");
                Date fechaIngreso = resultSet.getDate("CHOFIN");
                String categoria = resultSet.getString("CHOCAT");
                double salario = resultSet.getDouble("CHOSBA");

                // Agregar una fila al modelo de la tabla
                model.addRow(new Object[]{codigo, nombre, fechaIngreso, categoria, salario});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de la tabla chofer.");
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

    public String obtenerRutaImagenes() {
        String rutaProyecto = System.getProperty("user.dir");
        String rutaImagenes = rutaProyecto + "/src/main/java/img/fotos";
        File carpetaImagenes = new File(rutaImagenes);
        if (!carpetaImagenes.exists()) {
            carpetaImagenes.mkdirs();
        }
        return rutaImagenes;
    }

    public void cargarDatosViajes(DefaultTableModel model) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        datosViajesOriginales = new ArrayList<>(); // Inicializar la lista

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT VIANRO, BUSNRO, RUTCOD, IDCOD, VIAHRS, VIAFCH, COSVIA FROM viaje";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Limpiar la tabla antes de cargar nuevos datos
            model.setRowCount(0);

            // Llenar la tabla con los datos de la base de datos
            while (resultSet.next()) {
                String codigoViaje = resultSet.getString("VIANRO");
                int numeroBus = resultSet.getInt("BUSNRO");
                String codigoRuta = resultSet.getString("RUTCOD");
                String codigoChofer = resultSet.getString("IDCOD");
                Time horaSalida = resultSet.getTime("VIAHRS");
                Date fechaSalida = resultSet.getDate("VIAFCH");
                double costo = resultSet.getDouble("COSVIA");

                // Crear un array con los datos de la fila
                Object[] fila = {codigoViaje, numeroBus, codigoRuta, codigoChofer, horaSalida, fechaSalida, costo};

                // Agregar la fila a la tabla y a la lista de datos originales
                model.addRow(fila);
                datosViajesOriginales.add(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de la tabla viaje.");
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

    public void cargarChoferesEnCombo(JComboBox<String> cmbChofer) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.getConnection();
            String sql = "SELECT IDCOD, CHONOM FROM chofer";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Limpiar el combo antes de cargar nuevos datos
            cmbChofer.removeAllItems();

            // Agregar un elemento por defecto
            cmbChofer.addItem("Seleccione un chofer");

            // Llenar el combo con los datos de la base de datos
            while (resultSet.next()) {
                String codigo = resultSet.getString("IDCOD");
                String nombre = resultSet.getString("CHONOM");
                cmbChofer.addItem(nombre + " - " + codigo); // Mostrar nombre y código
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de los choferes.", "Error", JOptionPane.ERROR_MESSAGE);
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
    // Método para filtrar los datos desde la lista original
    public void filtrarViajesPorChofer(DefaultTableModel model, String codigoChofer) {
        // Limpiar la tabla antes de cargar nuevos datos
        model.setRowCount(0);

        // Si no se selecciona un chofer, mostrar todos los datos
        if (codigoChofer == null || codigoChofer.isEmpty() || codigoChofer.equals("Seleccione un chofer")) {
            for (Object[] fila : datosViajesOriginales) {
                model.addRow(fila);
            }
        } else {
            // Filtrar los datos según el código del chofer
            for (Object[] fila : datosViajesOriginales) {
                if (fila[3].equals(codigoChofer)) { // El índice 3 corresponde a IDCOD
                    model.addRow(fila);
                }
            }
        }
    }
    
}