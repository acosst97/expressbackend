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
@Table(name="vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehiculo;
    private int Capacidad;
    private String documentacion;
    private String placaVehiculo;
    private String seguroVig;
    private String modelo;


    @ManyToOne
    @JoinColumn(name = "usuario_idusuario") // Nombre de la columna de clave foránea en la tabla Vehiculo
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "reservaciones_id_reservaciones")
    private Reservacion reservacion;

}
