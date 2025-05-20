package com.express.dto;

import com.express.model.Usuario;

public class UpdateUsuarioDTO {
    private int idUsuario;
    private String primerNombre;

    private String segundoNombre;
    private String primerApellido;
    private String segApellido;

    private int    experiencia;
    private String telefono;
    private String correo;

    public UpdateUsuarioDTO(Usuario usuario){
        this.idUsuario = usuario.getIdUsuario();
        this.primerNombre = usuario.getPrimerNombre();
        this.segundoNombre = usuario.getSegundoNombre();
        this.primerApellido = usuario.getSegApellido();
        this.segApellido = usuario.getSegApellido();
        this.experiencia = usuario.getExperiencia();
        this.telefono = usuario.getTelefono();
        this.correo = usuario.getCorreo();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
