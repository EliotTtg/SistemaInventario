package dao;

import config.ConexionBD;

import model.Rol;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class RolDAO {

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

            while(rs.next()){

                Rol rol =
                        new Rol();

                rol.setIdRol(
                        rs.getInt("id_rol")
                );

                rol.setNombre(
                        rs.getString("nombre")
                );

                rol.setDescripcion(
                        rs.getString("descripcion")
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