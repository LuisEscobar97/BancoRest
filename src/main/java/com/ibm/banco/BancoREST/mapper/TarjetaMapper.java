package com.ibm.banco.BancoREST.mapper;

import com.ibm.banco.BancoREST.dto.TarjetaDTO;
import com.ibm.banco.BancoREST.entities.Tarjeta;

public class TarjetaMapper {
    public static TarjetaDTO mapTarjeta(Tarjeta tarjeta){
        TarjetaDTO tarjetaDTO= new TarjetaDTO();
        tarjetaDTO.setId(tarjeta.getId());
        tarjetaDTO.setNombre(tarjeta.getNombre());
        tarjetaDTO.setTipoTarjeta(tarjeta.getTipoTarjeta());
        return tarjetaDTO;

    }
}
