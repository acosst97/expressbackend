package com.express.controller;

import com.express.dto.*;
import com.express.dto.rol.DisassociateRolUsuarioDTO;
import com.express.dto.rol.DisassociateRolUsuarioResponseDTO;
import com.express.dto.rol.ListaRolDTO;
import com.express.dto.rol.RegistroRolDTO;
import com.express.imp.RolImp;
import com.express.imp.UsuarioServiceImp;
import com.express.model.Rol;
import com.express.model.Usuario;
import com.express.services.RolService;
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
    private RolService rolService;
    @Autowired
    RolImp rolImp;
    @Autowired
    UsuarioServiceImp uImp;
    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registrarUsuario(@RequestBody RegistroUsuarioDto registroUsuarioDTO) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(registroUsuarioDTO);
            return new ResponseEntity<>(new MensajeDTO("Usuario registrado exitosamente con ID: " + nuevoUsuario.getIdUsuario()), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new MensajeDTO("Error al registrar usuario: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginUsuarioDTO loginUsuarioDTO) {
        Usuario usuarioValidado = usuarioService.validarUsuario(loginUsuarioDTO.getCorreo(), loginUsuarioDTO.getPassword());
        if (usuarioValidado != null) {
            UsuarioLoginResponseDTO responseDTO = uImp.convertirAUsuarioLoginDTO(usuarioValidado);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
             return new ResponseEntity<>(new MensajeDTO("Correo o contraseña incorrectos"), HttpStatus.UNAUTHORIZED);
        }
  }
    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        List<ListarUsuarioDto> usuarios = usuarioService.listarUsuarios();
        if (usuarios == null || usuarios.isEmpty()){
            Map<String,Object> responseEmpty  =  new HashMap<>();
            responseEmpty.put("mensaje", "No hay usuarios Disponibles");
            responseEmpty.put("usuarios",usuarios);
            return new ResponseEntity<>(responseEmpty, HttpStatus.OK);
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
    @PostMapping("/registroRol")
    public ResponseEntity<?> registrarRol(@RequestBody RegistroRolDTO registroRolDTO) {
        return rolService.registrarRol(registroRolDTO);
    }
    /**
     * Endpoint para desasociar un rol de un usuario.
     * Requiere un DisassociateRolUsuarioDTO con idUsuario y idRol.
     * @param disassociateRolUsuarioDTO DTO que contiene el ID del usuario y el ID del rol a desasociar.
     * @return ResponseEntity con DisassociateRolUsuarioResponseDTO indicando el resultado.
     */
    @PostMapping("/desasociar-rol")
    public ResponseEntity<DisassociateRolUsuarioResponseDTO> desasociarRol(@RequestBody DisassociateRolUsuarioDTO disassociateRolUsuarioDTO) {
        return usuarioService.disassociateRolFromUser(disassociateRolUsuarioDTO);
    }


}
