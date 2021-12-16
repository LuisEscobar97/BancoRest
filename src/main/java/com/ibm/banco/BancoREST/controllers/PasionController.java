package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.entities.Pasion;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.services.PasionDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pasiones")
@ApiIgnore
public class PasionController {

    @Autowired
    private PasionDAO pasionDAO;

    @GetMapping("/pasion")
    private ResponseEntity<?> obtenerPasiones(@RequestParam(name = "id") Integer id){
        Optional<Pasion> pasion = pasionDAO.buscarPorID(id);

        if (!pasion.isPresent())
            throw new BadRequestException(String.format("La pasion con ID: %d no existe",id));

        return new ResponseEntity<Pasion>(pasion.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>guardarPasiones(@Valid @RequestBody Pasion tarjeta, BindingResult result){

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

        Pasion pasionGuardada =  pasionDAO.guardar(tarjeta);

        return new ResponseEntity<Pasion>( pasionGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?>obtenerTodosPasiones(){

        List<Pasion> lsiatPasiones= (List<Pasion>) pasionDAO.buscarTodos();
        if (lsiatPasiones.isEmpty())
            throw new BadRequestException("No hay Pasiones dadas de alta en la base de datos");

        return new ResponseEntity<List<Pasion>>( lsiatPasiones,HttpStatus.OK);


    }
    @DeleteMapping("/e")
    public ResponseEntity<?> eliminarPasion(@RequestParam(name = "id")Integer id){

        Optional<Pasion> pasionEncontrada =pasionDAO.buscarPorID(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();

        if (!pasionEncontrada.isPresent())
            throw new BadRequestException(String.format("La Pasion con ID: %d no existe",id));


        pasionDAO.eliminarPorId(id);
        respuesta.put("OK", "Pasion ID: " + id + " eliminado exitosamente{"+pasionEncontrada.get().toString()+"}");

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
    }
}
