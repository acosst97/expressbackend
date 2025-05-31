package com.express.dto.vehiculos;

import com.express.model.Vehiculo;
import com.express.services.VehiculoService;

public class ListarVehiculosDTO {

    private int idVehiculo;
    private int capacidad;
    private String documentacion;
    private String placaVehiculo;
    private String seguroVig;
    private String modelo;
    private String base64;
    private String documentoUsuario;
    private String nombreUsuario;

    // Constructor


    public ListarVehiculosDTO(Vehiculo vehiculo) {
        this.idVehiculo = vehiculo.getIdVehiculo();
        this.capacidad = vehiculo.getCapacidad();
        this.documentacion = vehiculo.getDocumentacion();
        this.base64 = vehiculo.getDocBase64();
        this.placaVehiculo = vehiculo.getPlacaVehiculo();
        this.seguroVig = vehiculo.getSeguroVig();
        this.modelo = vehiculo.getModelo();
        this.documentoUsuario = vehiculo.getUsuario().getDocumento();
        this.nombreUsuario = vehiculo.getUsuario().getPrimerNombre();
    }


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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
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

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
