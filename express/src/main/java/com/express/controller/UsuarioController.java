package com.express.controller;

import com.express.dto.*;
import com.express.dto.rol.ListaRolDTO;
import com.express.imp.RolImp;
import com.express.model.Rol;
import com.express.model.Usuario;
import com.express.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    RolImp rolImp;
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody RegistroUsuarioDto registroUsuarioDTO){
        Usuario nuevoUsuario = usuarioService.registrarUsuario(registroUsuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginUsuarioDTO loginUsuarioDTO) {
        Usuario usuarioValidado = usuarioService.validarUsuario(loginUsuarioDTO.getCorreo(), loginUsuarioDTO.getPassword());
        if (usuarioValidado != null) {
            return new ResponseEntity<>(usuarioValidado, HttpStatus.OK);
        } else {
             return new ResponseEntity<>(new MensajeDTO("Correo o contraseña incorrectos"), HttpStatus.UNAUTHORIZED);
        }
  }
    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        List<ListarUsuarioDto> usuarios = usuarioService.listarUsuarios();
        if (usuarios == null || usuarios.isEmpty()){
            return new ResponseEntity<>(new MensajeDTO("No hay Usuarios para mostrar."), HttpStatus.OK);
        }else{
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Lista de usuarios obtenida exitosamente.");
            response.put("usuarios", usuarios);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarUsuario(@RequestBody UpdateUsuarioDTO updateUsuarioDTO){
     return usuarioService.updateUsuario(updateUsuarioDTO);
    }
    @PutMapping("/actualizar-rol")
    public ResponseEntity<?> actualizarRolUsuario(@RequestBody UpdateUsuarioRolDTO updateUsuarioRolDTO) {
        return usuarioService.actualizarRolUsuario(updateUsuarioRolDTO);
    }
    @GetMapping("/listarRoles")
    public ResponseEntity<?> listarRoles() {
            List<ListaRolDTO> roll = rolImp.buscartodos();
        if (roll == null || roll.isEmpty()){
            return new ResponseEntity<>(new MensajeDTO("No hay Roles para mostrar."), HttpStatus.OK);
        }else{
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Lista de roles obtenida exitosamente.");
            response.put("roles", roll);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


}
