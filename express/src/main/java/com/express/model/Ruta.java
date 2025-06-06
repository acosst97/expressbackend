package com.express.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservacion> reservaciones = new ArrayList<>();


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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }
    // Método de ayuda para añadir una reservación a la lista
    public void addReservacion(Reservacion reservacion) {
        this.reservaciones.add(reservacion);
        reservacion.setRuta(this); // Establecer la referencia inversa
    }

    // Método de ayuda para remover una reservación de la lista
    public void removeReservacion(Reservacion reservacion) {
        this.reservaciones.remove(reservacion);
        reservacion.setRuta(null); // Remover la referencia inversa
    }
}
