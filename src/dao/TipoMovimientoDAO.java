package dao;

import config.ConexionBD;
import model.TipoMovimiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoMovimientoDAO {


    public boolean guardar(
            TipoMovimiento tipoMovimiento
    ) {

        String sql = """
                INSERT INTO tipos_movimiento(
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
                    tipoMovimiento.getNombre()
            );

            ps.setString(
                    2,
                    tipoMovimiento.getDescripcion()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar tipo movimiento: "
                            + e.getMessage()
            );
        }

        return false;
    }


    public boolean actualizar(
            TipoMovimiento tipoMovimiento
    ) {

        String sql = """
                UPDATE tipos_movimiento
                SET nombre = ?,
                    descripcion = ?
                WHERE id_tipo_movimiento = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    tipoMovimiento.getNombre()
            );

            ps.setString(
                    2,
                    tipoMovimiento.getDescripcion()
            );

            ps.setInt(
                    3,
                    tipoMovimiento
                            .getIdTipoMovimiento()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error actualizar tipo movimiento: "
                            + e.getMessage()
            );
        }

        return false;
    }


    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM tipos_movimiento
                WHERE id_tipo_movimiento = ?
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
                    "Error eliminar tipo movimiento: "
                            + e.getMessage()
            );
        }

        return false;
    }


    public TipoMovimiento buscarPorId(
            int id
    ) {

        TipoMovimiento tipoMovimiento =
                null;

        String sql = """
                SELECT *
                FROM tipos_movimiento
                WHERE id_tipo_movimiento = ?
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

                tipoMovimiento =
                        new TipoMovimiento();

                tipoMovimiento.setIdTipoMovimiento(
                        rs.getInt(
                                "id_tipo_movimiento"
                        )
                );

                tipoMovimiento.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                tipoMovimiento.setDescripcion(
                        rs.getString(
                                "descripcion"
                        )
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar tipo movimiento: "
                            + e.getMessage()
            );
        }

        return tipoMovimiento;
    }


    public List<TipoMovimiento> listar() {

        List<TipoMovimiento> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM tipos_movimiento
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

                TipoMovimiento tipoMovimiento =
                        new TipoMovimiento();

                tipoMovimiento.setIdTipoMovimiento(
                        rs.getInt(
                                "id_tipo_movimiento"
                        )
                );

                tipoMovimiento.setNombre(
                        rs.getString(
                                "nombre"
                        )
                );

                tipoMovimiento.setDescripcion(
                        rs.getString(
                                "descripcion"
                        )
                );

                lista.add(tipoMovimiento);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar tipos movimiento: "
                            + e.getMessage()
            );
        }

        return lista;
    }
}