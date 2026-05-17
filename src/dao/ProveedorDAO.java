package dao;

import config.ConexionBD;

import model.Proveedor;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public boolean existeRuc(
            String ruc
    ) {

        String sql = """
                SELECT id_proveedor
                FROM proveedores
                WHERE ruc = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(1, ruc);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Error validar RUC: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean existeRucEditar(

            String ruc,

            int idProveedor
    ) {

        String sql = """
                SELECT id_proveedor
                FROM proveedores
                WHERE ruc = ?
                AND id_proveedor != ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(1, ruc);

            ps.setInt(2, idProveedor);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Error validar RUC editar: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean guardar(
            Proveedor proveedor
    ) {

        String sql = """
                INSERT INTO proveedores(
                    nombre,
                    ruc,
                    telefono,
                    direccion
                )
                VALUES(?,?,?,?)
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    proveedor.getNombre()
            );

            ps.setString(
                    2,
                    proveedor.getRuc()
            );

            ps.setString(
                    3,
                    proveedor.getTelefono()
            );

            ps.setString(
                    4,
                    proveedor.getDireccion()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar proveedor: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean actualizar(
            Proveedor proveedor
    ) {

        String sql = """
                UPDATE proveedores
                SET nombre = ?,
                    ruc = ?,
                    telefono = ?,
                    direccion = ?
                WHERE id_proveedor = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    proveedor.getNombre()
            );

            ps.setString(
                    2,
                    proveedor.getRuc()
            );

            ps.setString(
                    3,
                    proveedor.getTelefono()
            );

            ps.setString(
                    4,
                    proveedor.getDireccion()
            );

            ps.setInt(
                    5,
                    proveedor.getIdProveedor()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error actualizar proveedor: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM proveedores
                WHERE id_proveedor = ?
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
                    "Error eliminar proveedor: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public Proveedor buscarPorId(
            int id
    ) {

        Proveedor proveedor = null;

        String sql = """
                SELECT *
                FROM proveedores
                WHERE id_proveedor = ?
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

                proveedor =
                        new Proveedor();

                proveedor.setIdProveedor(
                        rs.getInt(
                                "id_proveedor"
                        )
                );

                proveedor.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                proveedor.setRuc(
                        rs.getString(
                                "ruc"
                        )
                );

                proveedor.setTelefono(
                        rs.getString(
                                "telefono"
                        )
                );

                proveedor.setDireccion(
                        rs.getString(
                                "direccion"
                        )
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar proveedor: "
                            + e.getMessage()
            );
        }

        return proveedor;
    }

    public List<Proveedor> listar() {

        List<Proveedor> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM proveedores
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

                Proveedor proveedor =
                        new Proveedor();

                proveedor.setIdProveedor(
                        rs.getInt(
                                "id_proveedor"
                        )
                );

                proveedor.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                proveedor.setRuc(
                        rs.getString(
                                "ruc"
                        )
                );

                proveedor.setTelefono(
                        rs.getString(
                                "telefono"
                        )
                );

                proveedor.setDireccion(
                        rs.getString(
                                "direccion"
                        )
                );

                lista.add(proveedor);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar proveedores: "
                            + e.getMessage()
            );
        }

        return lista;
    }
}