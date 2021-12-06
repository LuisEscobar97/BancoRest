package com.ibm.banco.BancoREST.respositories;

import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarjetaPasionRespository extends CrudRepository<TarjetaPasion,Integer> {



}
