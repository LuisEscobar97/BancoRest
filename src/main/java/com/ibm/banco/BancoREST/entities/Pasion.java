package com.ibm.banco.BancoREST.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pasiones",schema = "tarjetas")
public class Pasion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede estar vacio")
    @Column(name = "pasion",nullable = false,unique = true)
    private String pasion;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "pasion")
    @JsonIgnoreProperties({"pasion"})
    private List<TarjetaPasion> tarjetasPasiones;

    @OneToMany(mappedBy = "pasion",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"pasion"})
    private Set<Cliente> clientes;

    public Pasion(Integer id, String pasion) {
        this.id = id;
        this.pasion = pasion;
    }
    @PrePersist
    private void antesPersistir(){
        this.fechaCreacion=new Date();
    }

    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion=new Date();
    }

    @Override
    public String toString() {
        return "Pasion{" +
                "id=" + id +
                ", pasion='" + pasion + '\'' +
                '}';
    }
}
