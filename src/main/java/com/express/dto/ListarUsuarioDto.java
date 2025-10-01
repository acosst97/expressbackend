package com.express.dto;

import com.express.dto.rol.RolDto;
import com.express.model.Rol;
import com.express.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


public class ListarUsuarioDto {
    private Integer idUsuario;
    private String documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segApellido;
    private String telefono;
    private String correo;
    private int experiencia;
    private List<RolDto> roles; // Cambiado a lista
    public ListarUsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.documento = usuario.getDocumento();
        this.primerNombre = usuario.getPrimerNombre();
        this.segundoNombre = usuario.getSegundoNombre();
        this.primerApellido = usuario.getPrimerApellido();
        this.segApellido = usuario.getSegApellido();
        this.correo = usuario.getCorreo();
        this.telefono = usuario.getTelefono();
        this.experiencia = usuario.getExperiencia();

        this.roles = usuario.getRoles()
                .stream()
                .map(rol -> new RolDto(rol.getIdRol(), rol.getNombreRol()))
                .collect(Collectors.toList());
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<RolDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDto> roles) {
        this.roles = roles;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
}
