package com.express.dto.serviciosDto;

public class CrearServicioVehiculoDto {


   private String nombreServicio;

   private String valorServicio;
   private String descripcion;
   private String images;

   public  void registrarServicioVehiculoDto(String nombreServicio, String valorServicio, String descripcion,String images){
      this.nombreServicio = nombreServicio;
      this.valorServicio = valorServicio;
      this.descripcion = descripcion;
      this.images = images;
   }

   public String getNombreServicio() {
      return nombreServicio;
   }

   public void setNombreServicio(String nombreServicio) {
      nombreServicio = nombreServicio;
   }

   public String getValorServicio() {
      return valorServicio;
   }

   public void setValorServicio(String valorServicio) {
      this.valorServicio = valorServicio;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getImages() {
      return images;
   }

   public void setImages(String images) {
      this.images = images;
   }

   public CrearServicioVehiculoDto(String nombreServicio, String valorServicio, String descripcion) {
      this.nombreServicio = nombreServicio;
      this.valorServicio = valorServicio;
      this.descripcion = descripcion;
   }
}
