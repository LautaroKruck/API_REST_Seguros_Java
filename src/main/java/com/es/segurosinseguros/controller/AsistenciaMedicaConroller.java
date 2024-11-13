package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.service.AsistenciaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class AsistenciaMedicaConroller {

    @Autowired
    private AsistenciaMedicaService asistenciaMedicaService;

    @PostMapping
    public ResponseEntity<AsistenciaMedicaDTO> crearAsistencia(@RequestBody AsistenciaMedicaDTO asistenciaMedicaDTO) {
        // Validaciones previas si las necesitas
        // Ejemplo: verificar que el seguro asociado existe, importe mayor que 0, etc.

        // Llamada al servicio para crear la asistencia médica
        AsistenciaMedicaDTO nuevaAsistencia = asistenciaMedicaService.crearAsistencia(asistenciaMedicaDTO);

        // Retornar la asistencia creada con un código de estado 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsistencia);
    }
}
