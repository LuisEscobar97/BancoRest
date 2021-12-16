package com.ibm.banco.BancoREST.respositories;

import com.ibm.banco.BancoREST.entities.Tarjeta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TarjetaRespositoryTest {

    @Autowired
    private TarjetaRepository tarjetaRespository;

    @Test
    @DisplayName("Test: Buscar todas la tarjetas")
    void buscarTodasLasTarjetas(){
        List<Tarjeta>expected= (List<Tarjeta>) tarjetaRespository.findAll();

        assertThat(expected.size() ==9).isTrue();
    }
}
