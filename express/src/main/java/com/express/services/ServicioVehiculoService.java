package com.express.services;

import com.express.dto.serviciosDto.CrearServicioVehiculoDto;
import com.express.dto.serviciosDto.ListarServicioVehiculoDto;
import com.express.model.ServicioVehiculos;

import java.util.List;

public interface ServicioVehiculoService {

    List<ListarServicioVehiculoDto> listarServiciosVehiculos();
    ServicioVehiculos registrarServicioVehiculo(CrearServicioVehiculoDto crearServicioVehiculoDto);

    boolean deleteServicio(int idServicio);
    //public void updateServicio();

}
