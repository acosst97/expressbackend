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
@Table(name="reservaciones")
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservaciones;
    private String detallePago;
    private String  valorPago;
    private String fechaReserva;
    private String fechaViaje;

    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")
    private Usuario usuario;
  //
    @OneToMany(mappedBy = "reservacion") //es el nombre del atributo en la clase vehiculo que mapea esta relación
    private List<Vehiculo> vehiculo;

    @OneToOne(mappedBy = "reservacion") // Indica que la relación ya está gestionada por el atributo 'reservacion' en Ruta
    private Ruta ruta;
}
