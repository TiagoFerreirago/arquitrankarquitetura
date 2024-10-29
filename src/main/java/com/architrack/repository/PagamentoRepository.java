package com.architrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architrack.entities.Pagamentos;

public interface PagamentoRepository extends JpaRepository<Pagamentos, Long> {

}
