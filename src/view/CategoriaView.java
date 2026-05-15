package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoriaView extends JFrame {

    public JTextField txtNombre;

    public JTextArea txtDescripcion;

    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnGenerarReporte;
    public JButton btnVolver;

    public JTable tabla;

    public CategoriaView() {

        setTitle("Categorias");

        setSize(900, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        getContentPane().setBackground(
                new Color(245,247,250)
        );

        JLabel titulo =
                new JLabel(
                        "GESTION DE CATEGORIAS"
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        titulo.setBounds(
                250,
                20,
                400,
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
                100,
                30
        );

        add(lblNombre);

        txtNombre =
                new JTextField();

        txtNombre.setBounds(
                150,
                90,
                250,
                30
        );

        add(txtNombre);

        JLabel lblDescripcion =
                new JLabel(
                        "Descripcion:"
                );

        lblDescripcion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblDescripcion.setBounds(
                40,
                140,
                120,
                30
        );

        add(lblDescripcion);

        txtDescripcion =
                new JTextArea();

        txtDescripcion.setLineWrap(true);

        JScrollPane scrollDescripcion =
                new JScrollPane(
                        txtDescripcion
                );

        scrollDescripcion.setBounds(
                150,
                140,
                300,
                80
        );

        add(scrollDescripcion);

        btnGuardar =
                crearBoton(
                        "Guardar",
                        new Color(39,174,96)
                );

        btnGuardar.setBounds(
                40,
                250,
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
                250,
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
                250,
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
                250,
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
                680,
                250,
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
                                "Descripcion"
                        }
                )
        );

        JScrollPane scrollTabla =
                new JScrollPane(tabla);

        scrollTabla.setBounds(
                40,
                330,
                780,
                140
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