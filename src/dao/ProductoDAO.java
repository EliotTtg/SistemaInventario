package dao;

import config.ConexionBD;

import model.Categoria;
import model.Producto;
import model.Proveedor;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public boolean existeCodigo(
            String codigo
    ) {

        String sql = """
                SELECT id_producto
                FROM productos
                WHERE codigo = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(1, codigo);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Error validar codigo: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean existeCodigoEditar(

            String codigo,

            int idProducto
    ) {

        String sql = """
                SELECT id_producto
                FROM productos
                WHERE codigo = ?
                AND id_producto != ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(1, codigo);

            ps.setInt(2, idProducto);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Error validar codigo editar: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean guardar(
            Producto producto
    ) {

        String sql = """
                INSERT INTO productos(
                    codigo,
                    nombre,
                    descripcion,
                    stock_actual,
                    stock_minimo,
                    precio,
                    id_categoria,
                    id_proveedor
                )
                VALUES(?,?,?,?,?,?,?,?)
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    producto.getCodigo()
            );

            ps.setString(
                    2,
                    producto.getNombre()
            );

            ps.setString(
                    3,
                    producto.getDescripcion()
            );

            ps.setInt(
                    4,
                    producto.getStockActual()
            );

            ps.setInt(
                    5,
                    producto.getStockMinimo()
            );

            ps.setDouble(
                    6,
                    producto.getPrecio()
            );

            ps.setInt(
                    7,
                    producto
                            .getCategoria()
                            .getIdCategoria()
            );

            ps.setInt(
                    8,
                    producto
                            .getProveedor()
                            .getIdProveedor()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error guardar producto: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean actualizar(
            Producto producto
    ) {

        String sql = """
                UPDATE productos
                SET codigo = ?,
                    nombre = ?,
                    descripcion = ?,
                    stock_actual = ?,
                    stock_minimo = ?,
                    precio = ?,
                    id_categoria = ?,
                    id_proveedor = ?
                WHERE id_producto = ?
                """;

        try (

                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    producto.getCodigo()
            );

            ps.setString(
                    2,
                    producto.getNombre()
            );

            ps.setString(
                    3,
                    producto.getDescripcion()
            );

            ps.setInt(
                    4,
                    producto.getStockActual()
            );

            ps.setInt(
                    5,
                    producto.getStockMinimo()
            );

            ps.setDouble(
                    6,
                    producto.getPrecio()
            );

            ps.setInt(
                    7,
                    producto
                            .getCategoria()
                            .getIdCategoria()
            );

            ps.setInt(
                    8,
                    producto
                            .getProveedor()
                            .getIdProveedor()
            );

            ps.setInt(
                    9,
                    producto.getIdProducto()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error actualizar producto: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM productos
                WHERE id_producto = ?
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
                    "Error eliminar producto: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public Producto buscarPorId(
            int id
    ) {

        Producto producto = null;

        String sql = """
                SELECT *
                FROM productos
                WHERE id_producto = ?
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

                producto = new Producto();

                producto.setIdProducto(
                        rs.getInt("id_producto")
                );

                producto.setCodigo(
                        rs.getString("codigo")
                );

                producto.setNombre(
                        rs.getString("nombre")
                );

                producto.setDescripcion(
                        rs.getString("descripcion")
                );

                producto.setStockActual(
                        rs.getInt("stock_actual")
                );

                producto.setStockMinimo(
                        rs.getInt("stock_minimo")
                );

                producto.setPrecio(
                        rs.getDouble("precio")
                );

                Categoria categoria =
                        new Categoria();

                categoria.setIdCategoria(
                        rs.getInt("id_categoria")
                );

                producto.setCategoria(
                        categoria
                );

                Proveedor proveedor =
                        new Proveedor();

                proveedor.setIdProveedor(
                        rs.getInt("id_proveedor")
                );

                producto.setProveedor(
                        proveedor
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error buscar producto: "
                            + e.getMessage()
            );
        }

        return producto;
    }

    public List<Producto> listar() {

        List<Producto> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM productos
                ORDER BY id_producto DESC
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

                Producto producto =
                        new Producto();

                producto.setIdProducto(
                        rs.getInt("id_producto")
                );

                producto.setCodigo(
                        rs.getString("codigo")
                );

                producto.setNombre(
                        rs.getString("nombre")
                );

                producto.setDescripcion(
                        rs.getString("descripcion")
                );

                producto.setStockActual(
                        rs.getInt("stock_actual")
                );

                producto.setStockMinimo(
                        rs.getInt("stock_minimo")
                );

                producto.setPrecio(
                        rs.getDouble("precio")
                );

                Categoria categoria =
                        new Categoria();

                categoria.setIdCategoria(
                        rs.getInt("id_categoria")
                );

                producto.setCategoria(
                        categoria
                );

                Proveedor proveedor =
                        new Proveedor();

                proveedor.setIdProveedor(
                        rs.getInt("id_proveedor")
                );

                producto.setProveedor(
                        proveedor
                );

                lista.add(producto);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar productos: "
                            + e.getMessage()
            );
        }

        return lista;
    }
}