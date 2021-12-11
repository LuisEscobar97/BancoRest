package com.ibm.banco.BancoREST.dto;

import com.ibm.banco.BancoREST.enums.TipoTarjeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaDTO {

    private Integer id;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede estar vacio")
    private String nombre;

    @Column(name = "tipo_tarjeta",nullable = false)
    private TipoTarjeta tipoTarjeta;
}
