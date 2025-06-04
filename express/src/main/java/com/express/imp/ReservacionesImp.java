package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.reservaciones.ListarReservacionesDto;
import com.express.dto.reservaciones.RegistroReservacionesDto;
import com.express.model.Reservacion;
import com.express.model.Ruta;
import com.express.model.Usuario;
import com.express.repository.ReservacionesRepository;
import com.express.repository.RutaRepository;
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
    @Autowired
    private RutaRepository rutaRepository;
    @Override
    public ResponseEntity<?> registrarReservacion(RegistroReservacionesDto registroReservacionesDto) {
        Usuario usuario = uRepo.findByDocumento(registroReservacionesDto.getDocumentoUsuario());
        if (usuario == null) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró ningún usuario con el documento proporcionado."), HttpStatus.NOT_FOUND);
        }

        Optional<Ruta> rutaOptional = rutaRepository.findById(registroReservacionesDto.getIdRuta());
        if (rutaOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró la ruta con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        Ruta ruta = rutaOptional.get();
        Reservacion reservacion = new Reservacion();
        reservacion.setDetallePago(registroReservacionesDto.getDetallePago());
        reservacion.setValorPago(registroReservacionesDto.getValorPago());
        reservacion.setFechaReserva(registroReservacionesDto.getFechaReserva());
        reservacion.setFechaViaje(registroReservacionesDto.getFechaViaje());
        reservacion.setUsuario(usuario); // Asocia la reservación con el usuario encontrado
        reservacion.setRuta(ruta); // Asocia la reservación con la ruta encontrada

        // Opcional: Si quieres mantener la lista en Ruta actualizada (y no solo la FK en Reservacion)
        //ruta.addReservacion(reservacion); // Esto es útil si cargas la Ruta y quieres ver sus reservaciones.
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
        if (id == null) {
            return new ResponseEntity<>(new MensajeDTO("El ID de la reservación no puede ser nulo."), HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Reservacion> reservacionOptional = rRepo.findById(id);
            if (reservacionOptional.isEmpty()) {
                return new ResponseEntity<>(new MensajeDTO("No se encontró ninguna reservación con el ID proporcionado."), HttpStatus.NOT_FOUND);
            }
            Reservacion reservacionAEliminar = reservacionOptional.get();
            // // b) No permitir eliminar si la reservación está en un estado "Confirmado" o "En Curso"
            // //    Necesitarías un campo 'estado' en tu entidad Reservacion para esto
            // if (reservacionAEliminar.getEstado().equals("CONFIRMADA") || reservacionAEliminar.getEstado().equals("EN_CURSO")) {
            //     return new ResponseEntity<>(new MensajeDTO("No se puede eliminar una reservación con estado '" + reservacionAEliminar.getEstado() + "'."), HttpStatus.FORBIDDEN);
            // }
            rRepo.deleteById(id);
            return new ResponseEntity<>(new MensajeDTO("Reservación eliminada exitosamente."), HttpStatus.OK);
        } catch (Exception e) {
            //    logger.error("Error al eliminar la reservación con ID " + id + ": " + e.getMessage(), e);
            return new ResponseEntity<>(new MensajeDTO("Error interno del servidor al intentar eliminar la reservación."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
