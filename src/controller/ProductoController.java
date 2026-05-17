package controller;

import session.Session;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import dao.ReporteDAO;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Reporte;

import view.DashboardView;
import view.ProductoView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

public class ProductoController {

    private ProductoView view;

    private ProductoDAO dao;

    private CategoriaDAO categoriaDAO;

    private ProveedorDAO proveedorDAO;

    private ReporteDAO reporteDAO;

    private int idProductoSeleccionado = -1;

    public ProductoController(
            ProductoView view
    ) {

        this.view = view;

        dao = new ProductoDAO();

        categoriaDAO =
                new CategoriaDAO();

        proveedorDAO =
                new ProveedorDAO();

        reporteDAO =
                new ReporteDAO();

        cargarCategorias();

        cargarProveedores();

        listarProductos();

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

    public void cargarCategorias() {

        view.cbCategoria.removeAllItems();

        List<Categoria> lista =
                categoriaDAO.listar();

        for(Categoria c : lista){

            view.cbCategoria.addItem(c);
        }
    }

    public void cargarProveedores() {

        view.cbProveedor.removeAllItems();

        List<Proveedor> lista =
                proveedorDAO.listar();

        for(Proveedor p : lista){

            view.cbProveedor.addItem(p);
        }
    }

    public void guardar() {

        try {

            if(
                    view.txtCodigo.getText().trim().isEmpty()
                    ||
                    view.txtNombre.getText().trim().isEmpty()
                    ||
                    view.txtPrecio.getText().trim().isEmpty()
                    ||
                    view.txtStock.getText().trim().isEmpty()
                    ||
                    view.txtStockMinimo.getText().trim().isEmpty()
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "Complete todos los campos"
                );

                return;
            }

            if(
                    dao.existeCodigo(
                            view.txtCodigo.getText()
                    )
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "El codigo ya existe"
                );

                return;
            }

            int stock =
                    Integer.parseInt(
                            view.txtStock.getText()
                    );

            int stockMinimo =
                    Integer.parseInt(
                            view.txtStockMinimo.getText()
                    );

            double precio =
                    Double.parseDouble(
                            view.txtPrecio.getText()
                    );

            if(stock < 0){

                JOptionPane.showMessageDialog(
                        view,
                        "Stock invalido"
                );

                return;
            }

            if(stockMinimo < 0){

                JOptionPane.showMessageDialog(
                        view,
                        "Stock minimo invalido"
                );

                return;
            }

            if(precio <= 0){

                JOptionPane.showMessageDialog(
                        view,
                        "Precio invalido"
                );

                return;
            }

            Producto producto =
                    new Producto();

            producto.setCodigo(
                    view.txtCodigo.getText()
            );

            producto.setNombre(
                    view.txtNombre.getText()
            );

            producto.setDescripcion(
                    view.txtDescripcion.getText()
            );

            producto.setStockActual(stock);

            producto.setStockMinimo(stockMinimo);

            producto.setPrecio(precio);

            producto.setCategoria(
                    (Categoria)
                            view.cbCategoria
                                    .getSelectedItem()
            );

            producto.setProveedor(
                    (Proveedor)
                            view.cbProveedor
                                    .getSelectedItem()
            );

            boolean resultado =
                    dao.guardar(producto);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Producto guardado"
                );

                limpiar();

