package controller;

import dao.ProductoDAO;
import model.Categoria;
import model.Producto;
import model.Proveedor;
import view.ProductoView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoController {

    private ProductoView view;

    private ProductoDAO dao;

    private int idProductoSeleccionado = -1;

    public ProductoController(
            ProductoView view
    ) {

        this.view = view;

        this.dao = new ProductoDAO();

        listarProductos();

        this.view.btnGuardar
                .addActionListener(e -> guardar());

        this.view.btnActualizar
                .addActionListener(e -> actualizar());

        this.view.btnEliminar
                .addActionListener(e -> eliminar());

        this.view.tabla.getSelectionModel()
                .addListSelectionListener(e -> seleccionarFila());
    }


    public void guardar() {

        try {

            Producto producto =
                    new Producto();

            producto.setCodigo(
                    view.txtCodigo.getText()
            );

            producto.setNombre(
                    view.txtNombre.getText()
            );

            producto.setPrecio(
                    Double.parseDouble(
                            view.txtPrecio.getText()
                    )
            );


            Categoria categoria =
                    new Categoria();

            categoria.setIdCategoria(1);

            Proveedor proveedor =
                    new Proveedor();

            proveedor.setIdProveedor(1);

            producto.setCategoria(categoria);

            producto.setProveedor(proveedor);

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

            producto.setPrecio(
                    Double.parseDouble(
                            view.txtPrecio.getText()
                    )
            );

            Categoria categoria =
                    new Categoria();

            categoria.setIdCategoria(1);

            Proveedor proveedor =
                    new Proveedor();

            proveedor.setIdProveedor(1);

            producto.setCategoria(categoria);

            producto.setProveedor(proveedor);

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

            view.txtCodigo.setText(

                    view.tabla.getValueAt(
                            fila,
                            1
                    ).toString()
            );

            view.txtNombre.setText(

                    view.tabla.getValueAt(
                            fila,
                            2
                    ).toString()
            );

            view.txtPrecio.setText(

                    view.tabla.getValueAt(
                            fila,
                            3
                    ).toString()
            );
        }
    }


    public void limpiar() {

        view.txtCodigo.setText("");

        view.txtNombre.setText("");

        view.txtPrecio.setText("");

        idProductoSeleccionado = -1;
    }
}