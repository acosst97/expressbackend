package com.express.services;

import com.express.dto.ListarVehiculoDto;
import com.express.dto.vehiculos.ActualizarVehiculoDTO;
import com.express.dto.vehiculos.RegistroVehiculoDTO;
import com.express.model.Vehiculo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

     ResponseEntity<?> registrarVehiculo(RegistroVehiculoDTO registroVehiculoDTO);

    public void deleteVehiculo(int idVehiculo);

   ResponseEntity<?> updateVehiculo(ActualizarVehiculoDTO actualizarVehiculoDTO);
    List<ListarVehiculoDto> listartVehiculos();

    Optional<Vehiculo> obtenerVehiculoPorId(int idVehiculo);
}
