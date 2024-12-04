package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.NotFoundException;
import com.es.segurosinseguros.model.Seguro;
import com.es.segurosinseguros.repository.SeguroRepositoryAPI;
import com.es.segurosinseguros.service.SeguroService;
import com.es.segurosinseguros.utils.Mapper;
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

    @Autowired
    private SeguroRepositoryAPI seguroRepository;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @PostMapping
    public ResponseEntity<SeguroDTO> createSeguro(@RequestBody SeguroDTO seguroDTO) {
        if (seguroDTO == null) {
            throw new BadRequestException("El objeto Seguro no puede ser null.");
        }
        SeguroDTO createdSeguro = seguroService.insert(seguroDTO);
        return ResponseEntity.ok(createdSeguro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguroDTO> getByIdSeguro(@PathVariable Long  id) {
        if (id == null) {
            throw new BadRequestException("El ID no puede ser null.");
        }

        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID " + id + " no encontrado"));

        return ResponseEntity.ok(Mapper.seguroEntityToDTO(seguro));
    }

    @GetMapping
    public ResponseEntity<List<SeguroDTO>> getAllSeguros() {
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

