package controller;

import dao.UsuarioDAO;

import model.Usuario;

import session.Session;

import view.DashboardView;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginView view;

    private UsuarioDAO dao;

    public LoginController(
            LoginView view
    ) {

        this.view = view;

        this.dao = new UsuarioDAO();

        this.view.addLoginListener(
                new LoginListener()
        );
    }

    class LoginListener
            implements ActionListener {

        @Override
        public void actionPerformed(
                ActionEvent e
        ) {

            String usuario =
                    view.getUsuario();

            String password =
                    view.getPassword();

            Usuario user =
                    dao.validarUsuario(
                            usuario,
                            password
                    );

            if(user != null){

                Session.usuarioActual =
                        user;

                view.mostrarExito(
                        "Bienvenido "
                        + user.getNombre()
                );

                new DashboardView();

                view.dispose();

            }else{

                view.mostrarError(
                        "Credenciales incorrectas"
                );
            }
        }
    }
}