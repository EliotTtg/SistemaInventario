package dao;

import config.ConexionBD;

import model.Usuario;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

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

            ResultSet rs =
                    ps.executeQuery();

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
                    "Error validar usuario: "
                            + e.getMessage()
            );
        }

        return user;
    }

    public boolean guardar(
            Usuario usuario
    ) {

        String sql = """
                INSERT INTO usuarios(
                    nombre,
                    usuario,
                    password,
                    id_rol
                )
                VALUES(
                    ?,
                    ?,
                    ?,
                    (
                        SELECT id_rol
                        FROM roles
                        WHERE nombre = ?
                    )
                )
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    usuario.getNombre()
            );

            ps.setString(
                    2,
                    usuario.getUsuario()
            );

            ps.setString(
                    3,
                    usuario.getContrasena()
            );

            ps.setString(
                    4,
                    usuario.getRol()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar usuario: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean actualizar(
            Usuario usuario
    ) {

        String sql = """
                UPDATE usuarios
                SET nombre = ?,
                    usuario = ?,
                    password = ?,
                    id_rol = (
                        SELECT id_rol
                        FROM roles
                        WHERE nombre = ?
                    )
                WHERE id_usuario = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    usuario.getNombre()
            );

            ps.setString(
                    2,
                    usuario.getUsuario()
            );

            ps.setString(
                    3,
                    usuario.getContrasena()
            );

            ps.setString(
                    4,
                    usuario.getRol()
            );

            ps.setInt(
                    5,
                    usuario.getIdUsuario()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error actualizar usuario: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM usuarios
                WHERE id_usuario = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error eliminar usuario: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public Usuario buscarPorId(
            int id
    ) {

        Usuario usuario = null;

        String sql = """
                SELECT u.id_usuario,
                       u.nombre,
                       u.usuario,
                       u.password,
                       r.nombre AS rol
                FROM usuarios u
                INNER JOIN roles r
                    ON u.id_rol = r.id_rol
                WHERE u.id_usuario = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                usuario =
                        new Usuario();

                usuario.setIdUsuario(
                        rs.getInt("id_usuario")
                );

                usuario.setNombre(
                        rs.getString("nombre")
                );

                usuario.setUsuario(
                        rs.getString("usuario")
                );

                usuario.setContrasena(
                        rs.getString("password")
                );

                usuario.setRol(
                        rs.getString("rol")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar usuario: "
                            + e.getMessage()
            );
        }

        return usuario;
    }

    public List<Usuario> listar() {

        List<Usuario> lista =
                new ArrayList<>();

        String sql = """
                SELECT u.id_usuario,
                       u.nombre,
                       u.usuario,
                       r.nombre AS rol
                FROM usuarios u
                INNER JOIN roles r
                    ON u.id_rol = r.id_rol
                ORDER BY u.id_usuario DESC
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)

        ) {

            while(rs.next()){

                Usuario usuario =
                        new Usuario();

                usuario.setIdUsuario(
                        rs.getInt("id_usuario")
                );

                usuario.setNombre(
                        rs.getString("nombre")
                );

                usuario.setUsuario(
                        rs.getString("usuario")
                );

                usuario.setRol(
                        rs.getString("rol")
                );

                lista.add(usuario);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar usuarios: "
                            + e.getMessage()
            );
        }

        return lista;
    }
}