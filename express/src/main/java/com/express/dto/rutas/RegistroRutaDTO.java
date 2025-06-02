package com.express.dto.rutas;

import jakarta.persistence.Column;

public class RegistroRutaDTO {

    private int idRuta;

    private String codRuta;

    private String nombreRuta;

    private String origenRuta;
    private String destinoRuta;

    private Integer idEstado;
    private Integer reservacionesIdReservaciones;

    public RegistroRutaDTO(int idRuta, String codRuta, String nombreRuta, String origenRuta, String destinoRuta, String estadoRuta, Integer idEstado, Integer reservacionesIdReservaciones) {
        this.idRuta = idRuta;
        this.codRuta = codRuta;
        this.nombreRuta = nombreRuta;
        this.origenRuta = origenRuta;
        this.destinoRuta = destinoRuta;
        this.idEstado = idEstado;
        this.reservacionesIdReservaciones = reservacionesIdReservaciones;
    }

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

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getReservacionesIdReservaciones() {
        return reservacionesIdReservaciones;
    }

    public void setReservacionesIdReservaciones(Integer reservacionesIdReservaciones) {
        this.reservacionesIdReservaciones = reservacionesIdReservaciones;
    }
}
