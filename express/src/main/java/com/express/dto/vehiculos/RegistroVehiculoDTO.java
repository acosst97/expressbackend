package com.express.dto.vehiculos;

import com.express.dto.ListarVehiculoDto;
import com.express.model.Vehiculo;
import com.express.services.VehiculoService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RegistroVehiculoDTO  {
    private int idVehiculo;
    private int capacidad;
    private String documentacion;
    private String placaVehiculo;
    private String seguroVig;
    private String modelo;
    private String documento;




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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


}
