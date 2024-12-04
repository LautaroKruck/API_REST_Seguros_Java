package com.es.segurosinseguros.repository;

import com.es.segurosinseguros.model.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeguroRepositoryAPI extends JpaRepository<Seguro, Long> {
    Optional<Seguro> findById(Long id);
}
