package com.datadinner.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datadinner.model.CierreCaja;
import com.datadinner.service.CierreCajaService;

@RestController
@RequestMapping("/cierre")
public class CierreCajaController {

	private final CierreCajaService service;

	public CierreCajaController(CierreCajaService service) {
		this.service = service;
	}

	@PostMapping
	public CierreCaja crearCierre(@RequestBody CierreCaja cierre) {
		return service.guardarCierre(cierre);
	}

	@GetMapping("/{fecha}")
	public CierreCaja getCierre(@PathVariable String fecha) {
		LocalDate localDate = LocalDate.parse(fecha); // formato "yyyy-MM-dd"
		return service.getCierrePorFecha(localDate);
	}
}
