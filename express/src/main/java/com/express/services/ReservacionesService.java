package com.express.services;

import com.express.dto.reservaciones.ListarReservacionesDto;
import com.express.dto.reservaciones.RegistroReservacionesDto;
import com.express.model.Reservacion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservacionesService {

    ResponseEntity<?> registrarReservacion(RegistroReservacionesDto registroReservacionesDto);

    List<ListarReservacionesDto> listarReservaciones();

    ResponseEntity<?> eliminarReservacion(Integer id);
}
