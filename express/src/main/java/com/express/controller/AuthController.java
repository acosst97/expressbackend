package com.express.controller;

import com.express.dto.ResetContrasenaDTO;
import com.express.dto.SolicitudRecuperacionDTO;
import com.express.imp.EnviarEmailImp;
import com.express.model.Usuario;
import com.express.repository.UsuarioRepository;
import com.express.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EnviarEmailImp enviarEmailImp;

    @PostMapping("/solicitar-recuperacion")
    public ResponseEntity<?> solicitarRecuperacion(@RequestBody SolicitudRecuperacionDTO solicitud) {
        Usuario usuario = usuarioRepository.findByCorreo(solicitud.getCorreo());
        if (usuario == null) {
            return new ResponseEntity<>("Correo electrónico no encontrado", HttpStatus.NOT_FOUND);
        }
        // Genera token
        String token = UUID.randomUUID().toString();
        usuario.setResetPasswordToken(token);
        usuarioRepository.save(usuario);
        //Obtener nombre
        String nombreUsuario = usuario.getPrimerNombre();
        String apellido = usuario.getPrimerApellido();
        // Enviar el correo electrónico con el enlace de recuperación
        String recoveryLink = "http://localHost:4200/recovery/" + token;
        String subject = "Recuperación de Contraseña";
        String body = "Hola" + nombreUsuario + " " + apellido +  " \n\nHas solicitado restablecer tu contraseña. Haz clic en el siguiente enlace para continuar:\n\n" + recoveryLink + "\n\nSi no solicitaste esto, puedes ignorar este correo.";
        enviarEmailImp.enviarEmail("pepelolaso64@gmail.com", usuario.getCorreo(), subject, body);
        return new ResponseEntity<>("Se ha enviado un enlace de recuperación a tu correo electrónico", HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> restablecerContrasena(@RequestBody ResetContrasenaDTO resetContrasenaDTO) {
        Usuario usuario = usuarioRepository.findByResetPasswordToken(resetContrasenaDTO.getToken());

        if (usuario == null) {
            return new ResponseEntity<>("Token de recuperación inválido o expirado", HttpStatus.BAD_REQUEST);
        }
        String nuevaContrasena = resetContrasenaDTO.getNuevaContrasena();
        String contraseñaEncriptada = passwordEncoder.encode(nuevaContrasena);
        usuario.setPassword(contraseñaEncriptada);
        usuario.setResetPasswordToken(null);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Contraseña restablecida exitosamente", HttpStatus.OK);
    }
    @GetMapping("/reset-password")
    public ResponseEntity<?> mostrarFormularioRestablecimiento(@RequestParam("token") String token) {
        Usuario usuario = usuarioRepository.findByResetPasswordToken(token);

        if (usuario == null) {
            return new ResponseEntity<>("Token de recuperación inválido o expirado", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Token válido. Por favor, procede a cambiar tu contraseña.", HttpStatus.OK);
    }



}
