package com.architrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architrack.entities.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
