package com.express.imp;

import com.express.dto.RegistroUsuarioDto;
import com.express.model.Usuario;
import com.express.repository.UsuarioRepository;
import com.express.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario registrarUsuario(RegistroUsuarioDto registroUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setPrimerNombre(registroUsuarioDTO.getPrimerNombre());
        usuario.setSegundoNombre(registroUsuarioDTO.getSegundoNombre());
        usuario.setPrimerApellido(registroUsuarioDTO.getPrimerApellido());
        usuario.setSegApellido(registroUsuarioDTO.getSegApellido());
        usuario.setFecha_nacimiento(registroUsuarioDTO.getFechaNacimiento());
        usuario.setExperiencia(registroUsuarioDTO.getExperiencia());
        usuario.setTelefono(registroUsuarioDTO.getTelefono());
        usuario.setCorreo(registroUsuarioDTO.getCorreo());
        usuario.setPassword(passwordEncoder.encode(registroUsuarioDTO.getPassword()));
        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario validarUsuario(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && passwordEncoder.matches(password,usuario.getPassword())){
            return usuario;
        }
        return  null;
    }
}
