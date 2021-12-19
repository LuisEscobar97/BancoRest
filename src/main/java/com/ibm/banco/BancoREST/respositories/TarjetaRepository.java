package com.ibm.banco.BancoREST.respositories;

import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarjetaRepository extends CrudRepository<Tarjeta,Integer> {

   @Query("Select t from Tarjeta t join fetch t.tarjetaPasiones td join fetch td.pasion p " +
            "where lower(p.pasion) =lower(:parametroPasion) and td.limiteEdadMaximo >= :parametroEdad " +
            "and td.limiteEdadMinimo <= :parametroEdad " +
            "and td.limiteSalarioMaximo >= :parametroSalario " +
            "and td.limiteSalarioMinimo <= :parametroSalario")
   public Iterable<Tarjeta> findTarjetasPorPasionEdadAndSalario(@Param("parametroEdad")Integer edad, @Param("parametroSalario")Integer salario, @Param("parametroPasion")String pasion);


}
