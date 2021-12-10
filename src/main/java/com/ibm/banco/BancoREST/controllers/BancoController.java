package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.entities.Banco;
import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.services.BancoDAO;
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
@RequestMapping("/api/bancos")
public class BancoController {
    @Autowired
    private BancoDAO bancoDAO;

    @GetMapping("/banco")
    private ResponseEntity<?> obtenerBanco(@RequestParam(name = "id") Integer id){
        Optional<Banco> banco = bancoDAO.buscarPorID(id);

        if (!banco.isPresent())
            throw new BadRequestException(String.format("El Banco con ID: %d no existe",id));

        return new ResponseEntity<Banco>(banco.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>guardarBanco(@Valid @RequestBody Banco banco, BindingResult result){

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

        Banco bancoGuardado =  bancoDAO.guardar(banco);

        return new ResponseEntity<Banco>( bancoGuardado, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?>obtenerTodosBancos(){

        List<Banco> lsiatBancos= (List<Banco>) bancoDAO.buscarTodos();
        if (lsiatBancos.isEmpty())
            throw new BadRequestException("No hay bancos dados de alta en la base de datos");

        return new ResponseEntity<List<Banco>>( lsiatBancos,HttpStatus.OK);


    }
    @DeleteMapping("/e")
    public ResponseEntity<?> eliminarBanco(@RequestParam(name = "id")Integer id){

        Optional<Banco> bancoEncontrado =bancoDAO.buscarPorID(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();

        if (!bancoEncontrado.isPresent())
            throw new BadRequestException(String.format("El banco con ID: %d no existe",id));


        bancoDAO.eliminarPorId(id);
        respuesta.put("OK", "Banco ID: " + id + " eliminado exitosamente{"+bancoEncontrado.get().toString()+"}");

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
    }
}
