package com.express.services;

import com.express.model.Vehiculo;
import lombok.Data;

public interface VehiculoService {

    public void CrearVehiculo(Vehiculo vehiculo);
    public void deleteVehiculo(int idVehiculo);
}
