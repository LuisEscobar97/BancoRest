package com.ibm.banco.BancoREST;

import com.ibm.banco.BancoREST.services.ClienteDAO;
import com.ibm.banco.BancoREST.services.TarjetaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Comandos implements CommandLineRunner {


    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private TarjetaDAO tarjetaDAO;


    @Override
    public void run(String... args) throws Exception {
        
    }
}
