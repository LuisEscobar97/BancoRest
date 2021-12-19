package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.entities.Pasion;
import com.ibm.banco.BancoREST.respositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteDAOImpl extends GenericDAOImpl<Cliente, ClienteRepository> implements ClienteDAO {
    public ClienteDAOImpl(ClienteRepository repository) {
        super(repository);
    }

    @Override
    public Cliente asignarPasionCliente(Cliente cliente, Pasion pasion) {
        Cliente clienteActualizado=null;
        cliente.setPasion(pasion);
        clienteActualizado=repository.save(cliente);
        return clienteActualizado;
    }

    @Override
    public Cliente actualizarDatosCliente(Cliente clienteEncontrado, Cliente cliente) {
        Cliente clienteActualizado=null;
        clienteEncontrado.setApellido(cliente.getApellido());
        clienteEncontrado.setEdad(cliente.getEdad());
        clienteEncontrado.setNombre(cliente.getNombre());
        clienteEncontrado.setSueldo(cliente.getSueldo());
        clienteActualizado=repository.save(clienteEncontrado);
        return clienteActualizado;
    }
}
