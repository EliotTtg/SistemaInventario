package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReporteView extends JFrame {

    public JComboBox<String> cbTipoReporte;
    public JButton btnGenerar;
    public JButton btnEliminar;
    public JButton btnVolver;
    public JTable tabla;

    public ReporteView() {

        setTitle("Reportes");

        setSize(950, 600);

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
                        "GESTION DE REPORTES"
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
                        "Tipo Reporte:"
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
                100,
                150,
                30
        );

        add(lblTipo);

        cbTipoReporte =
                new JComboBox<>();

        cbTipoReporte.addItem(
                "REPORTE_PRODUCTOS"
        );

        cbTipoReporte.addItem(
                "REPORTE_MOVIMIENTOS"
        );

        cbTipoReporte.addItem(
                "REPORTE_PROVEEDORES"
        );

        cbTipoReporte.addItem(
                "REPORTE_CATEGORIAS"
        );

        cbTipoReporte.setBounds(
                200,
                100,
                250,
                30
        );

        add(cbTipoReporte);


        btnGenerar =
                crearBoton(
                        "Generar Reporte",
                        new Color(39,174,96)
                );

        btnGenerar.setBounds(
                40,
                180,
                180,
                45
        );

        add(btnGenerar);

        btnEliminar =
                crearBoton(
                        "Eliminar Historial",
                        new Color(192,57,43)
                );

        btnEliminar.setBounds(
                250,
                180,
                200,
                45
        );

        add(btnEliminar);

        btnVolver =
                crearBoton(
                        "Volver",
                        new Color(52,73,94)
                );

        btnVolver.setBounds(
                480,
                180,
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
                                "Tipo Reporte",
                                "Fecha Generacion",
                                "Usuario"
                        }
                )
        );

        JScrollPane scrollTabla =
                new JScrollPane(tabla);

        scrollTabla.setBounds(
                40,
                280,
                840,
                220
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