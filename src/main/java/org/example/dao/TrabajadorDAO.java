package org.example.dao;





import org.example.entidades.Trabajador;

import java.util.List;

public interface TrabajadorDAO {

    List<Trabajador> listarTrabajador();

    boolean agregarTrabajador(Trabajador trabajador);

    boolean eliminarTrabajador(int id);

    boolean actualizarPersona(Trabajador trabajador);

}
