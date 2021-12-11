package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.services.TarjetaPasionDAO;
import com.ibm.banco.BancoREST.services.TarjetaPasionDAOImpl;
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
@RequestMapping("/api/tarjetas-pasiones")
public class TarjetaPasionController {

    @Autowired
    private TarjetaPasionDAO tarjetaPasionDAO;

    @GetMapping("/t-p")
    private ResponseEntity<?> obtenerTarjetasPasion(@RequestParam(name = "id") Integer id){
        Optional<TarjetaPasion> tarjetaPasion = tarjetaPasionDAO.buscarPorID(id);

        if (!tarjetaPasion.isPresent())
            throw new BadRequestException(String.format("La recomendacion de tarjeta con ID: %d no existe",id));

        return new ResponseEntity<TarjetaPasion>(tarjetaPasion.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>guardarTarjetasPasion(@Valid @RequestBody TarjetaPasion tarjetaPasion, BindingResult result){

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

        TarjetaPasion tarjetaGuardada =  tarjetaPasionDAO.guardar(tarjetaPasion);

        return new ResponseEntity<TarjetaPasion>( tarjetaGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?>obtenerTodosTarjetasPasion(){

        List<TarjetaPasion> lsiatTarjetasPasion= (List<TarjetaPasion>) tarjetaPasionDAO.buscarTodos();
        if (lsiatTarjetasPasion.isEmpty())
            throw new BadRequestException("No hay recomendacion de tarjeta dadas de alta en la base de datos");

        return new ResponseEntity<List<TarjetaPasion>>( lsiatTarjetasPasion,HttpStatus.OK);


    }
    @DeleteMapping("/e")
    public ResponseEntity<?> eliminarTarjetasPasion(@RequestParam(name = "id")Integer id){

        Optional<TarjetaPasion> tarjetaPasionEncontrado =tarjetaPasionDAO.buscarPorID(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();

        if (!tarjetaPasionEncontrado.isPresent())
            throw new BadRequestException(String.format("la recomendacion de tarjeta con ID: %d no existe",id));


        tarjetaPasionDAO.eliminarPorId(id);
        respuesta.put("OK", "recomendacion de tarjeta ID: " + id + " eliminado exitosamente{"+tarjetaPasionEncontrado.get().toString()+"}");

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
    }


}
