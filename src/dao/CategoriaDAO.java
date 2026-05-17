package dao;

import config.ConexionBD;

import model.Categoria;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public boolean existeCategoria(
            String nombre
    ) {

        String sql = """
                SELECT id_categoria
                FROM categorias
                WHERE nombre = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(1, nombre);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Error validar categoria: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean existeCategoriaEditar(

            String nombre,

            int idCategoria
    ) {

        String sql = """
                SELECT id_categoria
                FROM categorias
                WHERE nombre = ?
                AND id_categoria != ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(1, nombre);

            ps.setInt(2, idCategoria);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Error validar categoria editar: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean guardar(
            Categoria categoria
    ) {

        String sql = """
                INSERT INTO categorias(
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
                    categoria.getNombre()
            );

            ps.setString(
                    2,
                    categoria.getDescripcion()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar categoria: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean actualizar(
            Categoria categoria
    ) {

        String sql = """
                UPDATE categorias
                SET nombre = ?,
                    descripcion = ?
                WHERE id_categoria = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    categoria.getNombre()
            );

            ps.setString(
                    2,
                    categoria.getDescripcion()
            );

            ps.setInt(
                    3,
                    categoria.getIdCategoria()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error actualizar categoria: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM categorias
                WHERE id_categoria = ?
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
                    "Error eliminar categoria: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public Categoria buscarPorId(
            int id
    ) {

        Categoria categoria = null;

        String sql = """
                SELECT *
                FROM categorias
                WHERE id_categoria = ?
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

                categoria =
                        new Categoria();

                categoria.setIdCategoria(
                        rs.getInt(
                                "id_categoria"
                        )
                );

                categoria.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                categoria.setDescripcion(
                        rs.getString(
                                "descripcion"
                        )
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar categoria: "
                            + e.getMessage()
            );
        }

        return categoria;
    }

    public List<Categoria> listar() {

        List<Categoria> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM categorias
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

                Categoria categoria =
                        new Categoria();

                categoria.setIdCategoria(
                        rs.getInt(
                                "id_categoria"
                        )
                );

                categoria.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                categoria.setDescripcion(
                        rs.getString(
                                "descripcion"
                        )
                );

                lista.add(categoria);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar categorias: "
                            + e.getMessage()
            );
        }

        return lista;
    }
}