package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import com.ibm.banco.BancoREST.respositories.TarjetaPasionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaPasionService {
    @Autowired
    TarjetaPasionRespository tarjetaPasionRespository;

    public Optional<List<TarjetaPasion>> getTarjetaPorPasion(Integer edad, Integer salario, String pasion){
        return tarjetaPasionRespository.getTarjetasPorPasionEdadAndSalario(edad,salario,pasion);
    }

}
