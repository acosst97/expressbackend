package com.express.dto.estado;

import com.express.model.Estado;

public class ListarEstado {
    private Integer idEstado;
    private String nombreEstado;
    private String descripcionEstado;

    public ListarEstado(Estado estado) {
        this.idEstado = estado.getIdEstado();
        this.nombreEstado = estado.getNombreEstado();
        this.descripcionEstado = estado.getDescripcionEstado();
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
