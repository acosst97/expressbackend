package com.express.services;

import com.express.dto.MensajeDTO;
import com.express.dto.reservaciones.ListarReservacionesDto;
import com.express.dto.reservaciones.RegistroReservacionesDto;
import com.express.dto.vehiculos.ActualizarVehiculoDTO;
import com.express.dto.vehiculos.AsignarVehiculoDTO;
import com.express.dto.vehiculos.ListarVehiculosDTO;
import com.express.dto.vehiculos.RegistroVehiculoDTO;
import com.express.model.Usuario;
import com.express.model.Vehiculo;
import com.express.repository.UsuarioRepository;
import com.express.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ReservacionesService {

    ResponseEntity<?> registrarReservacion(RegistroReservacionesDto registroReservacionesDto);

    List<ListarReservacionesDto> listarReservaciones();

    ResponseEntity<?> eliminarReservacion(Integer id);


}
