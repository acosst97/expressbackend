package com.express.imp;

import com.express.dto.rutas.ListarRutasDTO;
import com.express.model.Ruta;
import com.express.repository.RutaRepository;
import com.express.services.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutaImp implements RutaService {

    @Autowired
    RutaRepository rutaR;
    @Override
    public List<ListarRutasDTO> listaRutas() {
        List<Ruta> rutas = rutaR.findAll();
        return  rutas.stream().map(ListarRutasDTO::new).collect(Collectors.toList());
    }
}
