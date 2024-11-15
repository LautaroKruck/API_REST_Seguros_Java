package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.error.ErrorGenerico;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.NotFoundException;
import com.es.segurosinseguros.service.AsistenciaMedicaService;
import com.es.segurosinseguros.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AsistenciaMedicaConroller {

    @Autowired
    private AsistenciaMedicaService asistenciaMedicaService;

    public AsistenciaMedicaConroller(AsistenciaMedicaService asistenciaMedicaServiceService) {
        this.asistenciaMedicaService = asistenciaMedicaServiceService;
    }

    @PostMapping
    public ResponseEntity<AsistenciaMedicaDTO> insert(@RequestBody AsistenciaMedicaDTO asistenciaMedicaDTO) {
        // Validaciones previas si las necesitas
        // Ejemplo: verificar que el seguro asociado existe, importe mayor que 0, etc.

        // Llamada al servicio para crear la asistencia médica
        AsistenciaMedicaDTO nuevaAsistencia = asistenciaMedicaService.insert(asistenciaMedicaDTO);

        // Retornar la asistencia creada con un código de estado 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsistencia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaMedicaDTO> getById(@PathVariable String id) {

        // Compruebo que el id no es null

        if (id == null || id.equals("a")) {
            // LANZO UNA EXCEPCION PROPIA
            /*
                a) Qué código de estado devolverías --> BAD_REQUEST (400)
                b) Qué información daríais al cliente
                    --> Un mensaje: "id no válido" "El id no puede ser null"
                    --> la URI: localhost:8080/seguros/x
                c) Nombre a nuestra excepción --> BadRequestException
             */
            throw new BadRequestException("El campo NIF no tiene un formato válido");
        }
        return null;
    }

    @GetMapping
    public List<AsistenciaMedicaDTO> getAll() {
        return asistenciaMedicaService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AsistenciaMedicaDTO asistenciaMedicaDTO) {

        try {
            AsistenciaMedicaDTO updatedAsistencia = AsistenciaMedicaService.update(id, asistenciaMedicaDTO);
            return new ResponseEntity<>(updatedAsistencia, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorGenerico(e.getMessage(), "/seguros/" + id), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        asistenciaMedicaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
