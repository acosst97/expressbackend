package com.express.dto.rutas;

import com.express.model.Ruta;

public class RutaBasicaDTO {
    private int idRuta; // O Long
    private String codRuta;
    private String nombreRuta;
    private String origenRuta;
    private String destinoRuta;

    public RutaBasicaDTO(Ruta ruta) {
        this.idRuta = ruta.getIdRuta();
        this.codRuta = ruta.getCodRuta();
        this.nombreRuta = ruta.getNombreRuta();
        this.origenRuta = ruta.getOrigenRuta();
        this.destinoRuta = ruta.getDestinoRuta();
    }

    // --- Getters y Setters para RutaBasicaDTO ---
    public int getIdRuta() { return idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }
    public String getCodRuta() { return codRuta; }
    public void setCodRuta(String codRuta) { this.codRuta = codRuta; }
    public String getNombreRuta() { return nombreRuta; }
    public void setNombreRuta(String nombreRuta) { this.nombreRuta = nombreRuta; }
    public String getOrigenRuta() { return origenRuta; }
    public void setOrigenRuta(String origenRuta) { this.origenRuta = origenRuta; }
    public String getDestinoRuta() { return destinoRuta; }
    public void setDestinoRuta(String destinoRuta) { this.destinoRuta = destinoRuta; }
}

