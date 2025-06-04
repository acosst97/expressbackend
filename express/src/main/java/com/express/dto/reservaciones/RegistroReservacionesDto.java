package com.express.dto.reservaciones;

import com.express.model.Reservacion;

import java.util.ArrayList;
import java.util.List;

public class RegistroReservacionesDto {


    private String detallePago;
    private String  valorPago;
    private String fechaReserva;
    private String fechaViaje;
    private String documentoUsuario;
    private int idRuta;

    public RegistroReservacionesDto(String detallePago, String valorPago, String fechaReserva, String fechaViaje, String documentoUsuario, int idRuta) {
        this.detallePago = detallePago;
        this.valorPago = valorPago;
        this.fechaReserva = fechaReserva;
        this.fechaViaje = fechaViaje;
        this.documentoUsuario = documentoUsuario;
        this.idRuta = idRuta;
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

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }
}
