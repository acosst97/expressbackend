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

    @ManyToOne // Reservacion tiene una Ruta
    @JoinColumn(name = "ruta_id") // Columna de clave foránea en la tabla 'reservacion'
    private Ruta ruta;

    public int getIdReservaciones() {
        return idReservaciones;
    }

    public void setIdReservaciones(int idReservaciones) {
        this.idReservaciones = idReservaciones;
    }

    public String getDetallePago() {
        return detallePago;
    }

    public void setDetallePago(String detallePago) {
        this.detallePago = detallePago;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Vehiculo> getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(List<Vehiculo> vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}
