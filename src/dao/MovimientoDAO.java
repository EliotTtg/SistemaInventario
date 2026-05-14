package dao;

import config.ConexionBD;
import model.MovimientoInventario;
import model.Producto;
import model.TipoMovimiento;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {

    public boolean guardar(
            MovimientoInventario movimiento
    ) {

        String sql = """
                INSERT INTO movimientos(
                    cantidad,
                    motivo,
                    id_tipo_movimiento,
                    id_producto,
                    id_usuario
                )
                VALUES(?,?,?,?,?)
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    movimiento.getCantidad()
            );

            ps.setString(
                    2,
                    movimiento.getMotivo()
            );

            ps.setInt(
                    3,
                    movimiento
                            .getTipoMovimiento()
                            .getIdTipoMovimiento()
            );

            ps.setInt(
                    4,
                    movimiento
                            .getProducto()
                            .getIdProducto()
            );

            ps.setInt(
                    5,
                    movimiento
                            .getUsuario()
                            .getIdUsuario()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar movimiento: "
                            + e.getMessage()
            );
        }

        return false;
    }


    public MovimientoInventario buscarPorId(
            int id
    ) {

        MovimientoInventario movimiento =
                null;

        String sql = """
                SELECT *
                FROM movimientos
                WHERE id_movimiento = ?
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

                movimiento =
                        new MovimientoInventario();

                movimiento.setIdMovimiento(
                        rs.getInt(
                                "id_movimiento"
                        )
                );

                movimiento.setFecha(
                        rs.getTimestamp(
                                "fecha"
                        ).toLocalDateTime()
                );

                movimiento.setCantidad(
                        rs.getInt(
                                "cantidad"
                        )
                );

                movimiento.setMotivo(
                        rs.getString(
                                "motivo"
                        )
                );

                TipoMovimiento tipo =
                        new TipoMovimiento();

                tipo.setIdTipoMovimiento(
                        rs.getInt(
                                "id_tipo_movimiento"
                        )
                );

                movimiento.setTipoMovimiento(
                        tipo
                );


                Producto producto =
                        new Producto();

                producto.setIdProducto(
                        rs.getInt(
                                "id_producto"
                        )
                );

                movimiento.setProducto(
                        producto
                );

                Usuario usuario =
                        new Usuario();

                usuario.setIdUsuario(
                        rs.getInt(
                                "id_usuario"
                        )
                );

                movimiento.setUsuario(
                        usuario
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar movimiento: "
                            + e.getMessage()
            );
        }

        return movimiento;
    }


    public List<MovimientoInventario> listar() {

        List<MovimientoInventario> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM movimientos
                ORDER BY fecha DESC
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

                MovimientoInventario movimiento =
                        new MovimientoInventario();

                movimiento.setIdMovimiento(
                        rs.getInt(
                                "id_movimiento"
                        )
                );

                movimiento.setFecha(
                        rs.getTimestamp(
                                "fecha"
                        ).toLocalDateTime()
                );

                movimiento.setCantidad(
                        rs.getInt(
                                "cantidad"
                        )
                );

                movimiento.setMotivo(
                        rs.getString(
                                "motivo"
                        )
                );


                TipoMovimiento tipo =
                        new TipoMovimiento();

                tipo.setIdTipoMovimiento(
                        rs.getInt(
                                "id_tipo_movimiento"
                        )
                );

                movimiento.setTipoMovimiento(
                        tipo
                );


                Producto producto =
                        new Producto();

                producto.setIdProducto(
                        rs.getInt(
                                "id_producto"
                        )
                );

                movimiento.setProducto(
                        producto
                );


                Usuario usuario =
                        new Usuario();

                usuario.setIdUsuario(
                        rs.getInt(
                                "id_usuario"
                        )
                );

                movimiento.setUsuario(
                        usuario
                );

                lista.add(movimiento);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar movimientos: "
                            + e.getMessage()
            );
        }

        return lista;
    }


    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM movimientos
                WHERE id_movimiento = ?
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
                    "Error eliminar movimiento: "
                            + e.getMessage()
            );
        }

        return false;
    }
}