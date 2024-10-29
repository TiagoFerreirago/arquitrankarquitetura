package com.architrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architrack.entities.Arquiteto;

public interface ArquitetoRepository extends JpaRepository<Arquiteto, Long> {

}
