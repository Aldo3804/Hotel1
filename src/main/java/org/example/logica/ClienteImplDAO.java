package org.example.logica;



import org.example.conexion.Conexion;
import org.example.dao.ClienteDAO;
import org.example.entidades.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteImplDAO implements ClienteDAO {


    @Override
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT p.*, c.codigo_cliente " +
                "FROM persona p " +
                "JOIN cliente c ON p.idpersona = c.idpersona " +
                "ORDER BY p.nombre ASC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdpersona(rs.getInt("idpersona"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTipo_documento(rs.getString("tipo_documento"));
                c.setNum_documento(rs.getString("num_documento"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setEmail(rs.getString("email"));
                c.setCodigo_cliente(rs.getString("codigo_cliente"));
                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return clientes;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        String sqlPersona = "INSERT INTO persona (nombre, apellidos, tipo_documento, num_documento, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlCliente = "INSERT INTO cliente (idpersona, codigo_cliente) VALUES (?, ?)";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            // Insertar en tabla persona
            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psPersona.setString(1, cliente.getNombre());
                psPersona.setString(2, cliente.getApellidos());
                psPersona.setString(3, cliente.getTipo_documento());
                psPersona.setString(4, cliente.getNum_documento());
                psPersona.setString(5, cliente.getDireccion());
                psPersona.setString(6, cliente.getTelefono());
                psPersona.setString(7, cliente.getEmail());

                int rows = psPersona.executeUpdate();
                if (rows == 0) {
                    con.rollback();
                    return false;
                }

                // Obtener el id generado
                ResultSet generatedKeys = psPersona.getGeneratedKeys();
                if (generatedKeys.next()) {
                    cliente.setIdpersona(generatedKeys.getInt(1));
                } else {
                    con.rollback();
                    return false;
                }
            }

            // Insertar en tabla cliente
            try (PreparedStatement psCliente = con.prepareStatement(sqlCliente)) {
                psCliente.setInt(1, cliente.getIdpersona());
                psCliente.setString(2, cliente.getCodigo_cliente());
                psCliente.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        String sqlCliente = "DELETE FROM cliente WHERE idpersona = ?";
        String sqlPersona = "DELETE FROM persona WHERE idpersona = ?";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement psCliente = con.prepareStatement(sqlCliente)) {
                psCliente.setInt(1, cliente.getIdpersona());
                psCliente.executeUpdate();
            }

            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona)) {
                psPersona.setInt(1, cliente.getIdpersona());
                psPersona.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        String sqlPersona = "UPDATE persona SET nombre = ?, apellidos = ?, tipo_documento = ?, num_documento = ?, direccion = ?, telefono = ?, email = ? WHERE idpersona = ?";
        String sqlCliente = "UPDATE cliente SET codigo_cliente = ? WHERE idpersona = ?";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona)) {
                psPersona.setString(1, cliente.getNombre());
                psPersona.setString(2, cliente.getApellidos());
                psPersona.setString(3, cliente.getTipo_documento());
                psPersona.setString(4, cliente.getNum_documento());
                psPersona.setString(5, cliente.getDireccion());
                psPersona.setString(6, cliente.getTelefono());
                psPersona.setString(7, cliente.getEmail());
                psPersona.setInt(8, cliente.getIdpersona());
                psPersona.executeUpdate();
            }

            try (PreparedStatement psCliente = con.prepareStatement(sqlCliente)) {
                psCliente.setString(1, cliente.getCodigo_cliente());
                psCliente.setInt(2, cliente.getIdpersona());
                psCliente.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
