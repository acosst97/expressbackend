package com.express.dto;

import com.express.model.Rol;
import com.express.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ListarUsuarioDto {
    private Integer idUsuario;
    private String documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segApellido;
    private String correo;
    private List<String> nombresRoles; // Lista para los nombres de los roles
    private Rol rol;
    public ListarUsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.documento  = usuario.getDocumento();
        this.primerNombre = usuario.getPrimerNombre();
        this.segundoNombre = usuario.getSegundoNombre();
        this.primerApellido = usuario.getPrimerApellido();
        this.segApellido = usuario.getSegApellido();
        this.correo = usuario.getCorreo();
        this.nombresRoles = usuario.getRoles().stream()
                .map(Rol::getNombreRol)
                .collect(Collectors.toList());
    }
  //  private RolDto rol;


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<String> getNombresRoles() {
        return nombresRoles;
    }

    public void setNombresRoles(List<String> nombresRoles) {
        this.nombresRoles = nombresRoles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
