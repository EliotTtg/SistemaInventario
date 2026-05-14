package dao;

import config.ConexionBD;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario validarUsuario(
            String usuario,
            String password
    ) {

        Usuario user = null;

        String sql = """
                SELECT u.id_usuario,
                       u.nombre,
                       u.usuario,
                       u.password,
                       r.nombre AS rol
                FROM usuarios u
                INNER JOIN roles r
                    ON u.id_rol = r.id_rol
                WHERE u.usuario = ?
                AND u.password = ?
                """;

        try (

                Connection connection =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new Usuario();

                user.setIdUsuario(
                        rs.getInt("id_usuario")
                );

                user.setNombre(
                        rs.getString("nombre")
                );

                user.setUsuario(
                        rs.getString("usuario")
                );

                user.setContrasena(
                        rs.getString("password")
                );

                user.setRol(
                        rs.getString("rol")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al validar usuario: "
                            + e.getMessage()
            );
        }

        return user;
    }
}