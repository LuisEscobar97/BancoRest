package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Tarjeta;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarjetaDAO extends GenericDAO<Tarjeta>{
    public Iterable<Tarjeta> findTarjetasPorPasionEdadAndSalario(Integer edad,Integer salario,String pasion);
}
