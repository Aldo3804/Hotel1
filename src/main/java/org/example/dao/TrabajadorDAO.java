package org.example.dao;





import org.example.entidades.Persona;
import org.example.entidades.Trabajador;

import java.util.List;

public interface TrabajadorDAO {

    List<Trabajador> listarTrabajador();

    Persona buscarTrabajadorPorId(int id);

    Trabajador iniciarSesion(String login,String password);
}
