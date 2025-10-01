package com.express.dto.estado;

public class ActualizarDto {
    private Integer idEstado;
    private String nombreEstado;
    private String descripcionEstado;

    public ActualizarDto(Integer idEstado, String nombreEstado, String descripcionEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
        this.descripcionEstado = descripcionEstado;
    }

    public ActualizarDto() {
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
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
