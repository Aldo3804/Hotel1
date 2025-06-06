package org.example.servicio;

import org.example.dao.TrabajadorDAO;
import org.example.entidades.Persona;
import org.example.entidades.Trabajador;

import java.sql.SQLException;

public class TrabajadorServicio {

    private final TrabajadorDAO trabajadorDAO;

    public TrabajadorServicio(TrabajadorDAO trabajadorDAO) {
        this.trabajadorDAO = trabajadorDAO;
    }

    public Trabajador iniciarSesion(String login,String password) throws SQLException {
        return trabajadorDAO.iniciarSesion(login,password);
    }


    public Persona buscarPorId(int id) throws SQLException {
        return trabajadorDAO.buscarTrabajadorPorId(id);
    }
}
