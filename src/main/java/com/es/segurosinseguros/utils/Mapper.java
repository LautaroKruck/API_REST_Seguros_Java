package com.es.segurosinseguros.utils;


import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.model.AsistenciaMedica;
import com.es.segurosinseguros.model.Seguro;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Mapper {

    // SeguroDTO a Seguro
    public static Seguro seguroDTOToEntity(SeguroDTO seguroDTO) {
        if (seguroDTO == null) return null;

        Seguro seguro = new Seguro();

        // Asignación del ID
        if (seguroDTO.getIdSeguro() != null) {
            seguro.setIdSeguro(seguroDTO.getIdSeguro());
        }

        seguro.setNif(seguroDTO.getNif());
        seguro.setNombre(seguroDTO.getNombre());
        seguro.setApe1(seguroDTO.getApe1());
        seguro.setApe2(seguroDTO.getApe2());
        seguro.setEdad(seguroDTO.getEdad());
        seguro.setNumHijos(seguroDTO.getNumHijos());
        seguro.setSexo(seguroDTO.getSexo());
        seguro.setCasado(seguroDTO.isCasado());
        seguro.setEmbarazada(seguroDTO.isEmbarazada());

        // Si tu entidad tiene fecha de creación, puedes asignarla también desde el DTO
        if (seguroDTO.getFechaCreacion() != null) {
            seguro.setFechaCreacion(seguroDTO.getFechaCreacion());
        }

        return seguro;
    }


    // Seguro a SeguroDTO
    public static SeguroDTO seguroEntityToDTO(Seguro seguro) {
        if (seguro == null) return null;
        SeguroDTO seguroDTO = new SeguroDTO();
        seguroDTO.setNif(seguro.getNif());
        seguroDTO.setNombre(seguro.getNombre());
        seguroDTO.setApe1(seguro.getApe1());
        seguroDTO.setApe2(seguro.getApe2());
        seguroDTO.setEdad(seguro.getEdad());
        seguroDTO.setNumHijos(seguro.getNumHijos());
        seguroDTO.setSexo(seguro.getSexo());
        seguroDTO.setCasado(seguro.isCasado());
        seguroDTO.setEmbarazada(seguro.isEmbarazada());

        // Asignación del ID
        if (seguro.getIdSeguro() != null) {
            seguroDTO.setIdSeguro(seguro.getIdSeguro());
        }

        return seguroDTO;
    }


    // AsistenciaMedicaDTO a AsistenciaMedica
    // AsistenciaMedicaDTO a AsistenciaMedica
    public static AsistenciaMedica asistenciaMedicaDTOToEntity(AsistenciaMedicaDTO dto) {
        if (dto == null) return null;

        AsistenciaMedica asistencia = new AsistenciaMedica();
        asistencia.setBreveDescripcion(dto.getBreveDescripcion());
        asistencia.setLugar(dto.getLugar());
        asistencia.setExplicacion(dto.getExplicacion());
        asistencia.setTipoAsistencia(dto.getTipoAsistencia());
        asistencia.setFecha(dto.getFecha());
        asistencia.setHora(dto.getHora());
        asistencia.setImporte(dto.getImporte());

        // Asignar el seguro si está presente
        if (dto.getIdSeguro() != null) {
            Seguro seguro = new Seguro();
            seguro.setIdSeguro(dto.getIdSeguro());  // Solo asignar el id del seguro
            asistencia.setSeguro(seguro);  // Asignar el seguro con solo el ID
        }

        return asistencia;
    }


    // AsistenciaMedica a AsistenciaMedicaDTO
    public static AsistenciaMedicaDTO asistenciaMedicaEntityToDTO(AsistenciaMedica asistencia) {
        if (asistencia == null) return null;

        AsistenciaMedicaDTO dto = new AsistenciaMedicaDTO();
        dto.setBreveDescripcion(asistencia.getBreveDescripcion());
        dto.setLugar(asistencia.getLugar());
        dto.setExplicacion(asistencia.getExplicacion());
        dto.setTipoAsistencia(asistencia.getTipoAsistencia());
        dto.setFecha(asistencia.getFecha());
        dto.setHora(asistencia.getHora());
        dto.setImporte(asistencia.getImporte());

        // Aquí asignamos solo el id del Seguro en lugar del objeto completo
        if (asistencia.getSeguro() != null) {
            dto.setIdSeguro(asistencia.getSeguro().getIdSeguro());
        }

        return dto;
    }

    // Lista de Seguro a Lista de SeguroDTO
    public static List<SeguroDTO> segurosEntityToDTOList(List<Seguro> seguros) {
        return seguros.stream()
                .map(Mapper::seguroEntityToDTO)
                .collect(Collectors.toList());
    }

    // Lista de AsistenciaMedica a Lista de AsistenciaMedicaDTO
    public static List<AsistenciaMedicaDTO> asistenciasEntityToDTOList(List<AsistenciaMedica> asistencias) {
        return asistencias.stream()
                .map(Mapper::asistenciaMedicaEntityToDTO)
                .collect(Collectors.toList());
    }
}

