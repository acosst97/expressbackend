package com.express.repository;

import com.express.model.ServicioVehiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioVehiculoRepository extends JpaRepository<ServicioVehiculos,Integer> {
}
