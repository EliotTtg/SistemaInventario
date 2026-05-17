package view;

import model.Rol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class UsuarioView extends JFrame {

    public JTextField txtNombre;

    public JTextField txtUsuario;

    public JPasswordField txtContrasena;

    public JComboBox<Rol> cbRol;

    public JButton btnGuardar;

    public JButton btnActualizar;

    public JButton btnEliminar;

    public JButton btnVolver;

    public JTable tabla;

    public UsuarioView() {

        setTitle("Usuarios");

        setSize(950, 600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(
                Color.WHITE
        );

        JLabel titulo =
                new JLabel(
                        "GESTION DE USUARIOS"
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

        txtNombre =
                new JTextField();

        txtNombre.setBounds(
                40,
                100,
                250,
                40
        );

        txtNombre.setBorder(
                BorderFactory.createTitledBorder(
                        "Nombre"
                )
        );

        add(txtNombre);

        txtUsuario =
                new JTextField();

        txtUsuario.setBounds(
                320,
                100,
                250,
                40
        );

        txtUsuario.setBorder(
                BorderFactory.createTitledBorder(
                        "Usuario"
                )
        );

        add(txtUsuario);

        txtContrasena =
                new JPasswordField();

        txtContrasena.setBounds(
                600,
                100,
                250,
                40
        );

        txtContrasena.setBorder(
                BorderFactory.createTitledBorder(
                        "Contraseña"
                )
        );

        add(txtContrasena);

        cbRol =
                new JComboBox<>();

        cbRol.setBounds(
                40,
                170,
                220,
                40
        );

        cbRol.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        add(cbRol);

        btnGuardar =
                crearBoton(
                        "Guardar",
                        new Color(39,174,96)
                );

        btnGuardar.setBounds(
                40,
                250,
                130,
                40
        );

        add(btnGuardar);

        btnActualizar =
                crearBoton(
                        "Actualizar",
                        new Color(41,128,185)
                );

        btnActualizar.setBounds(
                190,
                250,
                150,
                40
        );

        add(btnActualizar);

        btnEliminar =
                crearBoton(
                        "Eliminar",
                        new Color(192,57,43)
                );

        btnEliminar.setBounds(
                360,
                250,
                130,
                40
        );

        add(btnEliminar);

        btnVolver =
                crearBoton(
                        "Volver",
                        new Color(52,73,94)
                );

        btnVolver.setBounds(
                510,
                250,
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
                                "Nombre",
                                "Usuario",
                                "Rol"
                        }
                )
        );

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(
                40,
                330,
                850,
                180
        );

        add(scroll);

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