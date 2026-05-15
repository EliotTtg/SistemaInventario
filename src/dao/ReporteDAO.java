package dao;

import config.ConexionBD;

import model.Reporte;
import model.Usuario;

import java.sql.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    public boolean guardar(
            Reporte reporte
    ) {

        String sql = """
                INSERT INTO reportes(
                    tipo_reporte,
                    id_usuario
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
                    reporte.getTipoReporte()
            );

            ps.setInt(
                    2,
                    reporte
                            .getUsuario()
                            .getIdUsuario()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar reporte: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public Reporte buscarPorId(
            int id
    ) {

        Reporte reporte = null;

        String sql = """
                SELECT r.*,
                       u.nombre AS usuario_nombre
                FROM reportes r
                INNER JOIN usuarios u
                    ON r.id_usuario = u.id_usuario
                WHERE r.id_reporte = ?
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

                reporte =
                        new Reporte();

                reporte.setIdReporte(
                        rs.getInt(
                                "id_reporte"
                        )
                );

                reporte.setFechaGeneracion(
                        rs.getObject(
                                "fecha_generacion",
                                LocalDateTime.class
                        )
                );

                reporte.setTipoReporte(
                        rs.getString(
                                "tipo_reporte"
                        )
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

                reporte.setUsuario(
                        usuario
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar reporte: "
                            + e.getMessage()
            );
        }

        return reporte;
    }

    public List<Reporte> listar() {

        List<Reporte> lista =
                new ArrayList<>();

        String sql = """
                SELECT r.*,
                       u.nombre AS usuario_nombre
                FROM reportes r
                INNER JOIN usuarios u
                    ON r.id_usuario = u.id_usuario
                ORDER BY r.fecha_generacion DESC
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

                Reporte reporte =
                        new Reporte();

                reporte.setIdReporte(
                        rs.getInt(
                                "id_reporte"
                        )
                );

                reporte.setFechaGeneracion(
                        rs.getObject(
                                "fecha_generacion",
                                LocalDateTime.class
                        )
                );

                reporte.setTipoReporte(
                        rs.getString(
                                "tipo_reporte"
                        )
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

                reporte.setUsuario(
                        usuario
                );

                lista.add(reporte);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar reportes: "
                            + e.getMessage()
            );
        }

        return lista;
    }

    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM reportes
                WHERE id_reporte = ?
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
                    "Error eliminar reporte: "
                            + e.getMessage()
            );
        }

        return false;
    }
}