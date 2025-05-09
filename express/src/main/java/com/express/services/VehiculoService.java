package com.express.services;

import com.express.dto.ListarVehiculoDto;
import com.express.dto.vehiculos.RegistroVehiculoDTO;
import com.express.model.Vehiculo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehiculoService {

     ResponseEntity<?> registrarVehiculo(RegistroVehiculoDTO registroVehiculoDTO);
    public void CrearVehiculo(Vehiculo vehiculo);
    public void deleteVehiculo(int idVehiculo);

    List<ListarVehiculoDto> listartVehiculos();
}
