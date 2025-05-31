package com.express.controller;

import com.express.dto.MensajeDTO;
import com.express.dto.vehiculos.ActualizarVehiculoDTO;
import com.express.dto.vehiculos.AsignarVehiculoDTO;
import com.express.dto.vehiculos.ListarVehiculosDTO;
import com.express.dto.vehiculos.RegistroVehiculoDTO;
import com.express.model.Vehiculo;
import com.express.repository.UsuarioRepository;
import com.express.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/vehiculos")
@CrossOrigin
public class VehiculoController {


     @Autowired
    private UsuarioRepository userR;
     @Autowired
    private VehiculoService vSrv;

     @GetMapping("/listarVehiculos")
     public ResponseEntity<?> listarVehiculos(){
         List<ListarVehiculosDTO> vehiculo = this.vSrv.listartVehiculos();
         if (vehiculo == null || vehiculo.isEmpty()){
             return  new ResponseEntity<>(new MensajeDTO("No hay Vehiculos Registrados"),HttpStatus.OK);
         }else{
             Map<String,Object> response  =  new HashMap<>();
             response.put("mensaje", "Consumo Exitoso");
             response.put("vehiculos",vehiculo);
             return new ResponseEntity<>(response,HttpStatus.OK);
         }
     }

     @PostMapping("/registrarVehiculo")
     public ResponseEntity<?>registroVehiculo (@RequestBody  RegistroVehiculoDTO registroVehiculoDTO){
      return vSrv.registrarVehiculo(registroVehiculoDTO);
     }
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarVehiculo(@RequestBody ActualizarVehiculoDTO actualizarVehiculoDTO) {
        return vSrv.updateVehiculo(actualizarVehiculoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable int id) {
        Optional<Vehiculo> vehiculoOptional = vSrv.obtenerVehiculoPorId(id);
        if (vehiculoOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el vehículo con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        vSrv.deleteVehiculo(id);
        return new ResponseEntity<>(new MensajeDTO("Vehículo eliminado exitosamente."), HttpStatus.OK);
    }

     //Asigncacion de vehiculo
     @PutMapping("/asignar")
     public ResponseEntity<?> asignarVehiculo(@RequestBody AsignarVehiculoDTO asignarVehiculoDTO) {
         return vSrv.asignarVehiculo(asignarVehiculoDTO);
     }

}
