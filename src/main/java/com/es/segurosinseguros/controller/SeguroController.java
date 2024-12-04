package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguroDTO> getByIdSeguro(@PathVariable Long  id) {

        // Compruebo que el id no es null

        if (id == null || id.equals("a")) {
            if (id == null || id <= 0) {
                throw new BadRequestException("El ID proporcionado no es válido.");
            }
            throw new BadRequestException("El campo NIF no tiene un formato válido");
        }
        SeguroDTO seguro = seguroService.findById(id);
        return ResponseEntity.ok(seguro);
    }

    @PostMapping
    public ResponseEntity<SeguroDTO> createSeguro(@RequestBody SeguroDTO seguroDTO) {
        if (seguroDTO == null) {
            throw new BadRequestException("El objeto Seguro no puede ser null.");
        }
        SeguroDTO nuevoSeguro = seguroService.insert(seguroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSeguro);
    }

    @GetMapping
    public ResponseEntity<List<SeguroDTO>> getAllSeguro() {
        List<SeguroDTO> seguros = seguroService.getAll();
        return ResponseEntity.ok(seguros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguroDTO> updateSeguro(@PathVariable Long id, @RequestBody SeguroDTO seguroDTO) {
        if (id == null) {
            throw new BadRequestException("El ID no puede ser null.");
        }
        if (seguroDTO == null) {
            throw new BadRequestException("El objeto Seguro no puede ser null.");
        }

        SeguroDTO updatedSeguro = seguroService.update(id, seguroDTO);
        return ResponseEntity.ok(updatedSeguro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeguro(@PathVariable Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no puede ser null.");
        }

        seguroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

