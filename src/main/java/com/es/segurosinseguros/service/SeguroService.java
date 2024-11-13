package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.repository.SeguroRepositoryAPI;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepositoryAPI seguroRepository;

    // Crear un seguro
    public SeguroDTO crearSeguro(SeguroDTO seguroDTO) {
        return null;
    }

    // Consultar un seguro por su identificador
    public SeguroDTO consultarSeguroPorId(Long idSeguro) {
        return null;
    }

    // Listar todos los seguros
    public List<SeguroDTO> listarTodosLosSeguros() {
        return null;
    }

    // Actualizar un seguro existente
    public SeguroDTO actualizarSeguro(Long idSeguro, SeguroDTO seguroDTO) {
        return null;
    }

    // Eliminar un seguro
    public void eliminarSeguro(Long idSeguro) {

    }

    // Método de validación de seguro
    private void validarSeguro(SeguroDTO seguroDTO) {

    }
}

