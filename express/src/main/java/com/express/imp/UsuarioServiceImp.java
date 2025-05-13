package com.express.imp;

import com.express.dto.ListarUsuarioDto;
import com.express.dto.MensajeDTO;
import com.express.dto.RegistroUsuarioDto;
import com.express.dto.UpdateUsuarioDTO;
import com.express.model.Rol;
import com.express.model.Usuario;
import com.express.repository.RolRepository;
import com.express.repository.UsuarioRepository;

import com.express.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolRepository rolRepository;

    @Transactional
    public Usuario registrarUsuario(RegistroUsuarioDto registroUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setDocumento(registroUsuarioDTO.getDocumento());
        usuario.setPrimerNombre(registroUsuarioDTO.getPrimerNombre());
        usuario.setSegundoNombre(registroUsuarioDTO.getSegundoNombre());
        usuario.setPrimerApellido(registroUsuarioDTO.getPrimerApellido());
        usuario.setSegApellido(registroUsuarioDTO.getSegApellido());
        usuario.setFecha_nacimiento(registroUsuarioDTO.getFechaNacimiento());
        usuario.setExperiencia(registroUsuarioDTO.getExperiencia());
        usuario.setTelefono(registroUsuarioDTO.getTelefono());
        usuario.setCorreo(registroUsuarioDTO.getCorreo());
        usuario.setPassword(passwordEncoder.encode(registroUsuarioDTO.getPassword()));

        // Obtener el rol por defecto ()
        Optional<Rol> rolClienteOptional = rolRepository.findById(3);
        if (rolClienteOptional.isPresent()) {
            Rol rolCliente = rolClienteOptional.get();
            usuario.setRoles(List.of(rolCliente)); // Asigna la lista de roles al usuario
        } else {
            throw new RuntimeException("Error: El rol con ID 3 no fue encontrado.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<ListarUsuarioDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(ListarUsuarioDto::new)
                .collect(Collectors.toList());
    }



    @Override
    public Usuario validarUsuario(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && passwordEncoder.matches(password,usuario.getPassword())){
            return usuario;
        }
        return  null;
    }
   //Crud


    @Override
    public ResponseEntity<?> updateUsuario(UpdateUsuarioDTO updateUsuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(updateUsuarioDTO.getIdUsuario());

        if (usuarioOptional.isEmpty()){
            return new ResponseEntity<>(new MensajeDTO("No se encontró el Usuario con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setPrimerNombre(updateUsuarioDTO.getPrimerNombre());
        usuario.setPrimerApellido(updateUsuarioDTO.getPrimerApellido());
        usuario.setSegundoNombre(updateUsuarioDTO.getSegundoNombre());
        usuario.setSegApellido(updateUsuarioDTO.getSegApellido());
        usuario.setExperiencia(updateUsuarioDTO.getExperiencia());
        usuario.setTelefono(updateUsuarioDTO.getTelefono());
        usuario.setCorreo(updateUsuarioDTO.getCorreo());
        Usuario newUsuario = usuarioRepository.save(usuario);
        return new  ResponseEntity<>(new MensajeDTO("Usuario actualizado exitosamente."), HttpStatus.OK);
    }
    @Override
    public void eliminarUsuario(Usuario usuario) {

    }

    @Override
    public Usuario buscarBYId(Integer idusuario) {
        return null;
    }




}
