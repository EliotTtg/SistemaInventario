package view;

import controller.CategoriaController;
import controller.LoginController;
import controller.MovimientoController;
import controller.ProductoController;
import controller.ProveedorController;
import controller.ReporteController;
import controller.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {

        setTitle("Sistema Inventario");

        setSize(1200, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        Color colorFondo =
                new Color(245,247,250);

        Color colorBoton =
                new Color(52,73,94);

        Color colorTexto =
                Color.WHITE;

        getContentPane().setBackground(
                colorFondo
        );

        JPanel panelSuperior =
                new JPanel();

        panelSuperior.setBackground(
                new Color(41,128,185)
        );

        panelSuperior.setPreferredSize(
                new Dimension(100,80)
        );

        JLabel titulo =
                new JLabel(
                        "SISTEMA DE INVENTARIO"
                );

        titulo.setForeground(
                Color.WHITE
        );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        panelSuperior.add(titulo);

        add(
                panelSuperior,
                BorderLayout.NORTH
        );

        JPanel panelCentral =
                new JPanel();

        panelCentral.setBackground(
                colorFondo
        );

        panelCentral.setLayout(
                new GridLayout(
                        3,
                        3,
                        25,
                        25
                )
        );

        panelCentral.setBorder(

                BorderFactory.createEmptyBorder(
                        40,
                        40,
                        40,
                        40
                )
        );

        JButton btnProductos =
                crearBoton(
                        "📦",
                        "PRODUCTOS",
                        colorBoton,
                        colorTexto
                );

        JButton btnCategorias =
                crearBoton(
                        "🗂",
                        "CATEGORIAS",
                        colorBoton,
                        colorTexto
                );

        JButton btnProveedores =
                crearBoton(
                        "🚚",
                        "PROVEEDORES",
                        colorBoton,
                        colorTexto
                );

        JButton btnMovimientos =
                crearBoton(
                        "🔄",
                        "MOVIMIENTOS",
                        colorBoton,
                        colorTexto
                );

        JButton btnReportes =
                crearBoton(
                        "📊",
                        "REPORTES",
                        colorBoton,
                        colorTexto
                );

        JButton btnUsuarios =
                crearBoton(
                        "👤",
                        "USUARIOS",
                        colorBoton,
                        colorTexto
                );

        JButton btnCerrarSesion =
                crearBoton(
                        "🔒",
                        "CERRAR SESION",
                        new Color(192,57,43),
                        colorTexto
                );

        btnProductos.addActionListener(e -> {

            ProductoView view =
                    new ProductoView();

            new ProductoController(view);

            dispose();
        });

        btnCategorias.addActionListener(e -> {

            CategoriaView view =
                    new CategoriaView();

            new CategoriaController(view);

            dispose();
        });

        btnProveedores.addActionListener(e -> {

            ProveedorView view =
                    new ProveedorView();

            new ProveedorController(view);

            dispose();
        });

        btnMovimientos.addActionListener(e -> {

            MovimientoView view =
                    new MovimientoView();

            new MovimientoController(view);

            dispose();
        });

        btnReportes.addActionListener(e -> {

            ReporteView view =
                    new ReporteView();

            new ReporteController(view);

            dispose();
        });

        btnUsuarios.addActionListener(e -> {

            UsuarioView view =
                    new UsuarioView();

            new UsuarioController(view);

            dispose();
        });

        btnCerrarSesion.addActionListener(e -> {

            int opcion =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Desea cerrar sesion?"
                    );

            if(opcion == 0){

                LoginView login =
                        new LoginView();

                new LoginController(login);

                dispose();
            }
        });

        panelCentral.add(btnProductos);

        panelCentral.add(btnCategorias);

        panelCentral.add(btnProveedores);

        panelCentral.add(btnMovimientos);

        panelCentral.add(btnReportes);

        panelCentral.add(btnUsuarios);

        panelCentral.add(btnCerrarSesion);

        add(
                panelCentral,
                BorderLayout.CENTER
        );

        setVisible(true);
    }

    private JButton crearBoton(

            String icono,

            String texto,

            Color fondo,

            Color textoColor
    ) {

        JButton boton =
                new JButton(

                        "<html><center>"
                                + "<div style='font-size:40px'>"
                                + icono
                                + "</div><br>"
                                + texto
                                + "</center></html>"
                );

        boton.setBackground(fondo);

        boton.setForeground(textoColor);

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        boton.setFocusPainted(false);

        boton.setBorderPainted(false);

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return boton;
    }
}