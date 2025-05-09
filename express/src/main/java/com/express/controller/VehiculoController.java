package com.express.controller;

import com.express.repository.UsuarioRepository;
import com.express.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehiculos")
@CrossOrigin
public class VehiculoController {


     @Autowired
    private UsuarioRepository userR;
     @Autowired
    private VehiculoService vSrv;





}
