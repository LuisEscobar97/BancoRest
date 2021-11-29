package com.ibm.banco.BancoREST.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pasiones",schema = "tarjetas")
public class Pasion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "pasion",nullable = false,unique = true)
    private String pasion;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "pasion")
    private List<TarjetaPasion> tarjetasPasiones;



    public Pasion(Integer id, String pasion) {
        this.id = id;
        this.pasion = pasion;
    }

}
