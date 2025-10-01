package com.express.dto.vehiculos;

public class AsignarVehiculoDTO {

    private int idVehiculo;
    private String documentoUsuario;


    public AsignarVehiculoDTO(int idVehiculo, String documentoUsuario) {
        this.idVehiculo = idVehiculo;
        this.documentoUsuario = documentoUsuario;
    }


    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }
}
