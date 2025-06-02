package org.example.dao;




import org.example.entidades.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {

    List<Producto> listarProducto();

    boolean agregarProducto(Producto producto) throws SQLException;

    boolean eliminarProducto(Producto producto) throws SQLException;

    boolean actualizarProducto(Producto producto);

}
