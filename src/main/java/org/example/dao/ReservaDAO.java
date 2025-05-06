package org.example.demo.dao;


import org.example.demo.entidades.Reserva;

import java.util.List;

public interface ReservaDAO {

    List<Reserva> listarReserva();

    boolean agregarReserva(Reserva reserva);

    boolean eliminarReserva(int id);

    boolean actualizarReserva(Reserva reserva);


}
