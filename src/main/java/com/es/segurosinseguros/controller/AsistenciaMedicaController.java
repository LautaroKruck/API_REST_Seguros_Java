package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.model.Seguro;
import com.es.segurosinseguros.service.AsistenciaMedicaService;
import com.es.segurosinseguros.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaMedicaController {
    @Autowired
    private SeguroService seguroService;

    @Autowired
    private AsistenciaMedicaService asistenciaMedicaService;

    @PostMapping
    public ResponseEntity<AsistenciaMedicaDTO> createAsistencia(@RequestBody AsistenciaMedicaDTO asistenciaDTO) {

        if (asistenciaDTO == null) {
            throw new BadRequestException("El objeto Asistencia no puede ser null.");
        }

        if (!seguroService.existsById(asistenciaDTO.getIdSeguro())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 si no existe el seguro
        }

        // Creamos la nueva asistencia
        AsistenciaMedicaDTO createdAsistencia = asistenciaMedicaService.insert(asistenciaDTO);

        // Retornamos la asistencia creada con el estado 201
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAsistencia);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaMedicaDTO> getByIdAsistencia(@PathVariable Long id) {

        // Compruebo que el id no es null
        if (id == null) {
            throw new BadRequestException("El ID no puede ser null.");
        }
        AsistenciaMedicaDTO asistencia = asistenciaMedicaService.findById(id);
        return ResponseEntity.ok(asistencia);
    }

    @GetMapping
    public ResponseEntity<List<AsistenciaMedicaDTO>> getAllAsistencias() {
        List<AsistenciaMedicaDTO> asistencias = asistenciaMedicaService.getAll();
        return ResponseEntity.ok(asistencias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaMedicaDTO> updateAsistencia(@PathVariable Long id, @RequestBody AsistenciaMedicaDTO asistenciaDTO) {
        if (id == null) {
            throw new BadRequestException("El ID no puede ser null.");
        }
        if (asistenciaDTO == null) {
            throw new BadRequestException("El objeto Asistencia no puede ser null.");
        }

        AsistenciaMedicaDTO updatedAsistencia = asistenciaMedicaService.update(id, asistenciaDTO);
        return ResponseEntity.ok(updatedAsistencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no puede ser null.");
        }

        asistenciaMedicaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
