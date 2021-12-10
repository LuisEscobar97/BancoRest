package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Banco;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.respositories.BancoRespository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BancoDAOImpl extends GenericDAOImpl<Banco,BancoRespository> implements BancoDAO{
    public BancoDAOImpl(BancoRespository repository) {
        super(repository);
    }

    @Override
    public Banco asignarTarjetaBanco(Tarjeta tarjeta, Banco banco) {
        Set<Tarjeta> tarjetas= banco.getTarjetas();
        tarjetas.add(tarjeta);
        banco.setTarjetas(tarjetas);
        return repository.save(banco);
    }
}
