package com.express.imp;

import com.express.model.Rol;
import com.express.repository.RolRepository;
import com.express.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolImp implements RolService {

    @Autowired
    private RolRepository rolRepository;


    @Override
    public List<Rol> buscartodos() {
        return rolRepository.findAll();
    }
}
