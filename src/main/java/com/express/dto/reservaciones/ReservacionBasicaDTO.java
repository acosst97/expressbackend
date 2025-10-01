package com.express.dto.reservaciones;

import com.express.model.Reservacion;

public class ReservacionBasicaDTO {
    private int idReservacion;
    private String detallePago;
    private String valorPago;
    private String fechaReserva;
    private String fechaViaje;

    public ReservacionBasicaDTO(Reservacion reservacion) {
        this.idReservacion = reservacion.getIdReservaciones();
        this.detallePago = reservacion.getDetallePago();

        try {
            this.valorPago = (reservacion.getValorPago());
        } catch (NumberFormatException e) {
            this.valorPago = null;
        }
        this.fechaReserva = (reservacion.getFechaReserva());
        this.fechaViaje = (reservacion.getFechaViaje());
    }

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
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
}
