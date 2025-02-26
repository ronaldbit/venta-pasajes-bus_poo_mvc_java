package vista;
 
import controlador.BusControlador;
import controlador.PasajeroControlador;
import controlador.ViajeControlador;

import java.awt.BorderLayout;
import java.awt.*; 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Pasajero;
import modelo.Viaje;
/**
 *
 * @author RONALD
 */
public class VendedorFormVenta extends javax.swing.JFrame {

    private String idViaje;
    private BusControlador busDAO = new BusControlador();
    
    private PasajeroControlador pasajeroDAO = new PasajeroControlador();
    private DefaultTableModel tableBoletos;
    
    public VendedorFormVenta(String idViaje) {
        this.idViaje = idViaje;
        initComponents(); 
        setTitle("PERÚBUSS - Agregar pasajero");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          
        cargarDatos(idViaje); 
        
        
        // Crear el ButtonGroup
        ButtonGroup grupoPasajeros = new ButtonGroup();
        // Agregar los RadioButtons al grupo
        grupoPasajeros.add(rbBoy);
        grupoPasajeros.add(rbAdulto);
        grupoPasajeros.add(rbEstudiante);
        rbBoy.addActionListener(e -> actualizarCostoPasaje());
        rbAdulto.addActionListener(e -> actualizarCostoPasaje());
        rbEstudiante.addActionListener(e -> actualizarCostoPasaje());
        inicializarCostoPasaje();
    }
    private double costoBase2;

