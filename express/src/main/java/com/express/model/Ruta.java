package com.express.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Integer idRuta;

    @Column(name = "cod_ruta", nullable = false, length = 45) // nullable = false si no permite nulos
    private String codRuta;
    @Column(name = "activa", nullable = false)
    private boolean activa;
    @Column(name = "nombre_ruta", nullable = false, length = 45)
    private String nombreRuta;

    @Column(name = "origen_ruta", nullable = false, length = 45)
    private String origenRuta;

    @Column(name = "destino_ruta", nullable = false, length = 45)
    private String destinoRuta;

    @ManyToOne
    @JoinColumn(name = "estado_id_estado")
    private Estado estado;
    @OneToOne
    @JoinColumn(name = "reservaciones_id_reservaciones", unique = true) // Columna de clave foránea en la tabla 'ruta'
    private Reservacion reservacion;


    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }


    public String getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(String codRuta) {
        this.codRuta = codRuta;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getOrigenRuta() {
        return origenRuta;
    }

    public void setOrigenRuta(String origenRuta) {
        this.origenRuta = origenRuta;
    }

    public String getDestinoRuta() {
        return destinoRuta;
    }

    public void setDestinoRuta(String destinoRuta) {
        this.destinoRuta = destinoRuta;
    }



    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }



}
