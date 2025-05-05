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
@Table(name="ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRuta;

    private String codRuta;
    private String nombreRuta;

    private String origenRuta;
    private String destinoRuta;
    private  String estadoRuta;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
    @OneToOne
    @JoinColumn(name = "reservaciones_id_reservaciones", unique = true) // Columna de clave foránea en la tabla 'ruta'
    private Reservacion reservacion;

}
