package com.express.imp;

import com.express.dto.*;
import com.express.dto.rol.DisassociateRolUsuarioDTO;
import com.express.dto.rol.DisassociateRolUsuarioResponseDTO;
import com.express.dto.rutas.RutaExcelDTO;
import com.express.model.Estado;
import com.express.model.Rol;
import com.express.model.Ruta;
import com.express.model.Usuario;
import com.express.repository.EstadoRepository;
import com.express.repository.RolRepository;
import com.express.repository.UsuarioRepository;

import com.express.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
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

    @Override
    @Transactional
    public ResponseEntity<DisassociateRolUsuarioResponseDTO> disassociateRolFromUser(DisassociateRolUsuarioDTO disassociateRolUsuarioDTO) {
        Integer idUsuario = disassociateRolUsuarioDTO.getIdUsuario();
        Integer idRol = disassociateRolUsuarioDTO.getIdRol();
        // 1. Validaciones de entrada
        if (idUsuario == null) {
            return new ResponseEntity<>(
                    new DisassociateRolUsuarioResponseDTO(false, "El ID de usuario no puede ser nulo.", null, idRol),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (idRol == null) {
            return new ResponseEntity<>(
                    new DisassociateRolUsuarioResponseDTO(false, "El ID de rol no puede ser nulo.", idUsuario, null),
                    HttpStatus.BAD_REQUEST
            );
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(
                    new DisassociateRolUsuarioResponseDTO(false, "Usuario no encontrado con ID: " + idUsuario, idUsuario, idRol),
                    HttpStatus.NOT_FOUND
            );
        }
        Usuario usuario = usuarioOptional.get();

        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if (rolOptional.isEmpty()) {
            return new ResponseEntity<>(
                    new DisassociateRolUsuarioResponseDTO(false, "Rol no encontrado con ID: " + idRol, idUsuario, idRol),
                    HttpStatus.NOT_FOUND
            );
        }
        Rol rolToDisassociate = rolOptional.get();
        // 4. Validar si el usuario realmente tiene el rol asociado
        // Utiliza un iterador para remover de forma segura mientras se itera
        boolean rolFoundAndRemoved = false;
        if (usuario.getRoles() != null) {
            Iterator<Rol> rolIterator = usuario.getRoles().iterator();
            while (rolIterator.hasNext()) {
                Rol currentRol = rolIterator.next();
                if (currentRol.getIdRol().equals(rolToDisassociate.getIdRol())) {
                    rolIterator.remove();
                    rolFoundAndRemoved = true;
                    break;
                }
            }
        }
        if (!rolFoundAndRemoved) {
            return new ResponseEntity<>(
                    new DisassociateRolUsuarioResponseDTO(false, "El usuario (ID: " + idUsuario + ") no tiene el rol " + rolToDisassociate.getNombreRol() + " (ID: " + idRol + ") asociado.", idUsuario, idRol),
                    HttpStatus.BAD_REQUEST
            );
        }

        // 5. Guardar el usuario actualizado
        // Esto actualizará la tabla intermedia (usuario_has_rol) al eliminar la entrada.
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(
                new DisassociateRolUsuarioResponseDTO(true, "Rol '" + rolToDisassociate.getNombreRol() + "' (ID: " + idRol + ") desasociado exitosamente del usuario (ID: " + idUsuario + ").", idUsuario, idRol),
                HttpStatus.OK
        );
    }



    public  UsuarioLoginResponseDTO convertirAUsuarioLoginDTO(Usuario usuario) {
        UsuarioLoginResponseDTO dto = new UsuarioLoginResponseDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setPrimerNombre(usuario.getPrimerNombre());
        dto.setPrimerApellido(usuario.getPrimerApellido());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());
        dto.setRoles(usuario.getRoles()
                .stream()
                .map(Rol::getNombreRol)
                .collect(Collectors.toList()));
        return dto;
    }

}
