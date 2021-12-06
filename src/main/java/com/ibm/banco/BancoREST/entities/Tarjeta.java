package com.ibm.banco.BancoREST.entities;

import com.ibm.banco.BancoREST.enums.TipoTarjeta;
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

@Entity
@Table(name = "tarjetas",schema = "tarjetas")
public class Tarjeta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre",nullable = false,unique = true)
    private String nombre;
    @Column(name = "tipo_tarjeta",nullable = false)
    private TipoTarjeta tipoTarjeta;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "tarjeta")
    private List<TarjetaPasion> tarjetaPasiones;

    public Tarjeta(Integer id, String nombre, TipoTarjeta tipoTarjeta) {
        this.id = id;
        this.nombre = nombre;
        this.tipoTarjeta = tipoTarjeta;
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
        return "Tarjeta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipoTarjeta=" + tipoTarjeta +
                '}';
    }
}
