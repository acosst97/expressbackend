package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.estado.ActualizarDto;
import com.express.dto.estado.ListarEstado;
import com.express.dto.estado.RegistrarEstadoDto;
import com.express.dto.rutas.ListarRutasDTO;
import com.express.model.Estado;
import com.express.model.Ruta;
import com.express.repository.EstadoRepository;
import com.express.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadoImp implements EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public ResponseEntity<?> RegistrarEstado(RegistrarEstadoDto registrarEstadoDto) {
        try{
            Estado estado = new Estado();
            estado.setNombreEstado(registrarEstadoDto.getNombreEstado());
            estado.setDescripcionEstado(registrarEstadoDto.getDescripcionEstado());
            Estado e = estadoRepository.save(estado);
            return new ResponseEntity<>(new MensajeDTO("Estado registrada exitosamente."), HttpStatus.CREATED);
        }catch (Exception e){
            System.err.println("Error al registrar el estado: " + e.getMessage());
            return new ResponseEntity<>(new MensajeDTO("Ocurrió un error al registrar el estado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ListarEstado> listarEstados() {
        List<Estado> estados = estadoRepository.findAll();
      return estados.stream().map(ListarEstado::new).collect(Collectors.toList());
    }
    @Override
    public ResponseEntity<?> updateEstado(ActualizarDto dto) {
        Optional<Estado> estadoOpt = estadoRepository.findById(dto.getIdEstado());
        if (estadoOpt.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("Estado no encontrado"), HttpStatus.NOT_FOUND);
        }
        Estado estado = estadoOpt.get();
        estado.setNombreEstado(dto.getNombreEstado());
        estado.setDescripcionEstado(dto.getDescripcionEstado());
        estadoRepository.save(estado);
        return new ResponseEntity<>(new MensajeDTO("Estado actualizado exitosamente"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> eliminarEstado(Integer id) {
        if (!estadoRepository.existsById(id)) {
            return new ResponseEntity<>(new MensajeDTO("Estado no encontrado"), HttpStatus.NOT_FOUND);
        }
        estadoRepository.deleteById(id);
        return new ResponseEntity<>(new MensajeDTO("Consumo Exitoso"), HttpStatus.OK);
    }

}
