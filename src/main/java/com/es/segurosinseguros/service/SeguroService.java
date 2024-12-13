package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.NotFoundException;
import com.es.segurosinseguros.model.Seguro;
import com.es.segurosinseguros.repository.SeguroRepositoryAPI;
import com.es.segurosinseguros.utils.Mapper;
import com.es.segurosinseguros.utils.NIFValidator;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepositoryAPI seguroRepository;

    @Autowired
    private Mapper mapper;

    // Crear un seguro
    public SeguroDTO insert(SeguroDTO seguroDTO) {
        // Validar los datos del seguro (si es necesario)
        validate(seguroDTO);

        // Convertir el DTO a entidad utilizando el Mapper
        Seguro seguro = Mapper.seguroDTOToEntity(seguroDTO);

        // Guardar el seguro en la base de datos
        seguro = seguroRepository.save(seguro);

        // Convertir la entidad de vuelta a DTO para devolver
        return Mapper.seguroEntityToDTO(seguro);
    }

    public SeguroDTO findById(Long id) {

        Seguro u = seguroRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Seguro con id "+id+" no encontrado"));

        return Mapper.seguroEntityToDTO(u);

    }

    public boolean existsById(Long id) {
        return seguroRepository.findById(id).isPresent();
    }


    // Consultar un seguro por su identificador
    public SeguroDTO getById(String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido");
        }
        // Buscar el seguro por su id
        Seguro seguro = seguroRepository.findById(idL)
                .orElseThrow(() -> new NotFoundException("ID " + id + " no encontrado"));

        // Convertir la entidad encontrada a DTO y devolverla
        return Mapper.seguroEntityToDTO(seguro);
    }

    // Listar todos los seguros
    public List<SeguroDTO> getAll() {
        // Obtener todos los seguros de la base de datos
        List<Seguro> seguros = seguroRepository.findAll();

        // Convertir la lista de entidades a lista de DTOs
        return seguros
                .stream()
                .map(Mapper::seguroEntityToDTO)
                .collect(Collectors.toList());
    }

    public SeguroDTO update(Long idSeguro, SeguroDTO seguroDTO) {
        // Validar el seguro antes de actualizar
        validate(seguroDTO);

        // Buscar el seguro existente
        Seguro seguroExistente = seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new NotFoundException("Seguro no encontrado con el ID: " + idSeguro));

        // Actualizar los campos necesarios
        seguroExistente.setNif(seguroDTO.getNif());
        seguroExistente.setNombre(seguroDTO.getNombre());
        seguroExistente.setApe1(seguroDTO.getApe1());
        seguroExistente.setApe2(seguroDTO.getApe2());
        seguroExistente.setEdad(seguroDTO.getEdad());
        seguroExistente.setNumHijos(seguroDTO.getNumHijos());
        seguroExistente.setSexo(seguroDTO.getSexo());
        seguroExistente.setCasado(seguroDTO.isCasado());
        seguroExistente.setEmbarazada(seguroDTO.isEmbarazada());
        seguroExistente.setFechaCreacion(seguroDTO.getFechaCreacion());

        // Guardar el seguro actualizado
        seguroExistente = seguroRepository.save(seguroExistente);
        return Mapper.seguroEntityToDTO(seguroExistente);
    }

    // Eliminar un seguro
    public void deleteById(Long id) {

        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seguro no encontrado con el ID: " + id));

        // Eliminar el seguro de la base de datos
        seguroRepository.deleteById(seguro.getIdSeguro());
    }

    // Método de validación de seguro
    private void validate(SeguroDTO seguroDTO) {
        // Validación de NIF
        if (!NIFValidator.esNIFValido(seguroDTO.getNif())) {
            throw new RuntimeException("El campo NIF no tiene un formato válido.");
        }

        // Validación de otros campos, si es necesario
        if (seguroDTO.getNombre() == null || seguroDTO.getNombre().isEmpty()) {
            throw new RuntimeException("El campo nombre no puede estar vacío.");
        }

        if (seguroDTO.getApe1() == null || seguroDTO.getApe1().isEmpty()) {
            throw new RuntimeException("El campo primer apellido no puede estar vacío.");
        }

        // Validación de otros campos, si es necesario
        if (seguroDTO.getEdad() == null || seguroDTO.getEdad() < 18) {
            throw new RuntimeException("No es posible ser menor de edad para hacer un seguro.");
        }

        if(seguroDTO.isCasado() == false && seguroDTO.getNumHijos() > 0) {
            throw new RuntimeException("Un seguro no puede registrar hijos si no está casado.");
        }

        if (seguroDTO.getSexo().equals("Hombre") && seguroDTO.isEmbarazada()) {
            throw new RuntimeException("El campo embarazada no puede ser true si el asegurado es hombre.");
        }

        if (seguroDTO.getSexo() == null || seguroDTO.getSexo().isEmpty()) {
            throw new RuntimeException("El campo sexo no puede estar vacío.");
        }
    }

}

