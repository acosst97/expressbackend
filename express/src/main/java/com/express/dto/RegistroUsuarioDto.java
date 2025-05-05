package com.express.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class RegistroUsuarioDto {

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segApellido;
    private String fechaNacimiento;
    private int experiencia;
    private String telefono;
    private String correo;
    private String password;
    public void RegistrarUsuarioDTO(String primerNombre, String segundoNombre, String primerApellido, String segApellido, String fechaNacimiento, int experiencia, String telefono, String correo, String password) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segApellido = segApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.experiencia = experiencia;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }


}
