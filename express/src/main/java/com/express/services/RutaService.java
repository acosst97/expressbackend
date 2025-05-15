package com.express.services;

import com.express.dto.rutas.ListarRutasDTO;
import com.express.dto.rutas.RegistroRutaDTO;
import com.express.dto.rutas.UpdateRutaDTO;
import com.express.model.Ruta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RutaService {

    List<ListarRutasDTO> listaRutas();

     public ResponseEntity<?> deleteById(int IdRuta);
    ResponseEntity<?> registrarRuta(RegistroRutaDTO registroRutaDTO);
    ResponseEntity<?> updateRuta(UpdateRutaDTO updateRutaDTO);
}
