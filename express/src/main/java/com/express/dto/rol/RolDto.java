package com.express.dto.rol;

public class RolDto {
    private Integer idRol;
    private String nombreRol;

    public RolDto(Integer idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }
}
