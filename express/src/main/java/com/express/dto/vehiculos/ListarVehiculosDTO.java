package com.express.dto.vehiculos;

import com.express.dto.ListarVehiculoDto;
import com.express.model.Vehiculo;

public class ListarVehiculosDTO {

    private int idVehiculo;
    private int capacidad;
    private String documentacion;
    private String placaVehiculo;
    private String seguroVig;
    private String modelo;
    private String primerNombre;

    public ListarVehiculosDTO(Vehiculo vehiculo){
       this.idVehiculo = vehiculo.getIdVehiculo();
       this.capacidad = vehiculo.getCapacidad();
       this.documentacion = vehiculo.getDocumentacion();
       this.placaVehiculo = vehiculo.getPlacaVehiculo();
       this.seguroVig = vehiculo.getSeguroVig();
       this.modelo = vehiculo.getModelo();
        if (vehiculo.getUsuario() != null) {
            this.primerNombre = vehiculo.getUsuario().getPrimerNombre();
        }
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

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
}
