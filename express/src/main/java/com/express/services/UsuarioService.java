package com.express.services;

import com.express.dto.ListarUsuarioDto;
import com.express.dto.RegistroUsuarioDto;
import com.express.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario registrarUsuario(RegistroUsuarioDto registroUsuarioDto);
    Usuario validarUsuario(String correo, String password);

    List<ListarUsuarioDto> listarUsuarios();

    public void actualizarUsuario (Usuario usuario);

    public void eliminarUsuario (Usuario usuario);


    Usuario buscarBYId(Integer idusuario);

}
