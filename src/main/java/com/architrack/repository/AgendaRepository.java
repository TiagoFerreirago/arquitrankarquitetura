package com.architrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architrack.entities.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
