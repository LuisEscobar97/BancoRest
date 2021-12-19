package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.dto.TarjetaDTO;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.exceptions.NotFoundException;
import com.ibm.banco.BancoREST.mapper.TarjetaMapper;
import com.ibm.banco.BancoREST.respositories.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarjetaDAOImpl extends GenericDAOImpl<Tarjeta, TarjetaRepository> implements TarjetaDAO{
    public TarjetaDAOImpl(TarjetaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<TarjetaDTO> findTarjetasPorPasionEdadAndSalario(Integer edad, Integer salario, String pasion) {
        List<TarjetaDTO>tarjetaDTOS=null;
        List<Tarjeta> tarjetas= (List<Tarjeta>) repository.findTarjetasPorPasionEdadAndSalario(edad,salario,pasion);
        if (tarjetas.isEmpty())
            throw new NotFoundException("No se encontraron recomedaciones para su perfil");
        tarjetaDTOS=tarjetas.stream()
                .map(TarjetaMapper::mapTarjeta)
                .collect(Collectors.toList());
        return tarjetaDTOS;
    }

}
