package com.ibm.banco.BancoREST.respositories;

import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarjetaPasionRespository extends PagingAndSortingRepository<TarjetaPasion,Integer> {

    @Query("SELECT t.nombre,p.pasion,t.id FROM Tarjetas t JOIN t.id tp JOIN tp.pasion_id p"+
            " WHERE tp.limite_edad_maximo <=:parametroEdad"
            +" and tp.limite_edad_minimo>=:parametroEdad"+
            "and p.pasion=:parametroPasion"
            +"and tp.limite_salario_minimo>=:parametroSalario"+
            "and tp.limite_salario_maximo<=:parametroSalario")
    Optional<List<TarjetaPasion>>getTarjetasPorPasionEdadAndSalario(@Param("parametroEdad")Integer edad, @Param("parametroSalario")Integer salario, @Param("parametroPasion")String pasion);

}
