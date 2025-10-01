package com.express.dto.rol;

public class DisassociateRolUsuarioDTO {
    private Integer idUsuario;
    private Integer idRol;

    public DisassociateRolUsuarioDTO() {
    }

    public DisassociateRolUsuarioDTO(Integer idUsuario, Integer idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
}
