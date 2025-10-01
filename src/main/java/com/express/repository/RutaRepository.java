package com.express.repository;

import com.express.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RutaRepository extends JpaRepository<Ruta,Integer> {

    Optional<Ruta> findById(Integer integer);
    Optional<Ruta> findByCodRuta(String codRuta);
}
