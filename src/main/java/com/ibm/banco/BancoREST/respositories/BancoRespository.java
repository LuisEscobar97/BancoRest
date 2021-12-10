package com.ibm.banco.BancoREST.respositories;

import com.ibm.banco.BancoREST.entities.Banco;
import org.springframework.data.repository.CrudRepository;

public interface BancoRespository extends CrudRepository<Banco,Integer> {

}
