package org.example.logica;

import org.example.conexion.Conexion;
import org.example.dao.TrabajadorDAO;
import org.example.entidades.Persona;
import org.example.entidades.Trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrabajadorImplDAO implements TrabajadorDAO {

    @Override
    public List<Trabajador> listarTrabajador() {
        return List.of();
    }

    @Override
    public Persona buscarTrabajadorPorId(int id) {

        String sql = "SELECT nombre,apellidos FROM Persona WHERE idPersona = ?";

        try(Connection conn = Conexion.getConexion()){

            PreparedStatement smtm = conn.prepareStatement(sql);
            smtm.setInt(1, id);
            ResultSet rs = smtm.executeQuery();

            if(rs.next()){
                Persona persona =  new Persona();
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidos(rs.getString("apellidos"));
                return persona;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public Trabajador iniciarSesion(String login, String password) {

        String sql = "select * from trabajador where login = ? and password = ? ";
        try(Connection conn = Conexion.getConexion()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, login);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setIdpersona(rs.getInt("idpersona"));
                trabajador.setSueldo(rs.getDouble("sueldo"));
                trabajador.setAcceso(rs.getString("acceso"));
                trabajador.setLogin(rs.getString("login"));
                trabajador.setPassword(rs.getString("password"));
                trabajador.setEstado(rs.getString("estado"));

                return trabajador;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
