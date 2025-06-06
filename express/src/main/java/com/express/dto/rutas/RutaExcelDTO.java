package com.express.dto.rutas;

public class RutaExcelDTO {
    private String codRuta;
    private String nombreRuta;
    private String origenRuta;
    private String destinoRuta;
    private String estadoNombre;
    private Boolean activa;
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

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "RutaExcelDTO{" +
                "codRuta='" + codRuta + '\'' +
                ", nombreRuta='" + nombreRuta + '\'' +
                ", origenRuta='" + origenRuta + '\'' +
                ", destinoRuta='" + destinoRuta + '\'' +
                ", estadoNombre='" + estadoNombre + '\'' +
                ", activa=" + activa +
                '}';
    }
}
