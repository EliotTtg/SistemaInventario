package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProveedorView extends JFrame {

    public JTextField txtNombre;
    public JTextField txtRuc;
    public JTextField txtTelefono;
    public JTextField txtDireccion;

    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnGenerarReporte;
    public JButton btnVolver;

    public JTable tabla;

    public ProveedorView() {

        setTitle("Proveedores");

        setSize(1000, 600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(
                new Color(245,247,250)
        );

        JLabel titulo =
                new JLabel(
                        "GESTION DE PROVEEDORES"
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        titulo.setBounds(
                260,
                20,
                450,
                40
        );

        add(titulo);

        JLabel lblNombre =
                new JLabel("Nombre:");

        lblNombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblNombre.setBounds(
                40,
                90,
                120,
                30
        );

        add(lblNombre);

        txtNombre =
                new JTextField();

        txtNombre.setBounds(
                180,
                90,
                250,
                30
        );

        add(txtNombre);

        JLabel lblRuc =
                new JLabel("RUC:");

        lblRuc.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblRuc.setBounds(
                40,
                140,
                120,
                30
        );

        add(lblRuc);

        txtRuc =
                new JTextField();

        txtRuc.setBounds(
                180,
                140,
                250,
                30
        );

        add(txtRuc);

        JLabel lblTelefono =
                new JLabel("Telefono:");

        lblTelefono.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblTelefono.setBounds(
                40,
                190,
                120,
                30
        );

        add(lblTelefono);

        txtTelefono =
                new JTextField();

        txtTelefono.setBounds(
                180,
                190,
                250,
                30
        );

        add(txtTelefono);

        JLabel lblDireccion =
                new JLabel("Direccion:");

        lblDireccion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblDireccion.setBounds(
                40,
                240,
                120,
                30
        );

        add(lblDireccion);

        txtDireccion =
                new JTextField();

        txtDireccion.setBounds(
                180,
                240,
                350,
                30
        );

        add(txtDireccion);

        btnGuardar =
                crearBoton(
                        "Guardar",
                        new Color(39,174,96)
                );

        btnGuardar.setBounds(
                40,
                320,
                120,
                40
        );

        add(btnGuardar);

        btnActualizar =
                crearBoton(
                        "Actualizar",
                        new Color(41,128,185)
                );

        btnActualizar.setBounds(
                180,
                320,
                140,
                40
        );

        add(btnActualizar);

        btnEliminar =
                crearBoton(
                        "Eliminar",
                        new Color(192,57,43)
                );

        btnEliminar.setBounds(
                340,
                320,
                120,
                40
        );

        add(btnEliminar);

        btnGenerarReporte =
                crearBoton(
                        "Generar Reporte",
                        new Color(155,89,182)
                );

        btnGenerarReporte.setBounds(
                480,
                320,
                180,
                40
        );

        add(btnGenerarReporte);

        btnVolver =
                crearBoton(
                        "Volver",
                        new Color(52,73,94)
                );

        btnVolver.setBounds(
                690,
                320,
                120,
                40
        );

        add(btnVolver);

        tabla =
                new JTable();

        tabla.setModel(

                new DefaultTableModel(

                        new Object[][]{},

                        new String[]{
                                "ID",
                                "Nombre",
                                "RUC",
                                "Telefono",
                                "Direccion"
                        }
                )
        );

        JScrollPane scrollTabla =
                new JScrollPane(tabla);

        scrollTabla.setBounds(
                40,
                400,
                900,
                120
        );

        add(scrollTabla);

        setVisible(true);
    }

    private JButton crearBoton(

            String texto,

            Color color
    ) {

        JButton boton =
                new JButton(texto);

        boton.setBackground(color);

        boton.setForeground(Color.WHITE);

        boton.setFocusPainted(false);

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        return boton;
    }
}