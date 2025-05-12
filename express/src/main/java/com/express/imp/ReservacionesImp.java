package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.reservaciones.ListarReservacionesDto;
import com.express.dto.reservaciones.RegistroReservacionesDto;
import com.express.model.Reservacion;
import com.express.model.Usuario;
import com.express.repository.ReservacionesRepository;
import com.express.repository.UsuarioRepository;
import com.express.services.ReservacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservacionesImp implements ReservacionesService {
  @Autowired
  private ReservacionesRepository rRepo;
    @Autowired
    private UsuarioRepository uRepo;
    @Override
    public ResponseEntity<?> registrarReservacion(RegistroReservacionesDto registroReservacionesDto) {

        Usuario usuario = uRepo.findByDocumento(registroReservacionesDto.getDocumentoUsuario());

        if (usuario == null) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró ningún usuario con el documento proporcionado."), HttpStatus.NOT_FOUND);
        }

        Reservacion reservacion = new Reservacion();
        reservacion.setDetallePago(registroReservacionesDto.getDetallePago());
        reservacion.setValorPago(registroReservacionesDto.getValorPago());
        reservacion.setFechaReserva(registroReservacionesDto.getFechaReserva());
        reservacion.setFechaViaje(registroReservacionesDto.getFechaViaje());
        reservacion.setUsuario(usuario); // Asocia la reservación con el usuario encontrado

        Reservacion nuevaReser = rRepo.save(reservacion);
        return new ResponseEntity<>(new MensajeDTO("Reservación registrada exitosamente."), HttpStatus.CREATED);
    }


    @Override
    public List<ListarReservacionesDto> listarReservaciones() {
        List<Reservacion> reservacion  = rRepo.findAll();
        return  reservacion.stream().map(ListarReservacionesDto::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> eliminarReservacion(Integer id) {
        Optional<Reservacion> reservacionOptional = rRepo.findById(id);

        if (reservacionOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró ninguna reservación con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }

        try {
            rRepo.deleteById(id);
            return new ResponseEntity<>(new MensajeDTO("Reservación eliminada exitosamente."), HttpStatus.OK);
        } catch (Exception e) {
            // En caso de algún error al eliminar (ej: violación de clave foránea)
            return new ResponseEntity<>(new MensajeDTO("Ocurrió un error al intentar eliminar la reservación."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
