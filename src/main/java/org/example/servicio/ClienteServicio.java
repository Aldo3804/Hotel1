package org.example.servicio;


import org.example.dao.ClienteDAO;
import org.example.entidades.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteServicio {

    private final ClienteDAO clienteDAO;

    public ClienteServicio(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean guardarCliente(Cliente cliente) throws SQLException {

        if(cliente.getIdpersona()==0){
            return clienteDAO.agregarCliente(cliente);
        }
        else {
            return clienteDAO.actualizarCliente(cliente);
        }

    }

    public boolean eliminarCliente(Cliente cliente) throws SQLException {

        return clienteDAO.eliminarCliente(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }



}
