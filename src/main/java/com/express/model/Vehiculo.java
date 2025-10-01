package com.express.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name="vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehiculo;
    private int capacidad;
    private String documentacion;
    @Column(columnDefinition = "TEXT")
    private String docBase64;

    private String placaVehiculo;
    private String seguroVig;
    private String modelo;
    @ManyToOne
    @JoinColumn(name = "usuario_idusuario") // Nombre de la columna de clave foránea en la tabla Vehiculo
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "reservaciones_id_reservaciones")
    private Reservacion reservacion;




    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getSeguroVig() {
        return seguroVig;
    }

    public void setSeguroVig(String seguroVig) {
        this.seguroVig = seguroVig;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDocBase64() {
        return docBase64;
    }

    public void setDocBase64(String docBase64) {
        this.docBase64 = docBase64;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }


}
