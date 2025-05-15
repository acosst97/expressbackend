package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.rutas.ListarRutasDTO;
import com.express.dto.rutas.RegistroRutaDTO;
import com.express.dto.rutas.UpdateRutaDTO;
import com.express.model.Estado;
import com.express.model.Reservacion;
import com.express.model.Ruta;
import com.express.repository.EstadoRepository;
import com.express.repository.ReservacionesRepository;
import com.express.repository.RutaRepository;
import com.express.services.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RutaImp implements RutaService {

    @Autowired
    RutaRepository rutaR;
    @Autowired
    EstadoRepository estadoRepo;

    @Autowired
    ReservacionesRepository reservacionRepo;
    @Override
    public List<ListarRutasDTO> listaRutas() {
        List<Ruta> rutas = rutaR.findAll();
        return  rutas.stream().map(ListarRutasDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> deleteById(int IdRuta) {
        return null;
    }

    @Override
    public ResponseEntity<?> registrarRuta(RegistroRutaDTO registroRutaDTO) {
        Optional<Estado> estadoOptional = estadoRepo.findById(registroRutaDTO.getIdEstado());
        if (estadoOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el estado con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }

        Reservacion reservacion = null;
        if (RegistroRutaDTO.getReservacionesIdReservaciones() != null) {
            Optional<Reservacion> reservacionOptional = reservacionRepo.findById(registroRutaDTO.getReservacionesIdReservaciones());
            if (reservacionOptional.isEmpty()) {
                return new ResponseEntity<>(new MensajeDTO("No se encontró la reservación con el ID proporcionado."), HttpStatus.NOT_FOUND);
            }
            reservacion = reservacionOptional.get();
        }

        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setCodRuta(registroRutaDTO.getCodRuta());
        nuevaRuta.setNombreRuta(registroRutaDTO.getNombreRuta());
        nuevaRuta.setOrigenRuta(registroRutaDTO.getOrigenRuta());
        nuevaRuta.setDestinoRuta(registroRutaDTO.getDestinoRuta());
        nuevaRuta.setEstadoRuta(registroRutaDTO.getEstadoRuta());
        nuevaRuta.setEstado(estadoOptional.get());
        nuevaRuta.setReservacion(reservacion);

        Ruta rutaGuardada = rutaR.save(nuevaRuta);
        return new ResponseEntity<>(new MensajeDTO("Ruta creada exitosamente."), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<?> updateRuta(UpdateRutaDTO updateRutaDTO) {
        return null;
    }
}