    private void cargarDatos(String vianro) {
        ViajeControlador viajeControlador = new ViajeControlador();
        Viaje viaje = viajeControlador.obtenerPorCodigo(vianro);

        if (viaje != null) {
            lblVIANRO.setText("Lista de pasajeros del viaje: " + viaje.getVIANRO());
            lblCOSVIA.setText("Costo de Pasaje: " + viaje.getCOSVIA());
            lblBUSNRO.setText("Bus: " + viaje.getBUSNRO());

            DecimalFormat df = new DecimalFormat("#.00");
            costoBase = viaje.getCOSVIA(); // Inicializar correctamente
            costoBase2 = viaje.getCOSVIA();
            txtCostoPasaje.setText(df.format(costoBase));

            tableBoletos = (DefaultTableModel) tablaBoletos.getModel();
            cargarPasajeros(viaje.getBUSNRO());
            
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró información para el código: " + vianro, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

     
    List<Integer> listaAsientos = new ArrayList<>(); 
    private void cargarPasajeros(int bsn) { 
        tableBoletos.setRowCount(0);
        listaAsientos.clear(); // Evitar duplicados al recargar

        List<Pasajero> pasajeros = pasajeroDAO.obtenerPasajerosPorViaje(idViaje);

        for (Pasajero pasajero : pasajeros) {
            tableBoletos.addRow(new Object[]{ 
                pasajero.getBOLNRO(),
                pasajero.getNOMPASA(),  
                pasajero.getNROASIE(),
                pasajero.getPAGO()
            });
            listaAsientos.add((int) pasajero.getNROASIE()); 
        }
        cargarAsientos(bsn);
        System.out.println("Asientos ocupados: " + listaAsientos); // Verifica los asientos ocupados
    }

    private void cargarAsientos(int codigoBus) {
        if (codigoBus == 0) {
            System.out.println("No se encontró el bus para esta ruta.");
            return;
        }

        int capacidad = busDAO.obtenerCapacidadBus(codigoBus);
        lblASIENTOS.setText("Asientos: " + capacidad);

        panelAsientos.removeAll();
        panelAsientos.setLayout(new GridLayout((int) Math.ceil(capacidad / 4.0), 4, 10, 10));

        // Convertir la lista a Set para mejorar la búsqueda
        Set<Integer> asientosOcupados = new HashSet<>(listaAsientos);

        panelAsientos.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes

        for (int fila = 0; fila < capacidad / 4; fila++) {
            int asientoIzq1 = fila * 4 + 1;
            int asientoIzq2 = fila * 4 + 2;
            int asientoDer1 = fila * 4 + 3;
            int asientoDer2 = fila * 4 + 4;

            // Agregar asientos izquierdos
            gbc.gridx = 0;
            agregarBotonAsiento(panelAsientos, asientoIzq1, asientosOcupados, gbc);
            gbc.gridx = 1;
            agregarBotonAsiento(panelAsientos, asientoIzq2, asientosOcupados, gbc);

            // Pasillo más delgado
            gbc.gridx = 2;
            JPanel pasillo = new JPanel();
            pasillo.setPreferredSize(new Dimension(5, 20)); // Ajustar tamaño del pasillo
            panelAsientos.add(pasillo, gbc);

            // Agregar asientos derechos
            gbc.gridx = 3;
            agregarBotonAsiento(panelAsientos, asientoDer1, asientosOcupados, gbc);
            gbc.gridx = 4;
            agregarBotonAsiento(panelAsientos, asientoDer2, asientosOcupados, gbc);
        } 
        panelAsientos.revalidate();
        panelAsientos.repaint();
    }

    private void agregarBotonAsiento(JPanel panel, int numeroAsiento, Set<Integer> asientosOcupados, GridBagConstraints gbc) {
        JButton boton = new JButton("A" + numeroAsiento);
        boton.setFont(new Font("Arial", Font.PLAIN, 10));
        boton.setPreferredSize(new Dimension(60, 30));

        if (asientosOcupados.contains(numeroAsiento)) {
            boton.setEnabled(false);
            boton.setBackground(Color.RED);
        } else {
            boton.setBackground(new Color(255, 255, 51));
            boton.addActionListener(evt -> registrarVenta(numeroAsiento));
        }

        panel.add(boton, gbc); // Agregar el botón con las restricciones de GridBagConstraints
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelAsientos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBoletos = new javax.swing.JTable();
        lblVIANRO = new javax.swing.JLabel();
        lblCOSVIA = new javax.swing.JLabel();
        lblASIENTOS = new javax.swing.JLabel();
        lblBUSNRO = new javax.swing.JLabel();
        btnRegistrarPasajero = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNroAsiento = new javax.swing.JTextField();
        tfNombrePasajero = new javax.swing.JTextField();
        txtCostoPasaje = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rbBoy = new javax.swing.JRadioButton();
        rbAdulto = new javax.swing.JRadioButton();
        rbEstudiante = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelAsientosLayout = new javax.swing.GroupLayout(panelAsientos);
        panelAsientos.setLayout(panelAsientosLayout);
        panelAsientosLayout.setHorizontalGroup(
            panelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        panelAsientosLayout.setVerticalGroup(
            panelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        tablaBoletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Boleto", "Pasajero", "Asiento", "Pago"
            }
        ));
        jScrollPane1.setViewportView(tablaBoletos);

        lblVIANRO.setText("Lista de pasajeros del  viaje: ");

        lblCOSVIA.setText("Costo de Pasaje: ");

        lblASIENTOS.setText("Asientos: ");

        lblBUSNRO.setText("Bus: ");

        btnRegistrarPasajero.setText("Registrar");
        btnRegistrarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPasajeroActionPerformed(evt);
            }
        });

        jLabel12.setText("Nombre del pasajero");

        jLabel13.setText("Asiento");

        txtNroAsiento.setEditable(false);

        txtCostoPasaje.setEditable(false);

        jLabel1.setText("Costo");

        rbBoy.setText("Niño");

        rbAdulto.setSelected(true);
        rbAdulto.setText("Adulto");

        rbEstudiante.setText("Estudiante");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbBoy, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbAdulto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbEstudiante)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbBoy)
                .addComponent(rbAdulto)
                .addComponent(rbEstudiante))
        );

        jLabel2.setText("Tipo de pasajero");

        btnClose.setText("Salir");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCOSVIA, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnRegistrarPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfNombrePasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCostoPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNroAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBUSNRO, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)
                                .addComponent(lblASIENTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblVIANRO, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(lblVIANRO)
                .addGap(7, 7, 7)
                .addComponent(lblCOSVIA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBUSNRO)
                    .addComponent(lblASIENTOS))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tfNombrePasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtNroAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCostoPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrarPasajero)
                            .addComponent(btnClose)))
                    .addComponent(panelAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private double costoBase;  
    private void inicializarCostoPasaje() { try {  costoBase = Double.parseDouble(txtCostoPasaje.getText()); } catch (NumberFormatException e) { costoBase = costoBase2;} }

    private void actualizarCostoPasaje() {
        double costoFinal = costoBase;   
        if (rbBoy.isSelected()) {
            costoFinal *= 0.7; // Descuento del 30% para niños
        } else if (rbEstudiante.isSelected()) {
            costoFinal *= 0.5; // Descuento del 50% para estudiantes
        }  
        txtCostoPasaje.setText(String.format("%.2f", costoFinal)); // Mostrar con 2 decimales
    }


    private void btnRegistrarPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPasajeroActionPerformed
        String nombrePasajero = tfNombrePasajero.getText().trim();
        String tipoPasajero = "";
        int nroAsiento;
        double costoPasaje;

        // Validar que el nombre del pasajero no esté vacío
        if (nombrePasajero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que se haya seleccionado un tipo de pasajero
        if (rbBoy.isSelected()) {
            tipoPasajero = "N";
        } else if (rbAdulto.isSelected()) {
            tipoPasajero = "A"; 
        } else if (rbEstudiante.isSelected()) {
            tipoPasajero = "E";
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar número de asiento
        try {
            nroAsiento = Integer.parseInt(txtNroAsiento.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Seleccione un número de asiento válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar costo del pasaje
        try {
            costoPasaje = Double.parseDouble(txtCostoPasaje.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un costo de pasaje válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean exito = pasajeroDAO.registrarPasajero(idViaje, nombrePasajero, nroAsiento, tipoPasajero,costoPasaje);

        if (exito) {  
            tfNombrePasajero.setText("");
            txtNroAsiento.setText("");
            txtCostoPasaje.setText(String.format("%.2f", costoBase));
            rbAdulto.setSelected(true); 
            cargarDatos(idViaje);
            JOptionPane.showMessageDialog(this, "Se registró correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarPasajeroActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendedorFormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendedorFormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendedorFormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendedorFormVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendedorFormVenta("100001").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnRegistrarPasajero;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblASIENTOS;
    private javax.swing.JLabel lblBUSNRO;
    private javax.swing.JLabel lblCOSVIA;
    private javax.swing.JLabel lblVIANRO;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JPanel panelAsientos;
    private javax.swing.JRadioButton rbAdulto;
    private javax.swing.JRadioButton rbBoy;
    private javax.swing.JRadioButton rbEstudiante;
    private javax.swing.JTable tablaBoletos;
    private javax.swing.JTextField tfNombrePasajero;
    private javax.swing.JTextField txtCostoPasaje;
    private javax.swing.JTextField txtNroAsiento;
    // End of variables declaration//GEN-END:variables

    private void registrarVenta(int asientoSeleccionado) {
        //JOptionPane.showMessageDialog(this, "asiento: "+asientoSeleccionado);
        txtNroAsiento.setText(String.valueOf(asientoSeleccionado));
    }
}
