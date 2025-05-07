package com.express.dto;

import com.express.model.Usuario;

import java.util.List;

public class ListarVehiculoDto {


    private int idVehiculo;
    private int Capacidad;
    private String documentacion;
    private String placaVehiculo;
    private String seguroVig;
    private String modelo;

    private List<String> primerNombre;
    private Usuario usuario;
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        Capacidad = capacidad;
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
    public List<String> getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(List<String> primerNombre) {
        this.primerNombre = primerNombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public ListarVehiculoDto(int idVehiculo, int capacidad, String documentacion, String placaVehiculo, String seguroVig, String modelo) {
        this.idVehiculo = idVehiculo;
        Capacidad = capacidad;
        this.documentacion = documentacion;
        this.placaVehiculo = placaVehiculo;
        this.seguroVig = seguroVig;
        this.modelo = modelo;
    }


}
