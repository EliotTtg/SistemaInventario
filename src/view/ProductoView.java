package view;

import model.Categoria;
import model.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductoView extends JFrame {

    public JTextField txtCodigo;
    public JTextField txtNombre;
    public JTextArea txtDescripcion;
    public JTextField txtStock;
    public JTextField txtStockMinimo;
    public JTextField txtPrecio;

    public JComboBox<Categoria> cbCategoria;
    public JComboBox<Proveedor> cbProveedor;

    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnGenerarReporte;
    public JButton btnVolver;

    public JTable tabla;

    public ProductoView() {

        setTitle("Productos");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(245,247,250));

        JLabel titulo = new JLabel("GESTION DE PRODUCTOS");

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setBounds(350, 20, 400, 40);

        add(titulo);

        agregarLabel("Codigo:", 40, 90);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 90, 200, 30);

        add(txtCodigo);

        agregarLabel("Nombre:", 40, 140);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 140, 250, 30);

        add(txtNombre);

        agregarLabel("Descripcion:", 40, 190);

        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);

        JScrollPane scrollDescripcion =
                new JScrollPane(txtDescripcion);

        scrollDescripcion.setBounds(
                180,
                190,
                300,
                80
        );

        add(scrollDescripcion);

        agregarLabel("Stock:", 40, 300);

        txtStock = new JTextField();
        txtStock.setBounds(180, 300, 120, 30);

        add(txtStock);

        agregarLabel("Stock Minimo:", 40, 350);

        txtStockMinimo = new JTextField();

        txtStockMinimo.setBounds(
                180,
                350,
                120,
                30
        );

        add(txtStockMinimo);

        agregarLabel("Precio:", 40, 400);

        txtPrecio = new JTextField();

        txtPrecio.setBounds(
                180,
                400,
                120,
                30
        );

        add(txtPrecio);

        agregarLabel("Categoria:", 550, 90);

        cbCategoria = new JComboBox<>();

        cbCategoria.setBounds(
                700,
                90,
                220,
                30
        );

        add(cbCategoria);

        agregarLabel("Proveedor:", 550, 140);

        cbProveedor = new JComboBox<>();

        cbProveedor.setBounds(
                700,
                140,
                220,
                30
        );

        add(cbProveedor);

        btnGuardar =
                crearBoton(
                        "Guardar",
                        new Color(39,174,96)
                );

        btnGuardar.setBounds(
                550,
                220,
                140,
                45
        );

        add(btnGuardar);

        btnActualizar =
                crearBoton(
                        "Actualizar",
                        new Color(41,128,185)
                );

        btnActualizar.setBounds(
                710,
                220,
                140,
                45
        );

        add(btnActualizar);

        btnEliminar =
                crearBoton(
                        "Eliminar",
                        new Color(192,57,43)
                );

        btnEliminar.setBounds(
                870,
                220,
                140,
                45
        );

        add(btnEliminar);

        btnGenerarReporte =
                crearBoton(
                        "Generar Reporte",
                        new Color(155,89,182)
                );

        btnGenerarReporte.setBounds(
                550,
                290,
                180,
                45
        );

        add(btnGenerarReporte);

        btnVolver =
                crearBoton(
                        "Volver",
                        new Color(52,73,94)
                );

        btnVolver.setBounds(
                750,
                290,
                140,
                45
        );

        add(btnVolver);

        tabla = new JTable();

        tabla.setModel(
                new DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                                "ID",
                                "Codigo",
                                "Nombre",
                                "Stock",
                                "Precio"
                        }
                )
        );

        JScrollPane scrollTabla =
                new JScrollPane(tabla);

        scrollTabla.setBounds(
                40,
                470,
                980,
                120
        );

        add(scrollTabla);

        setVisible(true);
    }

    private void agregarLabel(
            String texto,
            int x,
            int y
    ) {

        JLabel label =
                new JLabel(texto);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        label.setBounds(
                x,
                y,
                140,
                30
        );

        add(label);
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