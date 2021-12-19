package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.dto.TarjetaDTO;
import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.exceptions.NotFoundException;
import com.ibm.banco.BancoREST.mapper.TarjetaMapper;
import com.ibm.banco.BancoREST.services.ClienteDAO;
import com.ibm.banco.BancoREST.services.TarjetaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/clientes")
@Api(value = "Clientes", description = "REST API para Clientes", tags = { "Clientes" })
public class ClienteController {
    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private TarjetaDAO tarjetaDAO;

    Logger logger = LoggerFactory.getLogger(ClienteController.class);


    /**
     * end created to get an especific client from the data base
     * @param id of a client is wanted to search
     * @return a card if exist
     */
    @ApiIgnore
    @GetMapping("/cliente")
    private ResponseEntity<?>obtenerCliente(@RequestParam(name = "id") Integer id){
        Optional<Cliente> cliente = clienteDAO.buscarPorID(id);

        if (!cliente.isPresent())
            throw new BadRequestException(String.format("El Cliente con ID: %d no existe",id));

        return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
    }

    /**
     * end point created for upload to the data base a new client
     * @param cliente json object with all data to can create a card
     * @param result message of exception handler when there is an error whit JSON Object data
     * @return new Card
     */
    @ApiIgnore
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
    /**
     * end point created for list all clients from the date base
     * @return
     */
    @ApiIgnore
    @GetMapping("/all")
    public ResponseEntity<?>obtenerTodosClientes(){

        List<Cliente> lsiatClientes= (List<Cliente>) clienteDAO.buscarTodos();
        if (lsiatClientes.isEmpty())
            throw new BadRequestException("No hay clientes dados de alta en la base de datos");

        return new ResponseEntity<List<Cliente>>( lsiatClientes,HttpStatus.OK);


    }
    /**
     * end point created for delete a client from de data base parsing its id
     * @param id of de client to delete
     * @return message of success or error
     */
    @ApiIgnore
    @DeleteMapping("/e")
    public ResponseEntity<?> eliminarClientes(@RequestParam(name = "id")Integer id){

        Optional<Cliente> cleinteEncontrado =clienteDAO.buscarPorID(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();

        if (!cleinteEncontrado.isPresent())
            throw new BadRequestException(String.format("El cliente con ID: %d no existe",id));


        clienteDAO.eliminarPorId(id);
        respuesta.put("OK", "Cliente ID: " + id + " eliminado exitosamente{"+cleinteEncontrado.get().toString()+"}");

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
    }

    /**
     * end point created with the porpuse to get a list of recommedated cards based on a perfil of a client
     * @param id of a client is wanted to search
     * @return list of card that has been made match with the parsed params
     */
    @GetMapping("/recomendaciones")
    @ApiOperation("Obtener recomendaciones de trajetas para un cliente pasando como parametro el cliente ID")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Accepted"),
            @ApiResponse(code = 404, message = "Not found"),
    }
    )
    public ResponseEntity<?> obtenerRecomendaciones(@RequestParam(name = "id")Integer id){
        Optional<Cliente> clienteEncotrado=null;
        Map<String, Object> respuesta = new HashMap<String, Object>();
        List<TarjetaDTO>tarjetaDTOS=null;
        try {
            clienteEncotrado=clienteDAO.buscarPorID(id);
            tarjetaDTOS= (List<TarjetaDTO>) tarjetaDAO.findTarjetasPorPasionEdadAndSalario(clienteEncotrado.get().getEdad(),clienteEncotrado.get().getSueldo(),clienteEncotrado.get().getPasion().getPasion());

            return new ResponseEntity<List<TarjetaDTO>>(tarjetaDTOS,HttpStatus.OK);
        }catch (NotFoundException e){
            logger.info(e.getMessage());
            respuesta.put("NOT FOUND", "No se encontraron recomedaciones");
        }

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.NOT_FOUND);
    }


}
