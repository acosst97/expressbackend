package com.express.imp;

import com.express.dto.ListarVehiculoDto;
import com.express.model.Vehiculo;
import com.express.services.VehiculoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoImp  implements VehiculoService {


    @Override
    public void CrearVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void deleteVehiculo(int idVehiculo) {

    }

    @Override
    public List<ListarVehiculoDto> listartVehiculos() {
        return null;
    }
}
