package controller;

import dao.CategoriaDAO;
import dao.ReporteDAO;

import model.Categoria;
import model.Reporte;

import session.Session;

import view.CategoriaView;
import view.DashboardView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

public class CategoriaController {

    private CategoriaView view;

    private CategoriaDAO dao;

    private ReporteDAO reporteDAO;

    private int idCategoriaSeleccionada = -1;

    public CategoriaController(
            CategoriaView view
    ) {

        this.view = view;

        this.dao = new CategoriaDAO();

        this.reporteDAO =
                new ReporteDAO();

        listarCategorias();

        this.view.btnGuardar
                .addActionListener(
                        e -> guardar()
                );

        this.view.btnActualizar
                .addActionListener(
                        e -> actualizar()
                );

        this.view.btnEliminar
                .addActionListener(
                        e -> eliminar()
                );

        this.view.btnGenerarReporte
                .addActionListener(
                        e -> generarReporte()
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

    public void guardar() {

        try {

            Categoria categoria =
                    new Categoria();

            categoria.setNombre(
                    view.txtNombre.getText()
            );

            categoria.setDescripcion(
                    view.txtDescripcion.getText()
            );

            boolean resultado =
                    dao.guardar(categoria);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Categoria guardada"
                );

                limpiar();

                listarCategorias();

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al guardar"
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    view,
                    ex.getMessage()
            );
        }
    }

    public void actualizar() {

        if(idCategoriaSeleccionada == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione una categoria"
            );

            return;
        }

        try {

            Categoria categoria =
                    new Categoria();

            categoria.setIdCategoria(
                    idCategoriaSeleccionada
            );

            categoria.setNombre(
                    view.txtNombre.getText()
            );

            categoria.setDescripcion(
                    view.txtDescripcion.getText()
            );

            boolean resultado =
                    dao.actualizar(categoria);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Categoria actualizada"
                );

                limpiar();

                listarCategorias();

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al actualizar"
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    view,
                    ex.getMessage()
            );
        }
    }

    public void eliminar() {

        if(idCategoriaSeleccionada == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione una categoria"
            );

            return;
        }

        int confirmacion =

                JOptionPane.showConfirmDialog(
                        view,
                        "¿Eliminar categoria?"
                );

        if(confirmacion == 0){

            boolean resultado =
                    dao.eliminar(
                            idCategoriaSeleccionada
                    );

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Categoria eliminada"
                );

                limpiar();

                listarCategorias();
            }
        }
    }

    public void generarReporte() {

        Reporte reporte =
                new Reporte();

        reporte.setTipoReporte(
                "REPORTE_CATEGORIAS"
        );

        reporte.setUsuario(
                Session.usuarioActual
        );

        boolean resultado =
                reporteDAO.guardar(reporte);

        if(resultado){

            JOptionPane.showMessageDialog(
                    view,
                    "Reporte generado correctamente"
            );

        }else{

            JOptionPane.showMessageDialog(
                    view,
                    "Error al generar reporte"
            );
        }
    }

    public void listarCategorias() {

        DefaultTableModel model =

                (DefaultTableModel)
                        view.tabla.getModel();

        model.setRowCount(0);

        List<Categoria> lista =
                dao.listar();

        for(Categoria c : lista){

            model.addRow(

                    new Object[]{

                            c.getIdCategoria(),

                            c.getNombre(),

                            c.getDescripcion()
                    }
            );
        }
    }

    public void seleccionarFila() {

        int fila =
                view.tabla.getSelectedRow();

        if(fila != -1){

            idCategoriaSeleccionada =

                    Integer.parseInt(

                            view.tabla.getValueAt(
                                    fila,
                                    0
                            ).toString()
                    );

            view.txtNombre.setText(

                    view.tabla.getValueAt(
                            fila,
                            1
                    ).toString()
            );

            view.txtDescripcion.setText(

                    view.tabla.getValueAt(
                            fila,
                            2
                    ).toString()
            );
        }
    }

    public void limpiar() {

        view.txtNombre.setText("");

        view.txtDescripcion.setText("");

        idCategoriaSeleccionada = -1;
    }

    public void volver() {

        new DashboardView();

        view.dispose();
    }
}