package com.express.services;

import com.express.dto.serviciosDto.CrearServicioVehiculoDto;
import com.express.dto.serviciosDto.ListarServicioVehiculoDto;
import com.express.dto.serviciosDto.UpdateServicesVehiculoDto;
import com.express.model.ServicioVehiculos;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServicioVehiculoService {

    List<ListarServicioVehiculoDto> listarServiciosVehiculos();
    ServicioVehiculos registrarServicioVehiculo(CrearServicioVehiculoDto crearServicioVehiculoDto);
    ResponseEntity<?> updateServiceVehicle(UpdateServicesVehiculoDto updateServicesVehiculoDto);
    boolean deleteServicio(int idServicio);


}
