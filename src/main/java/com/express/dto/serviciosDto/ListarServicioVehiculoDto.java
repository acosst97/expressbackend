package com.express.dto.serviciosDto;

import com.express.model.ServicioVehiculos;
import com.express.model.Vehiculo;

public class ListarServicioVehiculoDto {
    private int idServicio;
    private String nombreServicio;
    private String valorServicio;
    private String descripcion;
   private String images;
    public ListarServicioVehiculoDto(ServicioVehiculos servicioVehiculos) {
        this.idServicio = servicioVehiculos.getIdServicio();
        this.nombreServicio = servicioVehiculos.getNombreServicio();
        this.valorServicio = servicioVehiculos.getValorServicio();
        this.descripcion = servicioVehiculos.getDescripcion();
        this.images = servicioVehiculos.getImages();
    }
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(String valorServicio) {
        this.valorServicio = valorServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }



}
