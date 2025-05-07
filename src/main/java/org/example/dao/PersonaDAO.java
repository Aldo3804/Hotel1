package org.example.dao;




import org.example.entidades.Persona;

import java.util.List;

public interface PersonaDAO {

    List<Persona> listarPersona();

    boolean agregarPersona(Persona persona);

    boolean eliminarPersona(int id);

    boolean actualizarPersona(Persona persona);

}
