package com.ibm.banco.BancoREST.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "clientes",schema = "tarjetas")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 5,max = 50,message = "la logitud va de 5 a 50 caracteres")
    private String nombre;
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 5,max = 50,message = "la logitud va de 5 a 50 caracteres")
    private String apellido;
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 5,max = 50,message = "la logitud va de 5 a 50 caracteres")
    private String dni;
    @NotNull(message = "No puede ser nulo")
    @Positive(message = "tiene que ser mayor a cero")
    private BigDecimal sueldo;
    @NotNull(message = "No puede ser nulo")
    @Positive(message = "tiene que ser mayor a cero")
    private Integer edad;
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "pasion_id",foreignKey = @ForeignKey(name = "FK_PASION_ID"))
    private Pasion pasion;

    public Cliente(Integer id, String nombre, String apellido, String dni, BigDecimal sueldo, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sueldo = sueldo;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", sueldo=" + sueldo +
                ", edad=" + edad +
                ", fechaAlta=" + fechaAlta +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
    @PrePersist
    private void antesPersistir(){
        this.fechaAlta=new Date();
    }

    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion=new Date();
    }
}
