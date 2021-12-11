package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.entities.Pasion;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;

public interface ClienteDAO extends GenericDAO<Cliente>{

    public Cliente asignarPasionCliente(Cliente cliente, Pasion pasion);
    public Cliente actualizarDatosCliente(Cliente clienteEncontrado, Cliente cliente);

}
