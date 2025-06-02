package com.express.services;

import com.express.dto.estado.ActualizarDto;
import com.express.dto.estado.ListarEstado;
import com.express.dto.estado.RegistrarEstadoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstadoService {
    ResponseEntity<?> RegistrarEstado(RegistrarEstadoDto registrarEstadoDto);
    List<ListarEstado> listarEstados();
    ResponseEntity<?> updateEstado(ActualizarDto actualizarDto);
    ResponseEntity<?> eliminarEstado(Integer id);

}
