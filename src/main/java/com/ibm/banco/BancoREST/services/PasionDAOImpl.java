package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Pasion;
import com.ibm.banco.BancoREST.respositories.PasionRepository;
import org.springframework.stereotype.Service;

@Service
public class PasionDAOImpl extends GenericDAOImpl<Pasion, PasionRepository> implements PasionDAO{
    public PasionDAOImpl(PasionRepository repository) {
        super(repository);
    }
}
