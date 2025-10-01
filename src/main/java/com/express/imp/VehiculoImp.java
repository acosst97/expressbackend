package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.vehiculos.ActualizarVehiculoDTO;
import com.express.dto.vehiculos.AsignarVehiculoDTO;
import com.express.dto.vehiculos.ListarVehiculosDTO;
import com.express.dto.vehiculos.RegistroVehiculoDTO;
import com.express.model.Usuario;
import com.express.model.Vehiculo;
import com.express.repository.UsuarioRepository;
import com.express.repository.VehiculoRepository;
import com.express.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class VehiculoImp implements VehiculoService {
    @Autowired
    private UsuarioRepository uRepo;
    @Autowired
    private VehiculoRepository vRepo;
    @Override
    public ResponseEntity<?> registrarVehiculo(RegistroVehiculoDTO registroVehiculoDTO) {
        Usuario usuario = uRepo.findByDocumento(registroVehiculoDTO.getDocumento());
        if (usuario == null){
            return new ResponseEntity<>(new MensajeDTO("No se encontró ningún usuario con el documento proporcionado."), HttpStatus.NOT_FOUND);
        }
        Vehiculo vehiculo  = new Vehiculo();
        vehiculo.setCapacidad(registroVehiculoDTO.getCapacidad());
        vehiculo.setDocumentacion(registroVehiculoDTO.getDocumentacion());
        vehiculo.setDocBase64(registroVehiculoDTO.getDocBase64());
        vehiculo.setPlacaVehiculo(registroVehiculoDTO.getPlacaVehiculo());
        vehiculo.setSeguroVig(registroVehiculoDTO.getSeguroVig());
        vehiculo.setModelo(registroVehiculoDTO.getModelo());
         vehiculo.setUsuario(usuario);
        Vehiculo newVehiculo = vRepo.save(vehiculo);
        return new ResponseEntity<>(new MensajeDTO("Vehiculo registrada exitosamente."), HttpStatus.CREATED);
    }

    @Override
    public void deleteVehiculo(int idVehiculo) {
        Optional<Vehiculo> vehiculoOptional = vRepo.findById(idVehiculo);
        if (vehiculoOptional.isPresent()) {
            vRepo.deleteById(idVehiculo);
        }
    }

    @Override
    public ResponseEntity<?> updateVehiculo(ActualizarVehiculoDTO actualizarVehiculoDTO) {
        Optional<Vehiculo> vehiculoOptional = vRepo.findById(actualizarVehiculoDTO.getIdVehiculo());
        if (vehiculoOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el vehículo con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        Vehiculo vehiculo = vehiculoOptional.get();

        if (!vehiculo.getUsuario().getDocumento().equals(actualizarVehiculoDTO.getDocumento())) {
            return new ResponseEntity<>(new MensajeDTO("No tienes permiso para actualizar este vehículo."), HttpStatus.FORBIDDEN);
        }
        vehiculo.setCapacidad(actualizarVehiculoDTO.getCapacidad());
        vehiculo.setDocumentacion(actualizarVehiculoDTO.getDocumentacion());
        vehiculo.setPlacaVehiculo(actualizarVehiculoDTO.getPlacaVehiculo());
        vehiculo.setSeguroVig(actualizarVehiculoDTO.getSeguroVig());
        vehiculo.setModelo(actualizarVehiculoDTO.getModelo());
        Vehiculo vehiculoActualizado = vRepo.save(vehiculo);
        return new ResponseEntity<>(new MensajeDTO("Vehículo actualizado exitosamente."), HttpStatus.OK);
    }

    @Override
    public List<ListarVehiculosDTO> listartVehiculos() {
        List<Vehiculo> vehiculos = vRepo.findAll();
        return vehiculos.stream().map(ListarVehiculosDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> asignarVehiculo(AsignarVehiculoDTO asignarVehiculoDTO) {
        Optional<Vehiculo> vehiculoOptional = vRepo.findById(asignarVehiculoDTO.getIdVehiculo());
        Optional<Usuario> usuarioOptional = Optional.ofNullable(uRepo.findByDocumento(asignarVehiculoDTO.getDocumentoUsuario()));
        if (vehiculoOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el vehículo con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }

        if (usuarioOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el usuario con el documento proporcionado."), HttpStatus.NOT_FOUND);
        }

        Vehiculo vehiculo = vehiculoOptional.get();
        Usuario usuario = usuarioOptional.get();
        vehiculo.setUsuario(usuario);
        vRepo.save(vehiculo);
        return new ResponseEntity<>(new MensajeDTO("Vehículo asignado exitosamente al usuario."), HttpStatus.OK);
    }

    @Override
    public Optional<Vehiculo> obtenerVehiculoPorId(int idVehiculo) {
        return vRepo.findById(idVehiculo);
    }
}
