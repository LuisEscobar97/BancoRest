package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Banco;
import com.ibm.banco.BancoREST.entities.Tarjeta;

public interface BancoDAO extends GenericDAO<Banco>{

    public Banco asignarTarjetaBanco(Tarjeta tarjeta,Banco banco);
}
