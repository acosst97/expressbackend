package com.express.controller;

import com.express.dto.estado.ActualizarDto;
import com.express.dto.estado.ListarEstado;
import com.express.dto.estado.RegistrarEstadoDto;

import com.express.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/estado")
@CrossOrigin
public class EstadoController {
    @Autowired
    EstadoService srv;


    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistrarEstadoDto dto) {
        return srv.RegistrarEstado(dto);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        List<ListarEstado> estados = this.srv.listarEstados();
        if (estados == null || estados.isEmpty() ){
            Map<String,Object> responseEmpty  =  new HashMap<>();
            responseEmpty.put("mensaje", "No hay Rutas Disponibles");
            responseEmpty.put("estados",estados);
            return new ResponseEntity<>(responseEmpty, HttpStatus.OK);
        }else{
            Map<String,Object> response  =  new HashMap<>();
            response.put("mensaje", "Consumo Exitoso");
            response.put("estados",estados);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody ActualizarDto dto) {
        return srv.updateEstado(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        return srv.eliminarEstado(id);
    }

}
