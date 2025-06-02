package com.express.dto.rutas;

import com.express.model.Estado;
import com.express.model.Reservacion;
import com.express.model.Ruta;
import lombok.Data;

@Data
public class ListarRutasDTO {
    private int idRuta;
    private String codRuta;
    private Boolean activa;
    private String nombreRuta;
    private String origenRuta;
    private String destinoRuta;
    private Estado estado;
    private String nombreEstado;
    private String fechaReserva;
    private String FechaViaje;
    private String valorPago;
    public  ListarRutasDTO(Ruta ruta){
        this.idRuta = ruta.getIdRuta();
        this.codRuta = ruta.getCodRuta();
        this.nombreRuta = ruta.getNombreRuta();
        this.origenRuta = ruta.getOrigenRuta();
        this.destinoRuta = ruta.getDestinoRuta();
        this.activa  = ruta.getActiva();
        if (ruta.getEstado() != null){
            ruta.getEstado().getNombreEstado();
        }
        if (ruta.getReservacion() != null){
            ruta.getReservacion().getFechaReserva();
            ruta.getReservacion().getFechaViaje();
            ruta.getReservacion().getValorPago();
            ruta.getReservacion().getUsuario().getDocumento();
        }
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(String codRuta) {
        this.codRuta = codRuta;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getOrigenRuta() {
        return origenRuta;
    }

    public void setOrigenRuta(String origenRuta) {
        this.origenRuta = origenRuta;
    }

    public String getDestinoRuta() {
        return destinoRuta;
    }

    public void setDestinoRuta(String destinoRuta) {
        this.destinoRuta = destinoRuta;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaViaje() {
        return FechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        FechaViaje = fechaViaje;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}


