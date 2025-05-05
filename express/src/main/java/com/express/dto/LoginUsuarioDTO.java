package com.express.dto;


public class LoginUsuarioDTO {
    private String correo;
    private String password;

    public LoginUsuarioDTO(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public String getCorreo() {
        return  this.correo;
    }

    public String getPassword() {
        return this.password;
    }
}
