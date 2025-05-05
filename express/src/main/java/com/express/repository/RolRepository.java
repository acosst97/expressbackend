package com.express.repository;

import com.express.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {
    public Rol findBynombreRol(Rol rol);

}
