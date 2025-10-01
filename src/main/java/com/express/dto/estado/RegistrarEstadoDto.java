package com.express.dto.estado;

public class RegistrarEstadoDto {

    String nombreEstado;
    String descripcionEstado;

    public RegistrarEstadoDto(String nombreEstado, String descripcionEstado) {
        this.nombreEstado = nombreEstado;
        this.descripcionEstado = descripcionEstado;
    }

    public RegistrarEstadoDto() {
    }
    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }
}
