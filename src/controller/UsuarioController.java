package controller;

import dao.RolDAO;
import dao.UsuarioDAO;

import model.Rol;
import model.Usuario;

import view.DashboardView;
import view.UsuarioView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

public class UsuarioController {

    private UsuarioView view;

    private UsuarioDAO dao;

    private RolDAO rolDAO;

    private int idUsuarioSeleccionado = -1;

    public UsuarioController(
            UsuarioView view
    ) {

        this.view = view;

        this.dao = new UsuarioDAO();

        this.rolDAO =
                new RolDAO();

        cargarRoles();

        listarUsuarios();

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

        this.view.btnVolver
                .addActionListener(
                        e -> volver()
                );

        this.view.tabla.getSelectionModel()
                .addListSelectionListener(
                        e -> seleccionarFila()
                );
    }

    public void cargarRoles() {

        view.cbRol.removeAllItems();

        List<Rol> lista =
                rolDAO.listar();

        for(Rol rol : lista){

            view.cbRol.addItem(rol);
        }
    }

    public void guardar() {

        try {

            if(
                    view.txtNombre.getText()
                            .trim()
                            .isEmpty()
                    ||
                    view.txtUsuario.getText()
                            .trim()
                            .isEmpty()
                    ||
                    new String(
                            view.txtContrasena
                                    .getPassword()
                    ).trim().isEmpty()
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "Complete todos los campos"
                );

                return;
            }

            if(
                    dao.existeUsuario(
                            view.txtUsuario.getText()
                    )
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "El usuario ya existe"
                );

                return;
            }

            String password =
                    new String(
                            view.txtContrasena
                                    .getPassword()
                    );

            if(password.length() < 4){

                JOptionPane.showMessageDialog(
                        view,
                        "La contraseña debe tener minimo 4 caracteres"
                );

                return;
            }

            Usuario usuario =
                    new Usuario();

            usuario.setNombre(
                    view.txtNombre.getText()
            );

            usuario.setUsuario(
                    view.txtUsuario.getText()
            );

            usuario.setContrasena(
                    password
            );

            Rol rol =
                    (Rol)
                            view.cbRol
                                    .getSelectedItem();

            usuario.setRol(
                    rol.getNombre()
            );

            boolean resultado =
                    dao.guardar(usuario);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Usuario guardado"
                );

                limpiar();

                listarUsuarios();

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

        if(idUsuarioSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un usuario"
            );

            return;
        }

        try {

            if(
                    view.txtNombre.getText()
                            .trim()
                            .isEmpty()
                    ||
                    view.txtUsuario.getText()
                            .trim()
                            .isEmpty()
                    ||
                    new String(
                            view.txtContrasena
                                    .getPassword()
                    ).trim().isEmpty()
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "Complete todos los campos"
                );

                return;
            }

            if(
                    dao.existeUsuarioEditar(
                            view.txtUsuario.getText(),
                            idUsuarioSeleccionado
                    )
            ){

                JOptionPane.showMessageDialog(
                        view,
                        "El usuario ya existe"
                );

                return;
            }

            String password =
                    new String(
                            view.txtContrasena
                                    .getPassword()
                    );

            if(password.length() < 4){

                JOptionPane.showMessageDialog(
                        view,
                        "La contraseña debe tener minimo 4 caracteres"
                );

                return;
            }

            Usuario usuario =
                    new Usuario();

            usuario.setIdUsuario(
                    idUsuarioSeleccionado
            );

            usuario.setNombre(
                    view.txtNombre.getText()
            );

            usuario.setUsuario(
                    view.txtUsuario.getText()
            );

            usuario.setContrasena(
                    password
            );

            Rol rol =
                    (Rol)
                            view.cbRol
                                    .getSelectedItem();

            usuario.setRol(
                    rol.getNombre()
            );

            boolean resultado =
                    dao.actualizar(usuario);

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Usuario actualizado"
                );

                limpiar();

                listarUsuarios();

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

        if(idUsuarioSeleccionado == -1){

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione un usuario"
            );

            return;
        }

        int confirmacion =

                JOptionPane.showConfirmDialog(
                        view,
                        "¿Eliminar usuario?"
                );

        if(confirmacion == 0){

            boolean resultado =
                    dao.eliminar(
                            idUsuarioSeleccionado
                    );

            if(resultado){

                JOptionPane.showMessageDialog(
                        view,
                        "Usuario eliminado"
                );

                limpiar();

                listarUsuarios();

            }else{

                JOptionPane.showMessageDialog(
                        view,
                        "Error al eliminar"
                );
            }
        }
    }

    public void listarUsuarios() {

        DefaultTableModel model =

                (DefaultTableModel)
                        view.tabla.getModel();

        model.setRowCount(0);

        List<Usuario> lista =
                dao.listar();

        for(Usuario u : lista){

            model.addRow(

                    new Object[]{

                            u.getIdUsuario(),

                            u.getNombre(),

                            u.getUsuario(),

                            u.getRol()
                    }
            );
        }
    }

    public void seleccionarFila() {

        int fila =
                view.tabla.getSelectedRow();

        if(fila != -1){

            idUsuarioSeleccionado =

                    Integer.parseInt(

                            view.tabla.getValueAt(
                                    fila,
                                    0
                            ).toString()
                    );

            Usuario usuario =
                    dao.buscarPorId(
                            idUsuarioSeleccionado
                    );

            if(usuario != null){

                view.txtNombre.setText(
                        usuario.getNombre()
                );

                view.txtUsuario.setText(
                        usuario.getUsuario()
                );

                view.txtContrasena.setText(
                        usuario.getContrasena()
                );

                for(
                        int i = 0;
                        i < view.cbRol.getItemCount();
                        i++
                ){

                    Rol rol =
                            view.cbRol
                                    .getItemAt(i);

                    if(
                            rol.getNombre()
                                    .equals(
                                            usuario.getRol()
                                    )
                    ){

                        view.cbRol
                                .setSelectedIndex(i);

                        break;
                    }
                }
            }
        }
    }

    public void limpiar() {

        view.txtNombre.setText("");

        view.txtUsuario.setText("");

        view.txtContrasena.setText("");

        if(view.cbRol.getItemCount() > 0){

            view.cbRol.setSelectedIndex(0);
        }

        idUsuarioSeleccionado = -1;
    }

    public void volver() {

        new DashboardView();

        view.dispose();
    }
}