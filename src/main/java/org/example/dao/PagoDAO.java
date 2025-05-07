package org.example.dao;




import org.example.entidades.Pago;

import java.util.List;

public interface PagoDAO {

    List<Pago> listarPago();

    boolean agregarPago(Pago pago);

    boolean eliminarPago(int id);

    boolean actualizarPago(Pago pago);

}
