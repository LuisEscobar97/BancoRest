package com.ibm.banco.BancoREST.controllers;

import com.ibm.banco.BancoREST.dto.TarjetaDTO;
import com.ibm.banco.BancoREST.entities.Banco;
import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.exceptions.BadRequestException;
import com.ibm.banco.BancoREST.exceptions.NotFoundException;
import com.ibm.banco.BancoREST.mapper.TarjetaMapper;
import com.ibm.banco.BancoREST.services.TarjetaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import  org.slf4j.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarjetas")
@Api(value = "Tarjetas", description = "REST API para Tarjetas", tags = { "Tarjetas" })
public class TarjetaController {

    @Autowired
    private TarjetaDAO tarjetaDAO;

    Logger logger = LoggerFactory.getLogger(TarjetaController.class);

    /**
     * end created to get an especific card from the data base
     * @param id of a card is wanted to search
     * @return a card if exist
     */
    @ApiIgnore
    @GetMapping("/tarjeta")
    private ResponseEntity<?> obtenerTarjetas(@RequestParam(name = "id") Integer id){
        Optional<Tarjeta> tarjeta = tarjetaDAO.buscarPorID(id);

        if (!tarjeta.isPresent())
            throw new BadRequestException(String.format("El tarjeta con ID: %d no existe",id));

        return new ResponseEntity<Tarjeta>(tarjeta.get(), HttpStatus.OK);
    }

    /**
     * end point created for upload to the data base a new card
     * @param tarjeta json object with all data to can create a card
     * @param result message of exception handler when there is an error whit JSON Object data
     * @return new Card
     */
    @ApiIgnore
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

    /**
     * end point created for list all cards from the date base
     * @return
     */
    @ApiIgnore
    @GetMapping("/all")
    public ResponseEntity<?>obtenerTodosTarjetas(){

        List<Tarjeta> lsiatTarjetas= (List<Tarjeta>) tarjetaDAO.buscarTodos();
        if (lsiatTarjetas.isEmpty())
            throw new BadRequestException("No hay tarjetas dadas de alta en la base de datos");

        return new ResponseEntity<List<Tarjeta>>( lsiatTarjetas,HttpStatus.OK);


    }

    /**
     * end ppint created for delete a card from de data base parsing its id
     * @param id of de Card to delete
     * @return message of success or error
     */
    @ApiIgnore
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

    /**
     * end point created for to get recomendations of difrent types of cards given age,salary and a pasion
     * @param edad of de user
     * @param salario of de user
     * @param pasion of de user
     * @return list of card that has been made match with the parsed params
     */

    @GetMapping("/recomendaciones")
    @ApiOperation("Obtener recomendaciones de tarajetas pasando como parametro una pasion, la edad y el salario")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Accepted"),
            @ApiResponse(code = 404, message = "Not found"),
            }
    )
    public ResponseEntity<?> obtenerRecomendaciones(@RequestParam(name = "edad")Integer edad,@RequestParam(name = "salario") Integer salario, @RequestParam(name = "pasion") String pasion){

        List<TarjetaDTO>tarjetaDTOS=null;
        Map<String, Object> respuesta = new HashMap<String, Object>();
    try {
        tarjetaDTOS= (List<TarjetaDTO>) tarjetaDAO.findTarjetasPorPasionEdadAndSalario(edad,salario,pasion);

        return new ResponseEntity<List<TarjetaDTO>>(tarjetaDTOS,HttpStatus.OK);
    }catch (NotFoundException e){
        logger.info(e.getMessage());
        respuesta.put("NOT FOUND", "No se encontraron recomedaciones");
    }

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.NOT_FOUND);
    }
}
