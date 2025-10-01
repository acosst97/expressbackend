package com.express.services;

import com.express.dto.rol.ListaRolDTO;
import com.express.dto.rol.RegistroRolDTO;
import com.express.model.Rol;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolService {
    public List<ListaRolDTO> buscartodos();
    ResponseEntity<?> registrarRol(RegistroRolDTO registroRolDTO);
}