                listarProductos();

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al guardar"
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "Stock y precio deben ser numericos"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    view,
                    ex.getMessage()
            );
        }
    }

    public void actualizar() {

        if(idProductoSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un producto"
            );

            return;
        }

        try {

            if(
                    view.txtCodigo.getText().trim().isEmpty()
                    ||
                    view.txtNombre.getText().trim().isEmpty()
                    ||
                    view.txtPrecio.getText().trim().isEmpty()
                    ||
                    view.txtStock.getText().trim().isEmpty()
                    ||
                    view.txtStockMinimo.getText().trim().isEmpty()
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "Complete todos los campos"
                );

                return;
            }

            if(
                    dao.existeCodigoEditar(
                            view.txtCodigo.getText(),
                            idProductoSeleccionado
                    )
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "El codigo ya existe"
                );

                return;
            }

            int stock =
                    Integer.parseInt(
                            view.txtStock.getText()
                    );

            int stockMinimo =
                    Integer.parseInt(
                            view.txtStockMinimo.getText()
                    );

            double precio =
                    Double.parseDouble(
                            view.txtPrecio.getText()
                    );

            if(stock < 0){

                JOptionPane.showMessageDialog(
                        view,
                        "Stock invalido"
                );

                return;
            }

            if(stockMinimo < 0){

                JOptionPane.showMessageDialog(
                        view,
                        "Stock minimo invalido"
                );

                return;
            }

            if(precio <= 0){

                JOptionPane.showMessageDialog(
                        view,
                        "Precio invalido"
                );

                return;
            }

            Producto producto =
                    new Producto();

            producto.setIdProducto(
                    idProductoSeleccionado
            );

            producto.setCodigo(
                    view.txtCodigo.getText()
            );

            producto.setNombre(
                    view.txtNombre.getText()
            );

            producto.setDescripcion(
                    view.txtDescripcion.getText()
            );

            producto.setStockActual(stock);

            producto.setStockMinimo(stockMinimo);

            producto.setPrecio(precio);

            producto.setCategoria(
                    (Categoria)
                            view.cbCategoria
                                    .getSelectedItem()
            );

            producto.setProveedor(
                    (Proveedor)
                            view.cbProveedor
                                    .getSelectedItem()
            );

            boolean resultado =
                    dao.actualizar(producto);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Producto actualizado"
                );

                limpiar();

                listarProductos();

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al actualizar"
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "Stock y precio deben ser numericos"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    view,
                    ex.getMessage()
            );
        }
    }

    public void eliminar() {

        if(idProductoSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un producto"
            );

            return;
        }

        int confirmacion =
                JOptionPane.showConfirmDialog(
                        view,
                        "¿Eliminar producto?"
                );

        if(confirmacion == 0){

            boolean resultado =
                    dao.eliminar(
                            idProductoSeleccionado
                    );

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Producto eliminado"
                );

                limpiar();

                listarProductos();
            }
        }
    }

    public void generarReporte() {

        Reporte reporte =
                new Reporte();

        reporte.setTipoReporte(
                "REPORTE_PRODUCTOS"
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

    public void listarProductos() {

        DefaultTableModel model =
                (DefaultTableModel)
                        view.tabla.getModel();

        model.setRowCount(0);

        List<Producto> lista =
                dao.listar();

        for(Producto p : lista){

            model.addRow(
                    new Object[]{
                            p.getIdProducto(),
                            p.getCodigo(),
                            p.getNombre(),
                            p.getStockActual(),
                            p.getPrecio()
                    }
            );
        }
    }

    public void seleccionarFila() {

        int fila =
                view.tabla.getSelectedRow();

        if(fila != -1){

            idProductoSeleccionado =
                    Integer.parseInt(
                            view.tabla.getValueAt(
                                    fila,
                                    0
                            ).toString()
                    );

            Producto producto =
                    dao.buscarPorId(
                            idProductoSeleccionado
                    );

            if(producto != null){

                view.txtCodigo.setText(
                        producto.getCodigo()
                );

                view.txtNombre.setText(
                        producto.getNombre()
                );

                view.txtDescripcion.setText(
                        producto.getDescripcion()
                );

                view.txtStock.setText(
                        String.valueOf(
                                producto.getStockActual()
                        )
                );

                view.txtStockMinimo.setText(
                        String.valueOf(
                                producto.getStockMinimo()
                        )
                );

                view.txtPrecio.setText(
                        String.valueOf(
                                producto.getPrecio()
                        )
                );

                for(
                        int i = 0;
                        i < view.cbCategoria.getItemCount();
                        i++
                ){

                    Categoria c =
                            view.cbCategoria
                                    .getItemAt(i);

                    if(
                            c.getIdCategoria() ==
                            producto.getCategoria()
                                    .getIdCategoria()
                    ){

                        view.cbCategoria
                                .setSelectedIndex(i);

                        break;
                    }
                }

                for(
                        int i = 0;
                        i < view.cbProveedor.getItemCount();
                        i++
                ){

                    Proveedor p =
                            view.cbProveedor
                                    .getItemAt(i);

                    if(
                            p.getIdProveedor() ==
                            producto.getProveedor()
                                    .getIdProveedor()
                    ){

                        view.cbProveedor
                                .setSelectedIndex(i);

                        break;
                    }
                }
            }
        }
    }

    public void limpiar() {

        view.txtCodigo.setText("");

        view.txtNombre.setText("");

        view.txtDescripcion.setText("");

        view.txtStock.setText("");

        view.txtStockMinimo.setText("");

        view.txtPrecio.setText("");

        if(view.cbCategoria.getItemCount() > 0){

            view.cbCategoria
                    .setSelectedIndex(0);
        }

        if(view.cbProveedor.getItemCount() > 0){

            view.cbProveedor
                    .setSelectedIndex(0);
        }

        idProductoSeleccionado = -1;
    }

    public void volver() {

        new DashboardView();

        view.dispose();
    }
}