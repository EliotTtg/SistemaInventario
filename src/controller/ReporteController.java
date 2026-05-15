package controller;

import dao.ReporteDAO;

import model.Reporte;

import session.Session;

import view.DashboardView;
import view.ReporteView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReporteController {

    private ReporteView view;

    private ReporteDAO dao;

    private int idReporteSeleccionado = -1;

    public ReporteController(
            ReporteView view
    ) {

        this.view = view;

        this.dao = new ReporteDAO();

        listarReportes();

        this.view.btnGenerar
                .addActionListener(
                        e -> generarReporte()
                );

        this.view.btnEliminar
                .addActionListener(
                        e -> eliminar()
                );

        this.view.btnVolver
                .addActionListener(
                        e -> volver()
                );

        this.view.tabla.getSelectionModel()
                .addListSelectionListener(
                        e -> seleccionarFila()
                );
    }

    public void generarReporte() {

        try {

            Reporte reporte =
                    new Reporte();

            reporte.setTipoReporte(
                    view.cbTipoReporte
                            .getSelectedItem()
                            .toString()
            );

            reporte.setUsuario(
                    Session.usuarioActual
            );

            boolean resultado =
                    dao.guardar(reporte);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Reporte generado correctamente"
                );

                listarReportes();

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al generar reporte"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    view,
                    e.getMessage()
            );
        }
    }

    public void listarReportes() {

        DefaultTableModel model =

                (DefaultTableModel)
                        view.tabla.getModel();

        model.setRowCount(0);

        List<Reporte> lista =
                dao.listar();

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss"
                );

        for(Reporte r : lista){

            model.addRow(

                    new Object[]{

                            r.getIdReporte(),

                            r.getTipoReporte(),

                            r.getFechaGeneracion()
                                    .format(formato),

                            r.getUsuario()
                                    .getNombre()
                    }
            );
        }
    }

    public void seleccionarFila() {

        int fila =
                view.tabla.getSelectedRow();

        if(fila != -1){

            idReporteSeleccionado =

                    Integer.parseInt(

                            view.tabla.getValueAt(
                                    fila,
                                    0
                            ).toString()
                    );
        }
    }

    public void eliminar() {

        if(idReporteSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un reporte"
            );

            return;
        }

        int confirmacion =

                JOptionPane.showConfirmDialog(
                        view,
                        "¿Eliminar reporte?"
                );

        if(confirmacion == 0){

            boolean resultado =
                    dao.eliminar(
                            idReporteSeleccionado
                    );

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Reporte eliminado"
                );

                listarReportes();

                idReporteSeleccionado = -1;

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al eliminar"
                );
            }
        }
    }

    public void volver() {

        new DashboardView();

        view.dispose();
    }
}