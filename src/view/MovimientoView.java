package view;

import model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MovimientoView extends JFrame {

    public JComboBox<String> cbTipoMovimiento;

    public JComboBox<Producto> cbProducto;

    public JTextField txtCantidad;

    public JTextArea txtMotivo;

    public JButton btnGuardar;
    public JButton btnEliminar;
    public JButton btnGenerarReporte;
    public JButton btnVolver;

    public JTable tabla;

    public MovimientoView() {

        setTitle("Movimientos Inventario");

        setSize(1000, 650);

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
                        "GESTION DE MOVIMIENTOS"
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        titulo.setBounds(
                280,
                20,
                400,
                40
        );

        add(titulo);

        JLabel lblTipo =
                new JLabel(
                        "Tipo Movimiento:"
                );

        lblTipo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblTipo.setBounds(
                40,
                90,
                150,
                30
        );

        add(lblTipo);

        cbTipoMovimiento =
                new JComboBox<>();

        cbTipoMovimiento.addItem(
                "ENTRADA"
        );

        cbTipoMovimiento.addItem(
                "SALIDA"
        );

        cbTipoMovimiento.setBounds(
                200,
                90,
                180,
                30
        );

        add(cbTipoMovimiento);

        JLabel lblCantidad =
                new JLabel(
                        "Cantidad:"
                );

        lblCantidad.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblCantidad.setBounds(
                40,
                140,
                120,
                30
        );

        add(lblCantidad);

        txtCantidad =
                new JTextField();

        txtCantidad.setBounds(
                200,
                140,
                180,
                30
        );

        add(txtCantidad);

        JLabel lblProducto =
                new JLabel(
                        "Producto:"
                );

        lblProducto.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblProducto.setBounds(
                40,
                190,
                120,
                30
        );

        add(lblProducto);

        cbProducto =
                new JComboBox<>();

        cbProducto.setBounds(
                200,
                190,
                250,
                30
        );

        add(cbProducto);

        JLabel lblMotivo =
                new JLabel(
                        "Motivo:"
                );

        lblMotivo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblMotivo.setBounds(
                40,
                240,
                120,
                30
        );

        add(lblMotivo);

        txtMotivo =
                new JTextArea();

        txtMotivo.setLineWrap(true);

        JScrollPane scrollMotivo =
                new JScrollPane(
                        txtMotivo
                );

        scrollMotivo.setBounds(
                200,
                240,
                300,
                80
        );

        add(scrollMotivo);

        btnGuardar =
                crearBoton(
                        "Guardar",
                        new Color(39,174,96)
                );

        btnGuardar.setBounds(
                40,
                350,
                130,
                40
        );

        add(btnGuardar);

        btnEliminar =
                crearBoton(
                        "Eliminar",
                        new Color(192,57,43)
                );

        btnEliminar.setBounds(
                190,
                350,
                130,
                40
        );

        add(btnEliminar);

        btnGenerarReporte =
                crearBoton(
                        "Generar Reporte",
                        new Color(155,89,182)
                );

        btnGenerarReporte.setBounds(
                340,
                350,
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
                540,
                350,
                130,
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
                                "Tipo",
                                "Cantidad",
                                "Producto",
                                "Motivo",
                                "Fecha"
                        }
                )
        );

        JScrollPane scrollTabla =
                new JScrollPane(tabla);

        scrollTabla.setBounds(
                40,
                430,
                900,
                150
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