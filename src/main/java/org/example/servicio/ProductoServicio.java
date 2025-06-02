package org.example.servicio;


import org.example.dao.ProductoDAO;
import org.example.entidades.Producto;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoServicio {

    private final ProductoDAO productoDAO;

    public ProductoServicio(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public boolean guardarProducto(Producto producto) throws SQLException {
        if (producto.getIdproducto() == 0) {
            return productoDAO.agregarProducto(producto);
        } else {
            return productoDAO.actualizarProducto(producto);
        }

    }

    public boolean eliminarProducto(Producto producto) throws SQLException {
        return productoDAO.eliminarProducto(producto);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listarProducto().stream().sorted(
                        Comparator.comparing(Producto::getNombre))
                .collect(Collectors.toList());
    }


}
