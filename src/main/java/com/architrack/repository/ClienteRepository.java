package com.architrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architrack.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
