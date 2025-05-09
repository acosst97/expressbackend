package com.express.controller;

import com.express.dto.MensajeDTO;
import com.express.dto.serviciosDto.CrearServicioVehiculoDto;
import com.express.dto.serviciosDto.ListarServicioVehiculoDto;
import com.express.model.ServicioVehiculos;
import com.express.services.ServicioVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/services")
@CrossOrigin
public class ServicioVehiculoController {

    @Autowired
   private ServicioVehiculoService srv;
    @GetMapping("/listarVehiculos")
    public ResponseEntity<?> listarServicios() {
        List<ListarServicioVehiculoDto> servicios = srv.listarServiciosVehiculos();
        if (servicios == null || servicios.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No hay servicios para mostrar."), HttpStatus.OK);

        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Lista de Servicios obtenida exitosamente.");
            response.put("servicios", servicios);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
    }

    @PostMapping("/registerServiciosVehiculos")
    public ResponseEntity<MensajeDTO> registrarServicio(@RequestBody CrearServicioVehiculoDto crearServicioVehiculoDto) {
        ServicioVehiculos nuevoServicio = srv.registrarServicioVehiculo(crearServicioVehiculoDto);
        if (nuevoServicio != null){
            return new ResponseEntity<>(new MensajeDTO("Servicio Creado Exitosamente") ,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new MensajeDTO("No se pudo crear el servicio") ,HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO> eliminarVehiculo(@PathVariable int id) {
        boolean eliminado = srv.deleteServicio(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el servicio vehicular con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }

    }
}
