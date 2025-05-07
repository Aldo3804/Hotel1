package org.example.servicio;



import org.example.dao.HabitacionDAO;
import org.example.entidades.Habitacion;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HabitacionServicio {

    private final HabitacionDAO habitacionDAO;

    public HabitacionServicio(HabitacionDAO habitacionDAO) {
        this.habitacionDAO = habitacionDAO;
    }

    public boolean guardar(Habitacion habitacion) throws SQLException {

        if(habitacion.getIdhabitacion()==0){
            return habitacionDAO.agregarHabitacion(habitacion);
        }else{
            return habitacionDAO.actualizarHabitacion(habitacion);
        }

    }

    public boolean eliminar(Habitacion habitacion) throws SQLException {
        return habitacionDAO.eliminarHabitacion(habitacion);
    }

    public List<Habitacion> listar() throws SQLException {
        return habitacionDAO.listarHabitacion().stream().sorted(
                        Comparator.comparing(Habitacion::getPiso))
                .collect(Collectors.toList());
    }


}
