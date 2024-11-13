package com.es.segurosinseguros.repository;

import com.es.segurosinseguros.model.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguroRepositoryAPI extends JpaRepository<Seguro, Long> {
}
