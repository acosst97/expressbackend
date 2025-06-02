package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.serviciosDto.CrearServicioVehiculoDto;
import com.express.dto.serviciosDto.ListarServicioVehiculoDto;

import com.express.dto.serviciosDto.UpdateServicesVehiculoDto;
import com.express.model.ServicioVehiculos;
import com.express.repository.ServicioVehiculoRepository;
import com.express.services.ServicioVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                .map(ListarServicioVehiculoDto::new)
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
    public ResponseEntity<?> updateServiceVehicle(UpdateServicesVehiculoDto updateServicesVehiculoDto) {
        Optional<ServicioVehiculos> servicesOption = servicioVehiculoRepository.findById(updateServicesVehiculoDto.getIdServicio());
        if (servicesOption.isEmpty()){
            return new ResponseEntity<>(new MensajeDTO("No se encontró el servicio con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        ServicioVehiculos service = servicesOption.get();
        service.setNombreServicio(updateServicesVehiculoDto.getNombreServicio());
        service.setValorServicio(updateServicesVehiculoDto.getValorServicio());
        service.setDescripcion(updateServicesVehiculoDto.getDescripcion());
        service.setImages(updateServicesVehiculoDto.getImages());
        ServicioVehiculos serviceUpdate = servicioVehiculoRepository.save(service);
        return new ResponseEntity<>(new MensajeDTO("Consumo exitoso"), HttpStatus.OK);
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
