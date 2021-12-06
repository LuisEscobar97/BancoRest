package com.ibm.banco.BancoREST.respositories;

import com.ibm.banco.BancoREST.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

}
