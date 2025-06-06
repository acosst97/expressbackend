package com.express.controller;

import com.express.dto.MensajeDTO;
import com.express.dto.rutas.ListarRutasDTO;
import com.express.dto.rutas.RegistroRutaDTO;
import com.express.dto.rutas.UpdateRutaDTO;
import com.express.repository.ReservacionesRepository;

import com.express.services.RutaService;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rutas")
@CrossOrigin
public class RutaController {

    @Autowired
    RutaService rutaS;
    @Autowired
    ReservacionesRepository reservaR;

    @GetMapping("/listarRutas")
    public ResponseEntity<?> listarRutas(){
        List<ListarRutasDTO> rutas = this.rutaS.listaRutas();
        if (rutas == null || rutas.isEmpty() ){
            Map<String,Object> responseEmpty  =  new HashMap<>();
            responseEmpty.put("mensaje", "No hay Rutas Disponibles");
            responseEmpty.put("rutas",rutas);
         return new ResponseEntity<>(responseEmpty, HttpStatus.OK);
        }else{
            Map<String,Object> response  =  new HashMap<>();
            response.put("mensaje", "Consumo Exitoso");
            response.put("rutas",rutas);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> crearRuta(@RequestBody RegistroRutaDTO registroRutaDTO) {
        return rutaS.registrarRuta(registroRutaDTO);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarRuta(@RequestBody UpdateRutaDTO updateRutaDTO) {
        return rutaS.updateRuta(updateRutaDTO);
    }

    @DeleteMapping("/eliminar/{id}") // Ejemplo de endpoint para eliminar
    public ResponseEntity<?> eliminarRuta(@PathVariable("id") int id) {
        return rutaS.deleteById(id);
    }

    /**
     * Endpoint para cargar rutas masivamente desde un archivo Excel.
     *
     * @param file El archivo Excel ( MultipartFile ).
     * @return ResponseEntity con el resultado de la operación de carga.
     */
    @PostMapping("/upload-excel")
    public ResponseEntity<Map<String, Object>> uploadRutasExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            Map<String, Object> response = Map.of(
                    "success", false,
                    "message", "Por favor, selecciona un archivo para subir."
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> result = rutaS.uploadRutasFromExcel(file);

        if (Boolean.TRUE.equals(result.get("success"))) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            // Puedes ajustar el HttpStatus según el tipo de error (ej. BAD_REQUEST, INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
