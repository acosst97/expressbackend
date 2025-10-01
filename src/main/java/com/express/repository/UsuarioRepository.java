package com.express.repository;

import com.express.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
   public Usuario findByCorreo(String correo);
    Usuario findByDocumento(String documento);
    /*recuperacion de contraseña*/

    public Usuario findByResetPasswordToken(String token);

}
