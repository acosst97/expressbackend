package com.express.controller;


import com.express.dto.MensajeDTO;
import com.express.dto.reservaciones.ListarReservacionesDto;
import com.express.dto.reservaciones.RegistroReservacionesDto;
import com.express.model.Reservacion;
import com.express.services.ReservacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reservaciones")
@CrossOrigin
public class ReservacionesController {

    @Autowired
    private ReservacionesService rService;

    @GetMapping("/lstReservaciones")
    public ResponseEntity<?> listarReservaciones(){
        List<ListarReservacionesDto> reservacion = this.rService.listarReservaciones();
        if (reservacion == null || reservacion.isEmpty()){
            Map<String,Object> responseEmpty  =  new HashMap<>();
            responseEmpty.put("mensaje", "No hay reservaciones Disponibles");
            responseEmpty.put("reservaciones",reservacion);
            return new ResponseEntity<>(responseEmpty, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Lista de reservaciones obtenida exitosamente.");
            response.put("reservaciones", reservacion);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/registre")
    public  ResponseEntity<?> register (@RequestBody RegistroReservacionesDto registroReservacionesDto){
        return  rService.registrarReservacion(registroReservacionesDto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarReservacion(@PathVariable("id") int id) {
        return rService.eliminarReservacion(id);
    }
}
