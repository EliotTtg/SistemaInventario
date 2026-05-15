package controller;

import dao.MovimientoDAO;
import dao.ProductoDAO;
import dao.ReporteDAO;

import model.MovimientoInventario;
import model.Producto;
import model.Reporte;
import model.TipoMovimiento;

import session.Session;

import view.DashboardView;
import view.MovimientoView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MovimientoController {

    private MovimientoView view;

    private MovimientoDAO dao;

    private ProductoDAO productoDAO;

    private ReporteDAO reporteDAO;

    private int idMovimientoSeleccionado = -1;

    public MovimientoController(
            MovimientoView view
    ) {

        this.view = view;

        this.dao = new MovimientoDAO();

        this.productoDAO =
                new ProductoDAO();

        this.reporteDAO =
                new ReporteDAO();

        cargarProductos();

        listarMovimientos();

        this.view.btnGuardar
                .addActionListener(
                        e -> guardar()
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

    public void cargarProductos() {

        view.cbProducto.removeAllItems();

        List<Producto> lista =
                productoDAO.listar();

        for(Producto p : lista){

            view.cbProducto.addItem(p);
        }
    }

    public void guardar() {

        try {

            MovimientoInventario movimiento =
                    new MovimientoInventario();

            movimiento.setCantidad(

                    Integer.parseInt(
                            view.txtCantidad.getText()
                    )
            );

            movimiento.setMotivo(
                    view.txtMotivo.getText()
            );

            TipoMovimiento tipo =
                    new TipoMovimiento();

            if(view.cbTipoMovimiento
                    .getSelectedItem()
                    .toString()
                    .equals("ENTRADA")){

                tipo.setIdTipoMovimiento(1);

            }else{

                tipo.setIdTipoMovimiento(2);
            }

            movimiento.setTipoMovimiento(tipo);

            Producto producto =
                    (Producto)
                            view.cbProducto
                                    .getSelectedItem();

            movimiento.setProducto(producto);

            movimiento.setUsuario(
                    Session.usuarioActual
            );

            boolean resultado =
                    dao.guardar(movimiento);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Movimiento registrado"
                );

                limpiar();

                listarMovimientos();

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al registrar"
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    view,
                    ex.getMessage()
            );
        }
    }

    public void generarReporte() {

        Reporte reporte =
                new Reporte();

        reporte.setTipoReporte(
                "REPORTE_MOVIMIENTOS"
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

    public void eliminar() {

        if(idMovimientoSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un movimiento"
            );

            return;
        }

        int confirmacion =

                JOptionPane.showConfirmDialog(
                        view,
                        "¿Eliminar movimiento?"
                );

        if(confirmacion == 0){

            boolean resultado =
                    dao.eliminar(
                            idMovimientoSeleccionado
                    );

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Movimiento eliminado"
                );

                limpiar();

                listarMovimientos();
            }
        }
    }

    public void listarMovimientos() {

        DefaultTableModel model =

                (DefaultTableModel)
                        view.tabla.getModel();

        model.setRowCount(0);

        List<MovimientoInventario> lista =
                dao.listar();

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm"
                );

        for(MovimientoInventario m : lista){

            model.addRow(

                    new Object[]{

                            m.getIdMovimiento(),

                            m.getTipoMovimiento()
                                    .getIdTipoMovimiento() == 1
                                    ? "ENTRADA"
                                    : "SALIDA",

                            m.getCantidad(),

                            m.getProducto()
                                    .getNombre(),

                            m.getMotivo(),

                            m.getFecha()
                                    .format(formato)
                    }
            );
        }
    }

    public void seleccionarFila() {

        int fila =
                view.tabla.getSelectedRow();

        if(fila != -1){

            idMovimientoSeleccionado =

                    Integer.parseInt(

                            view.tabla.getValueAt(
                                    fila,
                                    0
                            ).toString()
                    );

            MovimientoInventario movimiento =
                    dao.buscarPorId(
                            idMovimientoSeleccionado
                    );

            if(movimiento != null){

                view.txtCantidad.setText(

                        String.valueOf(
                                movimiento.getCantidad()
                        )
                );

                view.txtMotivo.setText(
                        movimiento.getMotivo()
                );

                if(
                        movimiento.getTipoMovimiento()
                                .getIdTipoMovimiento() == 1
                ){

                    view.cbTipoMovimiento
                            .setSelectedItem(
                                    "ENTRADA"
                            );

                }else{

                    view.cbTipoMovimiento
                            .setSelectedItem(
                                    "SALIDA"
                            );
                }

                for(
                        int i = 0;
                        i < view.cbProducto.getItemCount();
                        i++
                ){

                    Producto p =
                            view.cbProducto
                                    .getItemAt(i);

                    if(
                            p.getIdProducto() ==
                            movimiento.getProducto()
                                    .getIdProducto()
                    ){

                        view.cbProducto
                                .setSelectedIndex(i);

                        break;
                    }
                }
            }
        }
    }

    public void limpiar() {

        view.txtCantidad.setText("");

        view.txtMotivo.setText("");

        view.cbTipoMovimiento
                .setSelectedIndex(0);

        if(view.cbProducto.getItemCount() > 0){

            view.cbProducto
                    .setSelectedIndex(0);
        }

        idMovimientoSeleccionado = -1;
    }

    public void volver() {

        new DashboardView();

        view.dispose();
    }
}