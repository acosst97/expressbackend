package com.express.services;

import com.express.dto.ListarVehiculoDto;
import com.express.model.Vehiculo;
import lombok.Data;

import java.util.List;

public interface VehiculoService {

    public void CrearVehiculo(Vehiculo vehiculo);
    public void deleteVehiculo(int idVehiculo);

    List<ListarVehiculoDto> listartVehiculos();
}
