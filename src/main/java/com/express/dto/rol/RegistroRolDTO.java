package com.express.dto.rol;

public class RegistroRolDTO {
    private String nombreRol;

    public RegistroRolDTO() {}

    public RegistroRolDTO(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
