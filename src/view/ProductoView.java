package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductoView extends JFrame {

    public JTextField txtCodigo;
    public JTextField txtNombre;
    public JTextField txtPrecio;

    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnEliminar;

    public JTable tabla;

    public ProductoView() {

        setTitle("Productos");

        setSize(800, 500);

        setLocationRelativeTo(null);

        setLayout(null);

        JLabel lblCodigo =
                new JLabel("Codigo:");

        lblCodigo.setBounds(
                20,
                20,
                100,
                25
        );

        add(lblCodigo);

        txtCodigo = new JTextField();

        txtCodigo.setBounds(
                120,
                20,
                150,
                25
        );

        add(txtCodigo);


        JLabel lblNombre =
                new JLabel("Nombre:");

        lblNombre.setBounds(
                20,
                60,
                100,
                25
        );

        add(lblNombre);

        txtNombre = new JTextField();

        txtNombre.setBounds(
                120,
                60,
                200,
                25
        );

        add(txtNombre);

        JLabel lblPrecio =
                new JLabel("Precio:");

        lblPrecio.setBounds(
                20,
                100,
                100,
                25
        );

        add(lblPrecio);

        txtPrecio = new JTextField();

        txtPrecio.setBounds(
                120,
                100,
                120,
                25
        );

        add(txtPrecio);


        btnGuardar =
                new JButton("Guardar");

        btnGuardar.setBounds(
                20,
                150,
                100,
                30
        );

        add(btnGuardar);

        btnActualizar =
                new JButton("Actualizar");

        btnActualizar.setBounds(
                140,
                150,
                120,
                30
        );

        add(btnActualizar);

        btnEliminar =
                new JButton("Eliminar");

        btnEliminar.setBounds(
                280,
                150,
                100,
                30
        );

        add(btnEliminar);


        tabla = new JTable();

        tabla.setModel(

                new DefaultTableModel(

                        new Object[][]{},

                        new String[]{
                                "ID",
                                "Codigo",
                                "Nombre",
                                "Precio"
                        }
                )
        );

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(
                20,
                220,
                740,
                200
        );

        add(scroll);

        setVisible(true);
    }
}