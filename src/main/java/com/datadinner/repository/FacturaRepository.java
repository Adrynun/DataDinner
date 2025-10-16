package com.datadinner.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datadinner.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

	List<Factura> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

	List<Factura> findByCerradaFalse(); // Tickets pendientes de cierre
}
