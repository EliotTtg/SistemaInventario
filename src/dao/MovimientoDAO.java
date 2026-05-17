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
                    id_tipo_movimiento,
                    cantidad,
                    id_producto,
                    motivo,
                    fecha,
                    id_usuario
                )
                VALUES(?,?,?,?,NOW(),?)
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    movimiento
                            .getTipoMovimiento()
                            .getIdTipoMovimiento()
            );

            ps.setInt(
                    2,
                    movimiento.getCantidad()
            );

            ps.setInt(
                    3,
                    movimiento
                            .getProducto()
                            .getIdProducto()
            );

            ps.setString(
                    4,
                    movimiento.getMotivo()
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

    public MovimientoInventario buscarPorId(
            int id
    ) {

        MovimientoInventario movimiento =
                null;

        String sql = """
                SELECT m.*,
                       p.nombre AS producto,
                       tm.nombre AS tipo_movimiento,
                       u.nombre AS usuario_nombre
                FROM movimientos m
                INNER JOIN productos p
                    ON m.id_producto = p.id_producto
                INNER JOIN tipos_movimiento tm
                    ON m.id_tipo_movimiento = tm.id_tipo_movimiento
                INNER JOIN usuarios u
                    ON m.id_usuario = u.id_usuario
                WHERE m.id_movimiento = ?
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

                movimiento =
                        new MovimientoInventario();

                movimiento.setIdMovimiento(
                        rs.getInt(
                                "id_movimiento"
                        )
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

                Timestamp timestamp =
                        rs.getTimestamp(
                                "fecha"
                        );

                if(timestamp != null){

                    movimiento.setFecha(
                            timestamp
                                    .toLocalDateTime()
                    );
                }

                TipoMovimiento tipo =
                        new TipoMovimiento();

                tipo.setIdTipoMovimiento(
                        rs.getInt(
                                "id_tipo_movimiento"
                        )
                );

                tipo.setNombre(
                        rs.getString(
                                "tipo_movimiento"
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

                producto.setNombre(
                        rs.getString(
                                "producto"
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

                usuario.setNombre(
                        rs.getString(
                                "usuario_nombre"
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
                SELECT m.*,
                       p.nombre AS producto,
                       tm.nombre AS tipo_movimiento,
                       u.nombre AS usuario_nombre
                FROM movimientos m
                INNER JOIN productos p
                    ON m.id_producto = p.id_producto
                INNER JOIN tipos_movimiento tm
                    ON m.id_tipo_movimiento = tm.id_tipo_movimiento
                INNER JOIN usuarios u
                    ON m.id_usuario = u.id_usuario
                ORDER BY m.id_movimiento DESC
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

                MovimientoInventario movimiento =
                        new MovimientoInventario();

                movimiento.setIdMovimiento(
                        rs.getInt(
                                "id_movimiento"
                        )
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

                Timestamp timestamp =
                        rs.getTimestamp(
                                "fecha"
                        );

                if(timestamp != null){

                    movimiento.setFecha(
                            timestamp
                                    .toLocalDateTime()
                    );
                }

                TipoMovimiento tipo =
                        new TipoMovimiento();

                tipo.setIdTipoMovimiento(
                        rs.getInt(
                                "id_tipo_movimiento"
                        )
                );

                tipo.setNombre(
                        rs.getString(
                                "tipo_movimiento"
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

                producto.setNombre(
                        rs.getString(
                                "producto"
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

                usuario.setNombre(
                        rs.getString(
                                "usuario_nombre"
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
}