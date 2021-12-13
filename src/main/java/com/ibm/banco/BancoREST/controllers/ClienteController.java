package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.dto.TarjetaDTO;
import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.exceptions.NotFoundException;
import com.ibm.banco.BancoREST.mapper.TarjetaMapper;
import com.ibm.banco.BancoREST.services.ClienteDAO;
import com.ibm.banco.BancoREST.services.TarjetaDAO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @Autowired
    private TarjetaDAO tarjetaDAO;

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
    public ResponseEntity<?> eliminarClientes(@RequestParam(name = "id")Integer id){

        Optional<Cliente> alumnoencontrado =clienteDAO.buscarPorID(id);
        Map<String, Object> respuesta = new HashMap<String, Object>();

        if (!alumnoencontrado.isPresent())
            throw new BadRequestException(String.format("El cliente con ID: %d no existe",id));


        clienteDAO.eliminarPorId(id);
        respuesta.put("OK", "Cliente ID: " + id + " eliminado exitosamente{"+alumnoencontrado.get().toString()+"}");

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
    }

    /**
     * end point creaqdo con la finaloidad de poder obtener las recomendaciones de tarjetas
     * para un usuarios en especifico
     * @param id del usuario al cual voy a entregarle recomedaciones acorde a su perfil
     * @return lista de tarjetas que hagan match con la informacion proporcionada del usuario
     */

    @GetMapping("/recomendaciones")
    @ApiOperation("Get recomedations of a difrents cards parsing ID froma client")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Accepted"),
            @ApiResponse(code = 404, message = "Not found"),
    }
    )
    public ResponseEntity<?> obtenerRecomendaciones(@RequestParam(name = "id")Integer id){
        Optional<Cliente> clienteEncotrado=clienteDAO.buscarPorID(id);
        if (!clienteEncotrado.isPresent())
            throw new NotFoundException(String.format("El cliente con ID: %d no existe",id));

        List<Tarjeta>listaTarjetas= (List<Tarjeta>) tarjetaDAO.findTarjetasPorPasionEdadAndSalario(clienteEncotrado.get().getEdad(),clienteEncotrado.get().getSueldo(),clienteEncotrado.get().getPasion().getPasion());
        if (listaTarjetas.isEmpty())
            throw new NotFoundException("No se encontraron recomedaciones para su perfil");

        List<TarjetaDTO>tarjetaDTOS=listaTarjetas.stream()
                .map(TarjetaMapper::mapTarjeta)
                .collect(Collectors.toList());
        return new ResponseEntity<List<TarjetaDTO>>(tarjetaDTOS,HttpStatus.OK);
    }


}
