package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.respositories.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaDAOImpl extends GenericDAOImpl<Tarjeta, TarjetaRepository> implements TarjetaDAO{
    public TarjetaDAOImpl(TarjetaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Tarjeta> findTarjetasPorPasionEdadAndSalario(Integer edad, Integer salario, String pasion) {
        return repository.findTarjetasPorPasionEdadAndSalario(edad,salario,pasion);
    }
}
