package com.express.services;

import com.express.dto.RegistroUsuarioDto;
import com.express.model.Usuario;

public interface UsuarioService {
    Usuario registrarUsuario(RegistroUsuarioDto registroUsuarioDto);
    Usuario validarUsuario(String correo, String password);


}
