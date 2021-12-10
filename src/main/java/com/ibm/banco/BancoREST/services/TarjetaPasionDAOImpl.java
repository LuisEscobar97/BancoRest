package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Pasion;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import com.ibm.banco.BancoREST.respositories.TarjetaPasionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaPasionDAOImpl extends GenericDAOImpl<TarjetaPasion,TarjetaPasionRespository>implements  TarjetaPasionDAO {

    public TarjetaPasionDAOImpl(TarjetaPasionRespository respository) {
        super(respository);
    }

    @Override
    public TarjetaPasion asignarPasionYTarjeta(TarjetaPasion tarjetaPasion, Tarjeta tarjeta, Pasion pasion) {
        TarjetaPasion tarjetaPasionActualizada= null;
        tarjetaPasion.setPasion(pasion);
        tarjetaPasion.setTarjeta(tarjeta);
        tarjetaPasionActualizada=repository.save(tarjetaPasion);

        return  tarjetaPasionActualizada;
    }
}
