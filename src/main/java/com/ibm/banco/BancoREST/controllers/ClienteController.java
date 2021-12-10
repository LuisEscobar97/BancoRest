package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.services.ClienteDAO;
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
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/cliente")
    private ResponseEntity<?>obtenerCliente(@RequestParam(name = "id") Integer id){
        Optional<Cliente> cliente = clienteDAO.buscarPorID(id);

        if (!cliente.isPresent())
            throw new BadRequestException(String.format("El Cliente con ID: %d no existe",id));

        return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>guardarCliente(@Valid @RequestBody Cliente cliente, BindingResult result){

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

        Cliente clienteGuardado =  clienteDAO.guardar(cliente);

        return new ResponseEntity<Cliente>( clienteGuardado, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?>obtenerTodosClientes(){

        List<Cliente> lsiatClientes= (List<Cliente>) clienteDAO.buscarTodos();
        if (lsiatClientes.isEmpty())
            throw new BadRequestException("No hay clientes dados de alta en la base de datos");

        return new ResponseEntity<List<Cliente>>( lsiatClientes,HttpStatus.OK);


    }
    @DeleteMapping("/e")
    public ResponseEntity<?> eliminarclientes(@RequestParam(name = "id")Integer id){

        Optional<Cliente> alumnoencontrado =clienteDAO.buscarPorID(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();

        if (!alumnoencontrado.isPresent())
            throw new BadRequestException(String.format("El cliente con ID: %d no existe",id));


        clienteDAO.eliminarPorId(id);
        respuesta.put("OK", "Cliente ID: " + id + " eliminado exitosamente{"+alumnoencontrado.get().toString()+"}");

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
    }


}
