package com.express.dto.reservaciones;

import com.express.model.Reservacion;
import com.express.model.Vehiculo;

import java.util.List;

public class ListarReservacionesDto {

    private String detallePago;
    private String  valorPago;
    private String fechaReserva;
    private String fechaViaje;
    private List<Integer> capacidad;
    private Vehiculo vehiculo;
    private Integer idReservaciones;
    private Integer idUsuario;
    private String documentoUsuario;
    private String primerNombre;
    private String primerApellido;
    public ListarReservacionesDto(Reservacion reservacion) {
        this.idReservaciones = reservacion.getIdReservaciones();
        this.detallePago = reservacion.getDetallePago();
        this.valorPago = reservacion.getValorPago();
        this.fechaReserva  = reservacion.getFechaReserva();
        this.fechaViaje = reservacion.getFechaViaje();
        if (reservacion.getUsuario() != null) {
            this.idUsuario = reservacion.getUsuario().getIdUsuario();
            this.documentoUsuario = reservacion.getUsuario().getDocumento();
            this.primerNombre = reservacion.getUsuario().getPrimerNombre();
            this.primerApellido = reservacion.getUsuario().getPrimerApellido();
        }
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

    public List<Integer> getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(List<Integer> capacidad) {
        this.capacidad = capacidad;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Integer getIdReservaciones() {
        return idReservaciones;
    }

    public void setIdReservaciones(Integer idReservaciones) {
        this.idReservaciones = idReservaciones;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
}
