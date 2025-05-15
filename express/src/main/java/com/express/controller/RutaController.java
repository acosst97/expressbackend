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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rutas")
public class RutaController {

    @Autowired
    RutaService rutaS;
    @Autowired
    ReservacionesRepository reservaR;

    @GetMapping("/listarRutas")
    public ResponseEntity<?> listarRutas(){
        List<ListarRutasDTO> rutas = this.rutaS.listaRutas();
        if (rutas == null || rutas.isEmpty() ){
         return new ResponseEntity<>(new MensajeDTO("No hay Rutas Disponibles"), HttpStatus.OK);
        }else{
            Map<String,Object> response  =  new HashMap<>();
            response.put("mensaje", "Consumo Exitoso");
            response.put("rutas",rutas);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearRuta(@RequestBody RegistroRutaDTO registroRutaDTO) {
        return rutaS.registrarRuta(registroRutaDTO);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarRuta(@RequestBody UpdateRutaDTO updateRutaDTO) {
        return rutaS.updateRuta(updateRutaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRuta(@PathVariable int id) {
        return rutaS.deleteById(id);
    }


}
