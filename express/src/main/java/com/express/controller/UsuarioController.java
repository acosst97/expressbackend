package com.express.controller;

import com.express.dto.LoginUsuarioDTO;
import com.express.dto.MensajeDTO;
import com.express.dto.RegistroUsuarioDto;
import com.express.model.Usuario;
import com.express.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

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

            // Opción 2 (si usas DTO):
             return new ResponseEntity<>(new MensajeDTO("Correo o contraseña incorrectos"), HttpStatus.UNAUTHORIZED);
        }
    }

}
