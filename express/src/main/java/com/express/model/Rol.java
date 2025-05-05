package com.express.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    private String nombreRol;
    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")// Nombre de la columna de clave foránea en la tabla usuario
    private Usuario usuario;
   /* @OneToMany(mappedBy = "rolId") // Referencia a la columna en Usuario
    private List<Usuario> usuarios;*/

}
