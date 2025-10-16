package com.datadinner.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.datadinner.model.CierreCaja;
import com.datadinner.repository.CierreCajaRepository;

@Service
public class CierreCajaService {

	private final CierreCajaRepository repository;

	public CierreCajaService(CierreCajaRepository repository) {
		this.repository = repository;
	}

	public CierreCaja guardarCierre(CierreCaja cierre) {
		return repository.save(cierre);
	}

	public CierreCaja getCierrePorFecha(LocalDate fecha) {
		return repository.findByFecha(fecha)
				.orElseThrow(() -> new RuntimeException("No existe cierre para la fecha: " + fecha));
	}
}
