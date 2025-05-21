package com.express.imp;

import com.express.dto.rol.ListaRolDTO;
import com.express.model.Rol;
import com.express.repository.RolRepository;
import com.express.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolImp implements RolService {

    @Autowired
    private RolRepository rolRepository;


    @Override
    public List<ListaRolDTO> buscartodos() {
        List<Rol> rol =  rolRepository.findAll();
        return rol.stream().map(ListaRolDTO::new).collect(Collectors.toList());
    }
}
