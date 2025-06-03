package com.express.imp;

import com.express.dto.*;
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

import java.util.ArrayList;
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


        Optional<Rol> rolClienteOptional = rolRepository.findByNombreRol("CLIENTE");
        if (rolClienteOptional.isPresent()) {
            Rol rolCliente = rolClienteOptional.get();
            usuario.setRoles(List.of(rolCliente));
        } else {

            // Podrías lanzar una excepción personalizada o loggear un error.
            throw new RuntimeException("Error: El rol 'CLIENTE' no fue encontrado. Asegúrate de que existe en la base de datos.");
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
        Usuario newUsuario = usuarioRepository.save(usuario);
        return new  ResponseEntity<>(new MensajeDTO("Usuario actualizado exitosamente."), HttpStatus.OK);
    }
    @Override
    public void eliminarUsuario(Usuario usuario) {

    }

    @Override
    public ResponseEntity<?> actualizarRolUsuario(UpdateUsuarioRolDTO updateUsuarioRolDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(updateUsuarioRolDTO.getIdUsuario());
        Optional<Rol> rolOptional = rolRepository.findById(updateUsuarioRolDTO.getIdRol());

        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el Usuario con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        if (rolOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el Rol con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioOptional.get();
        Rol nuevoRol = rolOptional.get();
        List<Rol> roles = new ArrayList<>(usuario.getRoles());
        boolean yaAsignado = usuario.getRoles().stream()
                .anyMatch(r -> r.getIdRol().equals(nuevoRol.getIdRol()));
        if (!yaAsignado) {
            usuario.getRoles().add(nuevoRol);
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(new MensajeDTO("Rol asignado al usuario exitosamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MensajeDTO("El usuario ya tiene asignado este rol."), HttpStatus.OK);
        }
    }

    @Override
    public Usuario buscarBYId(Integer idusuario) {
        return null;
    }


    public  UsuarioLoginResponseDTO convertirAUsuarioLoginDTO(Usuario usuario) {
        UsuarioLoginResponseDTO dto = new UsuarioLoginResponseDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setPrimerNombre(usuario.getPrimerNombre());
        dto.setPrimerApellido(usuario.getPrimerApellido());
        dto.setCorreo(usuario.getCorreo());
        dto.setRoles(usuario.getRoles()
                .stream()
                .map(Rol::getNombreRol)
                .collect(Collectors.toList()));
        return dto;
    }

}
