package com.express.dto.rol;

import com.express.model.Rol;

public class ListaRolDTO {
    int idRol;
    String nombreRol;

    public ListaRolDTO(Rol rol) {
        this.idRol = rol.getIdRol();
        this.nombreRol = rol.getNombreRol();
    }

    public ListaRolDTO() {
    }



    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
