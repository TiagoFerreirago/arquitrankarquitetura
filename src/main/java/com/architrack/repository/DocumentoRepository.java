package com.architrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architrack.entities.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
