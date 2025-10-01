package com.express.repository;

import com.express.model.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {
    Optional<Estado> findByNombreEstado(String nombreEstado);
}
