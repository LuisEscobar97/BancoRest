package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import com.ibm.banco.BancoREST.services.TarjetaPasionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaPasionController {

    @Autowired
    private TarjetaPasionService tarjetaPasionService;

    @GetMapping("/{edad}/{salario}/{pasion}")
    public Optional<List<TarjetaPasion>>getTarjetasPorPasion(@PathVariable("edad") Integer edad,@PathVariable("salario") Integer salario,@PathVariable("pasion") String pasion){
        return  tarjetaPasionService.getTarjetaPorPasion(edad,salario,pasion);
    }

}
