package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.exception.NotFoundException;
import com.es.segurosinseguros.model.AsistenciaMedica;
import com.es.segurosinseguros.repository.AsistenciaMedicaRepositoryAPI;
import com.es.segurosinseguros.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenciaMedicaService {

    @Autowired
    private AsistenciaMedicaRepositoryAPI asistenciaMedicaRepository;

    @Autowired
    private Mapper mapper;

    public AsistenciaMedicaDTO insert(AsistenciaMedicaDTO asistenciaMedicaDTO) {
        validate(asistenciaMedicaDTO);

        AsistenciaMedica asistenciaMedica = Mapper.asistenciaMedicaDTOToEntity(asistenciaMedicaDTO);
        AsistenciaMedica savedAsistencia = asistenciaMedicaRepository.save(asistenciaMedica);
        return Mapper.asistenciaMedicaEntityToDTO(savedAsistencia);
    }

    public AsistenciaMedicaDTO getById(String id) {
        AsistenciaMedica asistencia = asistenciaMedicaRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new NotFoundException("Asistencia médica no encontrada con el ID: " + id));
        return Mapper.asistenciaMedicaEntityToDTO(asistencia);
    }

    public List<AsistenciaMedicaDTO> getAll() {
        List<AsistenciaMedica> asistencias = asistenciaMedicaRepository.findAll();
        return asistencias.stream().map(Mapper::asistenciaMedicaEntityToDTO).collect(Collectors.toList());
    }

    public AsistenciaMedicaDTO update(Long id, AsistenciaMedicaDTO asistenciaMedicaDTO) {
        validate(asistenciaMedicaDTO);

        AsistenciaMedica asistencia = asistenciaMedicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Asistencia médica no encontrada con el ID: " + id));

        asistencia.getSeguro().setIdSeguro(asistenciaMedicaDTO.getIdSeguro());
        AsistenciaMedica updatedAsistencia = asistenciaMedicaRepository.save(asistencia);
        return Mapper.asistenciaMedicaEntityToDTO(updatedAsistencia);
    }

    public void deleteById(Long id) {
        AsistenciaMedica asistencia = asistenciaMedicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Asistencia médica no encontrada con el ID: " + id));
        asistenciaMedicaRepository.delete(asistencia);
    }

    public AsistenciaMedicaDTO findById(Long id) {

        AsistenciaMedica u = asistenciaMedicaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Asistencia médica no encontrada con el ID: " + id));

        return Mapper.asistenciaMedicaEntityToDTO(u);

    }

// Método de validación de asistencia médica
    public void validate(AsistenciaMedicaDTO asistenciaMedicaDTO) {
        // Validación de campos

        if (asistenciaMedicaDTO.getBreveDescripcion() == null || asistenciaMedicaDTO.getBreveDescripcion().isEmpty()) {
            throw new RuntimeException("El campo breveDescripcion no puede estar vacío.");
        }

        if (asistenciaMedicaDTO.getLugar() == null || asistenciaMedicaDTO.getLugar().isEmpty()) {
            throw new RuntimeException("El campo lugar no puede estar vacío.");
        }

        if (asistenciaMedicaDTO.getExplicacion() == null || asistenciaMedicaDTO.getExplicacion().isEmpty()) {
            throw new RuntimeException("El campo explicación no puede estar vacío.");
        }

        if (asistenciaMedicaDTO.getTipoAsistencia() == null || asistenciaMedicaDTO.getTipoAsistencia().isEmpty()) {
            throw new RuntimeException("El campo tipoAsistencia no puede ser nulo.");
        }

        if (asistenciaMedicaDTO.getFecha() == null) {
            throw new RuntimeException("El campo fecha no puede ser nulo.");
        }

        if (asistenciaMedicaDTO.getHora() == null) {
            throw new RuntimeException("El campo hora no puede ser nulo.");
        }

        if (asistenciaMedicaDTO.getImporte() <= 0.00) {
            throw new RuntimeException("El campo importe debe ser mayor que 0.");
        }

        // Añadir más validaciones según sea necesario
    }
}
