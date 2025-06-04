package com.express.dto.rutas;

import com.express.dto.reservaciones.ReservacionBasicaDTO;
import com.express.model.Estado;
import com.express.model.Reservacion;
import com.express.model.Ruta;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ListarRutasDTO {

    private int idRuta;
    private String codRuta;
    private Boolean activa;
    private String nombreRuta;
    private String origenRuta;
    private String destinoRuta;


    private List<ReservacionBasicaDTO>reservaciones;
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
        if (ruta.getReservaciones() != null && !ruta.getReservaciones().isEmpty()) {
            this.reservaciones = ruta.getReservaciones().stream()
                    .map(ReservacionBasicaDTO::new) // Convierte cada Reservacion a ReservacionBasicaDTO
                    .collect(Collectors.toList());
        } else {
            this.reservaciones = List.of(); // Devuelve una lista vacía si no hay reservaciones
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

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public List<ReservacionBasicaDTO> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<ReservacionBasicaDTO> reservaciones) {
        this.reservaciones = reservaciones;
    }
}


