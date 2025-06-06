package com.express.services;

import com.express.dto.*;
import com.express.dto.rol.DisassociateRolUsuarioDTO;
import com.express.dto.rol.DisassociateRolUsuarioResponseDTO;
import com.express.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    Usuario registrarUsuario(RegistroUsuarioDto registroUsuarioDto);
    Usuario validarUsuario(String correo, String password);

    List<ListarUsuarioDto> listarUsuarios();

    ResponseEntity<?> updateUsuario(UpdateUsuarioDTO updateUsuarioDTO);

    public void eliminarUsuario (Usuario usuario);

    ResponseEntity<?> actualizarRolUsuario(UpdateUsuarioRolDTO updateUsuarioRolDTO);
    Usuario buscarBYId(Integer idusuario);
    ResponseEntity<DisassociateRolUsuarioResponseDTO> disassociateRolFromUser(DisassociateRolUsuarioDTO disassociateRolUsuarioDTO);
}
