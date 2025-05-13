package com.express.services;

import com.express.dto.rutas.ListarRutasDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RutaService {

    List<ListarRutasDTO> listaRutas();

    //ResponseEntity<>
}
