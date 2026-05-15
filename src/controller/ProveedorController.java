package controller;

import dao.ProveedorDAO;
import dao.ReporteDAO;

import model.Proveedor;
import model.Reporte;

import session.Session;

import view.DashboardView;
import view.ProveedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

public class ProveedorController {

    private ProveedorView view;

    private ProveedorDAO dao;

    private ReporteDAO reporteDAO;

    private int idProveedorSeleccionado = -1;

    public ProveedorController(
            ProveedorView view
    ) {

        this.view = view;

        this.dao = new ProveedorDAO();

        this.reporteDAO =
                new ReporteDAO();

        listarProveedores();

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

            Proveedor proveedor =
                    new Proveedor();

            proveedor.setNombre(
                    view.txtNombre.getText()
            );

            proveedor.setRuc(
                    view.txtRuc.getText()
            );

            proveedor.setTelefono(
                    view.txtTelefono.getText()
            );

            proveedor.setDireccion(
                    view.txtDireccion.getText()
            );

            boolean resultado =
                    dao.guardar(proveedor);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Proveedor guardado"
                );

                limpiar();

                listarProveedores();

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

        if(idProveedorSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un proveedor"
            );

            return;
        }

        try {

            Proveedor proveedor =
                    new Proveedor();

            proveedor.setIdProveedor(
                    idProveedorSeleccionado
            );

            proveedor.setNombre(
                    view.txtNombre.getText()
            );

            proveedor.setRuc(
                    view.txtRuc.getText()
            );

            proveedor.setTelefono(
                    view.txtTelefono.getText()
            );

            proveedor.setDireccion(
                    view.txtDireccion.getText()
            );

            boolean resultado =
                    dao.actualizar(proveedor);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Proveedor actualizado"
                );

                limpiar();

                listarProveedores();

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

        if(idProveedorSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un proveedor"
            );

            return;
        }

        int confirmacion =

                JOptionPane.showConfirmDialog(
                        view,
                        "¿Eliminar proveedor?"
                );

        if(confirmacion == 0){

            boolean resultado =
                    dao.eliminar(
                            idProveedorSeleccionado
                    );

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Proveedor eliminado"
                );

                limpiar();

                listarProveedores();
            }
        }
    }

    public void generarReporte() {

        Reporte reporte =
                new Reporte();

        reporte.setTipoReporte(
                "REPORTE_PROVEEDORES"
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

    public void listarProveedores() {

        DefaultTableModel model =

                (DefaultTableModel)
                        view.tabla.getModel();

        model.setRowCount(0);

        List<Proveedor> lista =
                dao.listar();

        for(Proveedor p : lista){

            model.addRow(

                    new Object[]{

                            p.getIdProveedor(),

                            p.getNombre(),

                            p.getRuc(),

                            p.getTelefono(),

                            p.getDireccion()
                    }
            );
        }
    }

    public void seleccionarFila() {

        int fila =
                view.tabla.getSelectedRow();

        if(fila != -1){

            idProveedorSeleccionado =

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

            view.txtRuc.setText(

                    view.tabla.getValueAt(
                            fila,
                            2
                    ).toString()
            );

            view.txtTelefono.setText(

                    view.tabla.getValueAt(
                            fila,
                            3
                    ).toString()
            );

            view.txtDireccion.setText(

                    view.tabla.getValueAt(
                            fila,
                            4
                    ).toString()
            );
        }
    }

    public void limpiar() {

        view.txtNombre.setText("");

        view.txtRuc.setText("");

        view.txtTelefono.setText("");

        view.txtDireccion.setText("");

        idProveedorSeleccionado = -1;
    }

    public void volver() {

        new DashboardView();

        view.dispose();
    }
}