package com.express.imp;

import com.express.dto.MensajeDTO;
import com.express.dto.rutas.ListarRutasDTO;
import com.express.dto.rutas.RegistroRutaDTO;
import com.express.dto.rutas.RutaExcelDTO;
import com.express.dto.rutas.UpdateRutaDTO;
import com.express.model.Estado;
import com.express.model.Reservacion;
import com.express.model.Ruta;
import com.express.repository.EstadoRepository;
import com.express.repository.ReservacionesRepository;
import com.express.repository.RutaRepository;
import com.express.services.RutaService;
import jakarta.transaction.Transactional;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RutaImp implements RutaService {

    @Autowired
    RutaRepository rutaR;
    @Autowired
    EstadoRepository estadoRepo;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    ReservacionesRepository reservacionRepo;
    @Override
    public List<ListarRutasDTO> listaRutas() {
        List<Ruta> rutas = rutaR.findAll();
        return  rutas.stream().map(ListarRutasDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> deleteById(int idRuta) {
            Optional<Ruta> rutaOptional = rutaR.findById(idRuta);
            if (rutaOptional.isEmpty()) {
                return new ResponseEntity<>(new MensajeDTO("No se encontró la ruta con este Id."), HttpStatus.NOT_FOUND);
            }
            Ruta rutaAEliminar = rutaOptional.get();
            if (!rutaAEliminar.getReservaciones().isEmpty()) {
                return new ResponseEntity<>(new MensajeDTO("Error, ruta con reservaciones asociadas."), HttpStatus.CONFLICT);
            }
            rutaR.delete(rutaAEliminar);
            return new ResponseEntity<>(new MensajeDTO("Ruta eliminada exitosamente."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registrarRuta(RegistroRutaDTO registroRutaDTO) {
        Optional<Estado> estadoOptional = estadoRepo.findById(registroRutaDTO.getIdEstado());
        if (estadoOptional.isEmpty()) {
            return new ResponseEntity<>(new MensajeDTO("No se encontró el estado con el ID proporcionado."), HttpStatus.NOT_FOUND);
        }
        Estado estadoAsociado = estadoOptional.get(); // Obtenemos el objeto Estado
        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setCodRuta(registroRutaDTO.getCodRuta());
        nuevaRuta.setNombreRuta(registroRutaDTO.getNombreRuta());
        nuevaRuta.setOrigenRuta(registroRutaDTO.getOrigenRuta());
        nuevaRuta.setDestinoRuta(registroRutaDTO.getDestinoRuta());
        nuevaRuta.setEstado(estadoAsociado);
        if ("ACTIVO".equalsIgnoreCase(estadoAsociado.getNombreEstado())) {
            nuevaRuta.setActiva(true);
        } else {
            nuevaRuta.setActiva(false);
        }
        Ruta rutaGuardada = rutaR.save(nuevaRuta);
        return new ResponseEntity<>(new MensajeDTO("Consumo exitoso"), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<?> updateRuta(UpdateRutaDTO updateRutaDTO) {
        return null;
    }

    @Override
    @Transactional
    public Map<String, Object> uploadRutasFromExcel(MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        List<Ruta> savedRutas = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int errorCount = 0;
        int totalRows = 0;

        // Validar el tipo de archivo
        if (!"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType()) &&
                !"application/vnd.ms-excel".equals(file.getContentType())) {
            response.put("success", false);
            response.put("message", "Tipo de archivo no soportado. Por favor, sube un archivo Excel (.xlsx o .xls).");
            return response;
        }

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream); // Para .xlsx, usar HSSFWorkbook para .xls
            Sheet sheet = workbook.getSheetAt(0); // Obtener la primera hoja

            // Asumiendo que la primera fila es la cabecera
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            DataFormatter formatter = new DataFormatter();
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                totalRows++;

                // Saltar filas vacías (o considerar si tienen pocos datos esenciales)
                if (isRowEmpty(currentRow, formatter)) {
                    totalRows--; // No contar esta fila como válida para procesamiento
                    continue;
                }

                RutaExcelDTO rutaExcelDTO = new RutaExcelDTO();
                String rowErrors = "";

                // Leer celdas. Asegúrate de que los índices de columna coincidan con tu Excel.
                // Columna 0: codRuta
                // Columna 1: nombreRuta
                // Columna 2: origenRuta
                // Columna 3: destinoRuta
                // Columna 4: estadoNombre
                // Columna 5: activa

                try {
                    // Validar y asignar codRuta
                    Cell codRutaCell = currentRow.getCell(0);
                    if (codRutaCell != null && !formatter.formatCellValue(codRutaCell).trim().isEmpty()) {
                        rutaExcelDTO.setCodRuta(formatter.formatCellValue(codRutaCell).trim());
                    } else {
                        rowErrors += "codRuta es obligatorio; ";
                    }

                    // Validar y asignar nombreRuta
                    Cell nombreRutaCell = currentRow.getCell(1);
                    if (nombreRutaCell != null && !formatter.formatCellValue(nombreRutaCell).trim().isEmpty()) {
                        rutaExcelDTO.setNombreRuta(formatter.formatCellValue(nombreRutaCell).trim());
                    } else {
                        rowErrors += "nombreRuta es obligatorio; ";
                    }

                    // Validar y asignar origenRuta
                    Cell origenRutaCell = currentRow.getCell(2);
                    if (origenRutaCell != null && !formatter.formatCellValue(origenRutaCell).trim().isEmpty()) {
                        rutaExcelDTO.setOrigenRuta(formatter.formatCellValue(origenRutaCell).trim());
                    } else {
                        rowErrors += "origenRuta es obligatorio; ";
                    }

                    // Validar y asignar destinoRuta
                    Cell destinoRutaCell = currentRow.getCell(3);
                    if (destinoRutaCell != null && !formatter.formatCellValue(destinoRutaCell).trim().isEmpty()) {
                        rutaExcelDTO.setDestinoRuta(formatter.formatCellValue(destinoRutaCell).trim());
                    } else {
                        rowErrors += "destinoRuta es obligatorio; ";
                    }

                    // Validar y asignar estadoNombre
                    Cell estadoNombreCell = currentRow.getCell(4);
                    if (estadoNombreCell != null && !formatter.formatCellValue(estadoNombreCell).trim().isEmpty()) {
                        rutaExcelDTO.setEstadoNombre(formatter.formatCellValue(estadoNombreCell).trim());
                    } else {
                        rowErrors += "estadoNombre es obligatorio; ";
                    }

                    // Validar y asignar activa
                    Cell activaCell = currentRow.getCell(5);
                    if (activaCell != null) {
                        String activaStr = formatter.formatCellValue(activaCell).trim().toLowerCase();
                        if ("si".equals(activaStr) || "true".equals(activaStr) || "1".equals(activaStr)) {
                            rutaExcelDTO.setActiva(true);
                        } else if ("no".equals(activaStr) || "false".equals(activaStr) || "0".equals(activaStr)) {
                            rutaExcelDTO.setActiva(false);
                        } else {
                            rowErrors += "activa debe ser 'si'/'no' o 'true'/'false'; ";
                        }
                    } else {
                        rowErrors += "activa es obligatorio; ";
                    }

                } catch (Exception e) {
                    rowErrors += "Error leyendo celda: " + e.getMessage() + "; ";
                }

                if (!rowErrors.isEmpty()) {
                    errors.add("Fila " + (currentRow.getRowNum() + 1) + ": " + rowErrors);
                    errorCount++;
                    continue; // Pasa a la siguiente fila si hay errores de lectura/formato
                }

                // Convertir DTO a Entidad Ruta y guardar
                try {
                    Ruta ruta = new Ruta();
                    ruta.setCodRuta(rutaExcelDTO.getCodRuta());
                    ruta.setNombreRuta(rutaExcelDTO.getNombreRuta());
                    ruta.setOrigenRuta(rutaExcelDTO.getOrigenRuta());
                    ruta.setDestinoRuta(rutaExcelDTO.getDestinoRuta());
                    ruta.setActiva(rutaExcelDTO.getActiva());

                    // Buscar el estado por nombre
                    Optional<Estado> estadoOptional = estadoRepository.findByNombreEstado(rutaExcelDTO.getEstadoNombre());
                    if (estadoOptional.isPresent()) {
                        ruta.setEstado(estadoOptional.get());
                    } else {
                        errors.add("Fila " + (currentRow.getRowNum() + 1) + ": Estado '" + rutaExcelDTO.getEstadoNombre() + "' no encontrado.");
                        errorCount++;
                        continue; // Pasa a la siguiente fila
                    }

                    // Opcional: Validar si la ruta ya existe por codRuta antes de guardar
                    if (rutaR.findByCodRuta(ruta.getCodRuta()).isPresent()) {
                        errors.add("Fila " + (currentRow.getRowNum() + 1) + ": La ruta con código '" + ruta.getCodRuta() + "' ya existe.");
                        errorCount++;
                        continue;
                    }

                    rutaR.save(ruta);
                    savedRutas.add(ruta);
                    successCount++;

                } catch (Exception e) {
                    errors.add("Fila " + (currentRow.getRowNum() + 1) + ": Error al guardar la ruta - " + e.getMessage());
                    errorCount++;
                }
            }
            response.put("success", errors.isEmpty());
            response.put("message", "Proceso de carga masiva finalizado.");
            response.put("totalRowsProcessed", totalRows);
            response.put("successfulUploads", successCount);
            response.put("failedUploads", errorCount);
            response.put("errors", errors);
            response.put("savedRutas", savedRutas.stream().map(Ruta::getCodRuta).toList()); // O los IDs, etc.

        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Error al leer el archivo Excel: " + e.getMessage());
            response.put("totalRowsProcessed", 0);
            response.put("successfulUploads", 0);
            response.put("failedUploads", totalRows);
            response.put("errors", List.of("Error de lectura del archivo: " + e.getMessage()));
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ocurrió un error inesperado durante la carga masiva: " + e.getMessage());
            response.put("totalRowsProcessed", totalRows);
            response.put("successfulUploads", successCount);
            response.put("failedUploads", errorCount + (totalRows - successCount - errorCount)); // Cualquier fila no contada
            response.put("errors", List.of("Error general: " + e.getMessage()));
        }
        return response;
    }

    // Método auxiliar para verificar si una fila está completamente vacía
    private boolean isRowEmpty(Row row, DataFormatter formatter) {
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && !formatter.formatCellValue(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;

    }
}
