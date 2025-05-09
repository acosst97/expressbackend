package com.express.controller;

import com.express.dto.*;
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
    public ResponseEntity<List<ListarUsuarioDto>> listarUsuarios() {
        List<ListarUsuarioDto> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }



}
