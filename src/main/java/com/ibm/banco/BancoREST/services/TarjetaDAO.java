package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.dto.TarjetaDTO;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarjetaDAO extends GenericDAO<Tarjeta>{
    public Iterable<TarjetaDTO> findTarjetasPorPasionEdadAndSalario(Integer edad, Integer salario, String pasion);

}
