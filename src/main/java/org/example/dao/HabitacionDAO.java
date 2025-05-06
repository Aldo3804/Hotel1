package org.example.demo.dao;


import org.example.demo.entidades.Habitacion;

import java.sql.SQLException;
import java.util.List;

public interface HabitacionDAO {

    List<Habitacion> listarHabitacion();

    boolean agregarHabitacion(Habitacion habitacion) throws SQLException;

    boolean eliminarHabitacion(Habitacion habitacion) throws SQLException;

    boolean actualizarHabitacion(Habitacion habitacion);

}
