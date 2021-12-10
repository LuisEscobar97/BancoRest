package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.entities.Banco;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.services.TarjetaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaDAO tarjetaDAO;

    @GetMapping("/tarjeta")
    private ResponseEntity<?> obtenerTarjetas(@RequestParam(name = "id") Integer id){
        Optional<Tarjeta> tarjeta = tarjetaDAO.buscarPorID(id);

        if (!tarjeta.isPresent())
            throw new BadRequestException(String.format("El tarjeta con ID: %d no existe",id));

        return new ResponseEntity<Tarjeta>(tarjeta.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>guardarTarjetas(@Valid @RequestBody Tarjeta tarjeta, BindingResult result){

        Map<String, Object> validaciones = new HashMap<String, Object>();
        if (result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }

        Tarjeta tarjetaGuardada =  tarjetaDAO.guardar(tarjeta);

        return new ResponseEntity<Tarjeta>( tarjetaGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?>obtenerTodosTarjetas(){

        List<Tarjeta> lsiatTarjetas= (List<Tarjeta>) tarjetaDAO.buscarTodos();
        if (lsiatTarjetas.isEmpty())
            throw new BadRequestException("No hay tarjetas dadas de alta en la base de datos");

        return new ResponseEntity<List<Tarjeta>>( lsiatTarjetas,HttpStatus.OK);


    }
    @DeleteMapping("/e")
    public ResponseEntity<?> eliminarTarjetas(@RequestParam(name = "id")Integer id){

        Optional<Tarjeta> tarjetaEncontrado =tarjetaDAO.buscarPorID(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();

        if (!tarjetaEncontrado.isPresent())
            throw new BadRequestException(String.format("El tarjeta con ID: %d no existe",id));


        tarjetaDAO.eliminarPorId(id);
        respuesta.put("OK", "Tarjeta ID: " + id + " eliminado exitosamente{"+tarjetaEncontrado.get().toString()+"}");

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
    }
}
