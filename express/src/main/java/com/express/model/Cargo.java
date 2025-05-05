package com.express.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCargo;

    private String nombreCargo;
    private String nomina;
    private String funcion;
    private String estadoCargo;

    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")
    private Usuario usuario;
}
