package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.BadLocationException;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;


    @GetMapping("/{id}")
    public ResponseEntity<SeguroDTO> getById(@PathVariable String id) {

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

    @PostMapping
    public ResponseEntity<SeguroDTO> crearSeguro(@RequestBody SeguroDTO seguroDTO) {
        // Validaciones previas si las necesitas
        // Ejemplo: verificar que el nif tiene el formato adecuado, edad mayor que 0, etc.

        // Llamada al servicio para crear el seguro
        SeguroDTO nuevoSeguro = seguroService.crearSeguro(seguroDTO);

        // Retornar el seguro creado con un código de estado 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSeguro);
    }


}
