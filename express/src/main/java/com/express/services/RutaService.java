package com.express.services;

import com.express.dto.rutas.ListarRutasDTO;
import com.express.dto.rutas.RegistroRutaDTO;
import com.express.dto.rutas.UpdateRutaDTO;
import com.express.model.Ruta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RutaService {

    List<ListarRutasDTO> listaRutas();

     public ResponseEntity<?> deleteById(int idRuta);
    ResponseEntity<?> registrarRuta(RegistroRutaDTO registroRutaDTO);
    ResponseEntity<?> updateRuta(UpdateRutaDTO updateRutaDTO);
    Map<String, Object> uploadRutasFromExcel(MultipartFile file);
}
