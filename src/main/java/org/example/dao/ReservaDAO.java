package org.example.dao;




import org.example.entidades.Reserva;

import java.util.List;

public interface ReservaDAO {

    List<Reserva> listarReserva();

    boolean agregarReserva(Reserva reserva);

    boolean eliminarReserva(int id);

    boolean actualizarReserva(Reserva reserva);


}
