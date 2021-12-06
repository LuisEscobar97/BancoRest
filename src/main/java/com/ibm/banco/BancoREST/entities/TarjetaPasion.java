package com.ibm.banco.BancoREST.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tarjetas_pasiones",schema = "tarjetas")
public class TarjetaPasion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "limite_edad_maximo",nullable = false)
    private Integer limiteEdadMaximo;
    @Column(name = "limite_edad_minimo",nullable = false)
    private Integer limiteEdadMinimo;
    @Column(name = "limite_salario_maximo",nullable = false)
    private Integer limiteSalarioMaximo;
    @Column(name = "limite_salario_minimo",nullable = false)
    private Integer limiteSalarioMinimo;

    @ManyToOne(optional = true,cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "pasion_id")
    private Pasion pasion;
    @ManyToOne(optional = true,cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_id")
    private Tarjeta tarjeta;

    public TarjetaPasion(Integer id, Integer limiteEdadMaximo, Integer limiteEdadMinimo, Integer limiteSalarioMaximo, Integer limiteSalarioMinimo) {
        this.id = id;
        this.limiteEdadMaximo = limiteEdadMaximo;
        this.limiteEdadMinimo = limiteEdadMinimo;
        this.limiteSalarioMaximo = limiteSalarioMaximo;
        this.limiteSalarioMinimo = limiteSalarioMinimo;
    }

    @Override
    public String toString() {
        return "TarjetaPasion{" +
                "id=" + id +
                ", limiteEdadMaximo=" + limiteEdadMaximo +
                ", limiteEdadMinimo=" + limiteEdadMinimo +
                ", limiteSalarioMaximo=" + limiteSalarioMaximo +
                ", limiteSalarioMinimo=" + limiteSalarioMinimo +
                '}';
    }
}
