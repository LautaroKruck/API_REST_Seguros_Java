package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.repository.AsistenciaMedicaRepositoryAPI;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AsistenciaMedicaService {

    @Autowired
    private AsistenciaMedicaRepositoryAPI asistenciaMedicaRepository;

    // Crear una asistencia médica asociada a un seguro
    public AsistenciaMedicaDTO crearAsistencia(AsistenciaMedicaDTO asistenciaMedicaDTO) {
        return null;
    }

    // Consultar una asistencia médica por su identificador
    public AsistenciaMedicaDTO consultarAsistenciaPorId(Long idAsistenciaMedica) {
        return null;
    }

    // Listar todas las asistencias médicas
    public List<AsistenciaMedicaDTO> listarTodasLasAsistencias() {
        return null;
    }

    // Actualizar una asistencia médica existente
    public AsistenciaMedicaDTO actualizarAsistencia(Long idAsistenciaMedica, AsistenciaMedicaDTO asistenciaMedicaDTO) {
        return null;
    }

    // Eliminar una asistencia médica
    public void eliminarAsistencia(Long idAsistenciaMedica) {

    }

    // Método de validación de asistencia médica
    private void validarAsistencia(AsistenciaMedicaDTO asistenciaMedicaDTO) {

    }
}

