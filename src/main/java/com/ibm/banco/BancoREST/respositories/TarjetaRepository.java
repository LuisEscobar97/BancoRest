package com.ibm.banco.BancoREST.respositories;

import com.ibm.banco.BancoREST.entities.Tarjeta;
import com.ibm.banco.BancoREST.entities.TarjetaPasion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarjetaRepository extends CrudRepository<Tarjeta,Integer> {
    /*@Query(value = "Select t from tarjetas.tarjetas t inner join tarjetas.tarjetas_pasiones tp on t.id=tp.tarjeta_id " +
            "inner join tarjetas.pasiones p on p.id=tp.pasion_id " +
            "where p.pasion=:parametroPasion " +
            "and tp.limite_edad_maximo>=:parametroEdad " +
            "and tp.limite_edad_minimo<=:parametroEdad " +
            "and tp.limite_salario_maximo>=:parametroSalario " +
            "and tp.limite_salario_minimo<=:parametroSalario"
            ,nativeQuery = true)*/
   @Query("Select t from Tarjeta t join fetch t.tarjetaPasiones td join fetch td.pasion p " +
            "where p.pasion =:parametroPasion and td.limiteEdadMaximo >= :parametroEdad " +
            "and td.limiteEdadMinimo <= :parametroEdad " +
            "and td.limiteSalarioMaximo >= :parametroSalario " +
            "and td.limiteSalarioMinimo <= :parametroSalario")
   public Iterable<Tarjeta> findTarjetasPorPasionEdadAndSalario(@Param("parametroEdad")Integer edad, @Param("parametroSalario")Integer salario, @Param("parametroPasion")String pasion);

    @Query("Select t from Tarjeta t join fetch t.tarjetaPasiones td join fetch td.pasion p where p.pasion =:parametroPasion " +
            "and td.limiteEdadMaximo >= :parametroEdad " +
            "and td.limiteEdadMinimo <= :parametroEdad")
    public Iterable<Tarjeta> findTarjetasPorPasionEdadAndSalario2(@Param("parametroEdad")Integer edad,@Param("parametroPasion")String pasion);
}
