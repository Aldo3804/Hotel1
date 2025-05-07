package org.example.dao;




import org.example.entidades.Producto;

import java.util.List;

public interface ProductoDAO {

    List<Producto> listarProducto();

    boolean agregarProducto(Producto producto);

    boolean eliminarProducto(Producto producto);

    boolean actualizarProducto(Producto producto);

}
