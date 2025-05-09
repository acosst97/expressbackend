package com.express.repository;

import com.express.model.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservacionesRepository extends JpaRepository<Reservacion,Integer> {

}
