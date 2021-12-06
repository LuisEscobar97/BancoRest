package com.ibm.banco.BancoREST.services;

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

}
