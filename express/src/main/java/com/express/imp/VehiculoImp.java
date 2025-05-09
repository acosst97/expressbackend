package com.express.imp;

import com.express.dto.ListarVehiculoDto;
import com.express.dto.MensajeDTO;
import com.express.dto.vehiculos.RegistroVehiculoDTO;
import com.express.model.Usuario;
import com.express.model.Vehiculo;
import com.express.repository.UsuarioRepository;
import com.express.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoImp  implements VehiculoService {

  @Autowired
  private UsuarioRepository uRepo;
    @Override
    public ResponseEntity<?> registrarVehiculo(RegistroVehiculoDTO registroVehiculoDTO) {
        Usuario usuario = uRepo.findByDocumento(registroVehiculoDTO.getDocumento());
        if (usuario == null){
            return new ResponseEntity<>(new MensajeDTO("No se encontró ningún usuario con el documento proporcionado."), HttpStatus.NOT_FOUND);
        }

        return null;
    }

    @Override
    public void CrearVehiculo(Vehiculo vehiculo) {
    }
    @Override
    public void deleteVehiculo(int idVehiculo) {

    }
    @Override
    public List<ListarVehiculoDto> listartVehiculos() {
        return null;
    }
}
