package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.rol.ListaRolDTO;
import com.express.dto.rol.RegistroRolDTO;
import com.express.model.Rol;
import com.express.repository.RolRepository;
import com.express.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolImp implements RolService {

    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<ListaRolDTO> buscartodos() {
        List<Rol> rol =  rolRepository.findAll();
        return rol.stream().map(ListaRolDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> registrarRol(RegistroRolDTO registroRolDTO) {
        if (rolRepository.existsByNombreRol(registroRolDTO.getNombreRol())) {
            return new ResponseEntity<>(new MensajeDTO("El rol ya existe."), HttpStatus.BAD_REQUEST);
        }
        Rol nuevoRol = new Rol();
        nuevoRol.setNombreRol(registroRolDTO.getNombreRol());
        rolRepository.save(nuevoRol);
        return new ResponseEntity<>(new MensajeDTO("Rol registrado correctamente."), HttpStatus.CREATED);
    }


}
