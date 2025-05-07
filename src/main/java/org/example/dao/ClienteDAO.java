package org.example.dao;



import org.example.entidades.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    List<Cliente> listarClientes() throws SQLException;

    boolean agregarCliente(Cliente cliente);

    boolean eliminarCliente(Cliente cliente);

    boolean actualizarCliente(Cliente cliente);

}
