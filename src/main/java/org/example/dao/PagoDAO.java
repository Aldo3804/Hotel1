package org.example.demo.dao;


import org.example.demo.entidades.Pago;

import java.util.List;

public interface PagoDAO {

    List<Pago> listarPago();

    boolean agregarPago(Pago pago);

    boolean eliminarPago(int id);

    boolean actualizarPago(Pago pago);

}
