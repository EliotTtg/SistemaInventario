package dao;

import config.ConexionBD;
import model.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {


    public boolean guardar(
            Rol rol
    ) {

        String sql = """
                INSERT INTO roles(
                    nombre,
                    descripcion
                )
                VALUES(?,?)
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    rol.getNombre()
            );

            ps.setString(
                    2,
                    rol.getDescripcion()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar rol: "
                            + e.getMessage()
            );
        }

        return false;
    }


    public boolean actualizar(
            Rol rol
    ) {

        String sql = """
                UPDATE roles
                SET nombre = ?,
                    descripcion = ?
                WHERE id_rol = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    rol.getNombre()
            );

            ps.setString(
                    2,
                    rol.getDescripcion()
            );

            ps.setInt(
                    3,
                    rol.getIdRol()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error actualizar rol: "
                            + e.getMessage()
            );
        }

        return false;
    }


    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM roles
                WHERE id_rol = ?
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
                    "Error eliminar rol: "
                            + e.getMessage()
            );
        }

        return false;
    }


    public Rol buscarPorId(
            int id
    ) {

        Rol rol = null;

        String sql = """
                SELECT *
                FROM roles
                WHERE id_rol = ?
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

            if (rs.next()) {

                rol = new Rol();

                rol.setIdRol(
                        rs.getInt(
                                "id_rol"
                        )
                );

                rol.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                rol.setDescripcion(
                        rs.getString(
                                "descripcion"
                        )
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar rol: "
                            + e.getMessage()
            );
        }

        return rol;
    }


    public List<Rol> listar() {

        List<Rol> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM roles
                ORDER BY nombre
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)

        ) {

            while (rs.next()) {

                Rol rol = new Rol();

                rol.setIdRol(
                        rs.getInt(
                                "id_rol"
                        )
                );

                rol.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                rol.setDescripcion(
                        rs.getString(
                                "descripcion"
                        )
                );

                lista.add(rol);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar roles: "
                            + e.getMessage()
            );
        }

        return lista;
    }
}