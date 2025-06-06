package com.express.dto.rol;

public class DisassociateRolUsuarioResponseDTO {
    private boolean success;
    private String message;
    private Integer idUsuario;
    private Integer idRol;

    public DisassociateRolUsuarioResponseDTO(boolean success, String message, Integer idUsuario, Integer idRol) {
        this.success = success;
        this.message = message;
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
