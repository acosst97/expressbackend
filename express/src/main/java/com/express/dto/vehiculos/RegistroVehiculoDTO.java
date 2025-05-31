package com.express.dto.vehiculos;

import com.express.model.Vehiculo;

public class RegistroVehiculoDTO  {
    private int idVehiculo;
    private int capacidad;
    private String documentacion;
    private String docBase64;
    private String placaVehiculo;
    private String seguroVig;
    private String modelo;
    private String documento;

    public RegistroVehiculoDTO(Vehiculo vehiculo) {
        this.capacidad = vehiculo.getCapacidad();
        this.documentacion = vehiculo.getDocumentacion();
        this.docBase64 = vehiculo.getDocBase64();
        this.placaVehiculo = vehiculo.getPlacaVehiculo();
        this.seguroVig = vehiculo.getSeguroVig();
        this.modelo = vehiculo.getModelo();
        this.documento = vehiculo.getUsuario().getDocumento();
    }


    public RegistroVehiculoDTO() {
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

    public String getDocBase64() {
        return docBase64;
    }

    public void setDocBase64(String docBase64) {
        this.docBase64 = docBase64;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


}
