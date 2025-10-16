package com.datadinner.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datadinner.model.CierreCaja;

@Repository
public interface CierreCajaRepository extends JpaRepository<CierreCaja, Integer> {
	Optional<CierreCaja> findByFecha(LocalDate fecha);
}
