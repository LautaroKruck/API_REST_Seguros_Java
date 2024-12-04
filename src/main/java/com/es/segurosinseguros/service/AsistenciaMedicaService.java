package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.exception.NotFoundException;
import com.es.segurosinseguros.model.AsistenciaMedica;
import com.es.segurosinseguros.model.Seguro;
import com.es.segurosinseguros.repository.AsistenciaMedicaRepositoryAPI;
import com.es.segurosinseguros.utils.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AsistenciaMedicaService {

    @Autowired
    private AsistenciaMedicaRepositoryAPI asistenciaMedicaRepository;

    // Crear una nueva asistencia médica
    public AsistenciaMedicaDTO insert(AsistenciaMedicaDTO asistenciaMedicaDTO) {
        // Validar los datos (si es necesario)
        validate(asistenciaMedicaDTO);

        // Convertir el DTO a entidad
        AsistenciaMedica asistenciaMedica = Mapper.asistenciaMedicaDTOToEntity(asistenciaMedicaDTO);

        // Guardar la asistencia médica en la base de datos
        asistenciaMedica = asistenciaMedicaRepository.save(asistenciaMedica);

        // Convertir la entidad de vuelta a DTO
        return Mapper.asistenciaMedicaEntityToDTO(asistenciaMedica);
    }


    // Consultar una asistencia médica por su identificador
    public AsistenciaMedicaDTO getById(String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido");
        }
        // Buscar la asistencia médica por su id
        AsistenciaMedica asistenciaMedica = asistenciaMedicaRepository.findById(idL)
                .orElseThrow(() -> new NotFoundException("ID " + id + " no encontrado"));

        // Convertir la entidad a DTO y devolver
        return Mapper.asistenciaMedicaEntityToDTO(asistenciaMedica);
    }

    // Listar todas las asistencias médicas
    public List<AsistenciaMedicaDTO> getAll() {
        // Obtener todas las asistencias médicas de la base de datos
        List<AsistenciaMedica> asistencias = asistenciaMedicaRepository.findAll();

        // Convertir la lista de entidades a lista de DTOs
        return asistencias.stream().map(Mapper::asistenciaMedicaEntityToDTO).toList();
    }

    // Actualizar una asistencia médica existente
    public AsistenciaMedicaDTO update(Long id, AsistenciaMedicaDTO asistenciaMedicaDTO) {
        // Validar los datos (si es necesario)
        validate(asistenciaMedicaDTO);

        // Buscar la asistencia médica existente en la base de datos
        AsistenciaMedica asistenciaMedicaExistente = asistenciaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asistencia médica no encontrada"));

        // Convertir el DTO a entidad, pero asignando solo el ID del seguro
        AsistenciaMedica asistenciaMedica = Mapper.asistenciaMedicaDTOToEntity(asistenciaMedicaDTO);

        // Actualizar la entidad con los datos del DTO
        asistenciaMedicaExistente.setBreveDescripcion(asistenciaMedica.getBreveDescripcion());
        asistenciaMedicaExistente.setLugar(asistenciaMedica.getLugar());
        asistenciaMedicaExistente.setExplicacion(asistenciaMedica.getExplicacion());
        asistenciaMedicaExistente.setTipoAsistencia(asistenciaMedica.getTipoAsistencia());
        asistenciaMedicaExistente.setFecha(asistenciaMedica.getFecha());
        asistenciaMedicaExistente.setHora(asistenciaMedica.getHora());
        asistenciaMedicaExistente.setImporte(asistenciaMedica.getImporte());

        // Si el DTO tiene un ID de seguro, actualizamos el seguro en la entidad
        if (asistenciaMedicaDTO.getIdSeguro() != null) {
            Seguro seguro = new Seguro();
            seguro.setIdSeguro(asistenciaMedicaDTO.getIdSeguro());  // Solo asignar el ID
            asistenciaMedicaExistente.setSeguro(seguro);
        }

        // Guardar la asistencia médica actualizada en la base de datos
        asistenciaMedicaExistente = asistenciaMedicaRepository.save(asistenciaMedicaExistente);

        // Convertir la entidad actualizada de vuelta a DTO
        return Mapper.asistenciaMedicaEntityToDTO(asistenciaMedicaExistente);
    }


    // Eliminar una asistencia médica
    public void deleteById(Long idAsistenciaMedica) {

        if (!asistenciaMedicaRepository.existsById(idAsistenciaMedica)) {
            throw new NotFoundException("Asistencia médica no encontrada con el id: " + idAsistenciaMedica);
        }

        // Eliminar la asistencia médica de la base de datos
        asistenciaMedicaRepository.deleteById(idAsistenciaMedica);
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
