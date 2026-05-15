package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtUsuario;

    private JPasswordField txtPassword;

    private JButton btnLogin;

    public LoginView() {

        setTitle("Sistema Inventario");

        setSize(350, 350);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        JPanel panel =
                new JPanel();

        panel.setLayout(null);

        panel.setBackground(
                Color.WHITE
        );

        JLabel lblTitulo =
                new JLabel(
                        "Sistema Inventario"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        lblTitulo.setBounds(
                70,
                20,
                250,
                30
        );

        panel.add(lblTitulo);

        txtUsuario =
                new JTextField();

        txtUsuario.setBounds(
                40,
                80,
                250,
                35
        );

        txtUsuario.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        txtUsuario.setBorder(
                BorderFactory.createTitledBorder(
                        "Usuario"
                )
        );

        panel.add(txtUsuario);

        txtPassword =
                new JPasswordField();

        txtPassword.setBounds(
                40,
                130,
                250,
                35
        );

        txtPassword.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        txtPassword.setBorder(
                BorderFactory.createTitledBorder(
                        "Contraseña"
                )
        );

        panel.add(txtPassword);

        btnLogin =
                new JButton(
                        "Ingresar"
                );

        btnLogin.setBounds(
                100,
                180,
                130,
                35
        );

        btnLogin.setBackground(
                new Color(52,73,94)
        );

        btnLogin.setForeground(
                Color.WHITE
        );

        btnLogin.setFocusPainted(false);

        btnLogin.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        panel.add(btnLogin);

        add(panel);

        setVisible(true);
    }

    public String getUsuario() {

        return txtUsuario.getText();
    }

    public String getPassword() {

        return new String(
                txtPassword.getPassword()
        );
    }

    public void addLoginListener(
            ActionListener listener
    ) {

        btnLogin.addActionListener(listener);
    }

    public void mostrarMensaje(
            String mensaje
    ) {

        JOptionPane.showMessageDialog(
                this,
                mensaje
        );
    }

    public void mostrarError(
            String mensaje
    ) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void mostrarExito(
            String mensaje
    ) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}