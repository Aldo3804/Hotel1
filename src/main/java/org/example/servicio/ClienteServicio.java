package org.example.demo.servicio;

import org.example.demo.dao.ClienteDAO;
import org.example.demo.entidades.Cliente;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteServicio {

    private final ClienteDAO clienteDAO;

    public ClienteServicio(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean guardarCliente(Cliente cliente) {

        if(cliente.getIdpersona()==0){
            return clienteDAO.agregarCliente(cliente);
        }
        else {
            return clienteDAO.actualizarCliente(cliente);
        }

    }

    public boolean eliminarCliente(Cliente cliente) {
        return clienteDAO.eliminarCliente(cliente);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarClientes();
    }



}
