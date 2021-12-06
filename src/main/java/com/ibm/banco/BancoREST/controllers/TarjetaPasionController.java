package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import com.ibm.banco.BancoREST.services.TarjetaPasionDAOImpl;
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
    private TarjetaPasionDAOImpl tarjetaPasionService;



}
