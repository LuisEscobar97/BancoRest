package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Pasion;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarjetaPasionDAO extends GenericDAO<TarjetaPasion> {

        public TarjetaPasion asignarPasionYTarjeta(TarjetaPasion tarjetaPasion, Tarjeta tarjeta, Pasion pasion);
}
