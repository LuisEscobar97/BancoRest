package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import com.ibm.banco.BancoREST.respositories.ClienteRepository;
import com.ibm.banco.BancoREST.respositories.TarjetaPasionRespository;
import org.springframework.stereotype.Service;

@Service
public class ClienteDAOImpl extends GenericDAOImpl<Cliente, ClienteRepository> implements ClienteDAO {
    public ClienteDAOImpl(ClienteRepository repository) {
        super(repository);
    }
}
