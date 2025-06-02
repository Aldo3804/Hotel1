package org.example.logica;

import org.example.conexion.Conexion;
import org.example.dao.ProductoDAO;
import org.example.entidades.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoImplDAO implements ProductoDAO {

    @Override
    public List<Producto> listarProducto() {

        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT idproducto,nombre,descripcion,unidad_medida,precio_venta from producto";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
               Producto p = new Producto();
               p.setIdproducto(rs.getInt("idproducto"));
               p.setNombre(rs.getString("nombre"));
               p.setDescripcion(rs.getString("descripcion"));
               p.setUnidad_medida(rs.getString("unidad_medida"));
               p.setPrecio_venta(rs.getDouble("precio_venta"));
               lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, unidad_medida, precio_venta) VALUES ( ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getUnidad_medida());
            stmt.setDouble(4, producto.getPrecio_venta());


            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean eliminarProducto(Producto producto) {

        String sql = "DELETE FROM producto WHERE idproducto = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, producto.getIdproducto());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, unidad_medida = ?, precio_venta = ? WHERE idproducto = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getUnidad_medida());
            stmt.setDouble(4, producto.getPrecio_venta());
            stmt.setInt(5, producto.getIdproducto());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
