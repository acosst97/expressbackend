package com.express.imp;

import com.express.dto.serviciosDto.CrearServicioVehiculoDto;
import com.express.dto.serviciosDto.ListarServicioVehiculoDto;

import com.express.model.ServicioVehiculos;
import com.express.repository.ServicioVehiculoRepository;
import com.express.services.ServicioVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioVehiculoImp implements ServicioVehiculoService {
    @Autowired
    ServicioVehiculoRepository servicioVehiculoRepository;

    @Override
    public List<ListarServicioVehiculoDto> listarServiciosVehiculos() {
        List<ServicioVehiculos> servicios = servicioVehiculoRepository.findAll();
        return servicios.stream()
                .map(servicio -> new ListarServicioVehiculoDto(
                        servicio.getIdServicio(),
                        servicio.getNombreServicio(),
                        servicio.getValorServicio().toString(),
                        servicio.getDescripcion(),
                        servicio.getImages()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public ServicioVehiculos registrarServicioVehiculo(CrearServicioVehiculoDto crearServicioVehiculoDto) {
        ServicioVehiculos srvVehiculos = new ServicioVehiculos();
        srvVehiculos.setNombreServicio(crearServicioVehiculoDto.getNombreServicio());
        srvVehiculos.setValorServicio(crearServicioVehiculoDto.getValorServicio());
        srvVehiculos.setDescripcion(crearServicioVehiculoDto.getDescripcion());
        srvVehiculos.setImages(crearServicioVehiculoDto.getImages());
        return servicioVehiculoRepository.save(srvVehiculos);
    }

    @Override
    public boolean deleteServicio(int id) {
        Optional<ServicioVehiculos> servicio = servicioVehiculoRepository.findById(id);
        if (servicio.isPresent()) {
            servicioVehiculoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
