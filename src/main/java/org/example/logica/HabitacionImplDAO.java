package org.example.demo.logica;

import org.example.demo.conexion.Conexion;
import org.example.demo.dao.HabitacionDAO;
import org.example.demo.entidades.Habitacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionImplDAO implements HabitacionDAO {

    @Override
    public List<Habitacion> listarHabitacion() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT idHabitacion,numero, piso, detalles, precio_diario, estado, tipo_habitacion FROM habitacion";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setIdhabitacion(rs.getInt("idHabitacion"));
                h.setNumero(rs.getString("numero"));
                h.setPiso(rs.getString("piso"));
                h.setDetalles(rs.getString("detalles"));
                h.setPrecio_diario(rs.getDouble("precio_diario"));
                h.setEstado(rs.getString("estado"));
                h.setTipo_habitacion(rs.getString("tipo_habitacion"));
                lista.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean agregarHabitacion(Habitacion habitacion) {
        String sql = "INSERT INTO habitacion (numero, piso, detalles, precio_diario, estado, tipo_habitacion) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, habitacion.getNumero());
            stmt.setString(2, habitacion.getPiso());
            stmt.setString(3, habitacion.getDetalles());
            stmt.setDouble(4, habitacion.getPrecio_diario());
            stmt.setString(5, habitacion.getEstado());
            stmt.setString(6, habitacion.getTipo_habitacion());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean eliminarHabitacion(Habitacion habitacion) {
        String sql = "DELETE FROM habitacion WHERE idhabitacion = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, habitacion.getIdhabitacion());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean actualizarHabitacion(Habitacion habitacion) {
        String sql = "UPDATE habitacion SET numero = ?, piso = ?, detalles = ?, precio_diario = ?, estado = ?, tipo_habitacion = ? WHERE idhabitacion = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, habitacion.getNumero());
            stmt.setString(2, habitacion.getPiso());
            stmt.setString(3, habitacion.getDetalles());
            stmt.setDouble(4, habitacion.getPrecio_diario());
            stmt.setString(5, habitacion.getEstado());
            stmt.setString(6, habitacion.getTipo_habitacion());
            stmt.setInt(7, habitacion.getIdhabitacion());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
