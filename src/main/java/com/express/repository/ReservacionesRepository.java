package com.express.repository;

import com.express.model.Reservacion;
import com.express.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservacionesRepository extends JpaRepository<Reservacion,Integer> {

    Optional<Ruta> findById(int idRuta);
}
