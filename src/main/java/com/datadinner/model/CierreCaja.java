package com.datadinner.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cierre_caja")
public class CierreCaja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private int totalPedidos;

	@Column(nullable = false)
	private BigDecimal totalFacturado;

	@Column(columnDefinition = "json")
	private String resumenProductos; // JSON con detalle de productos

	public CierreCaja() {
	}

	public CierreCaja(LocalDate fecha, int totalPedidos, BigDecimal totalFacturado, String resumenProductos) {
		this.fecha = fecha;
		this.totalPedidos = totalPedidos;
		this.totalFacturado = totalFacturado;
		this.resumenProductos = resumenProductos;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getTotalPedidos() {
		return totalPedidos;
	}

	public void setTotalPedidos(int totalPedidos) {
		this.totalPedidos = totalPedidos;
	}

	public BigDecimal getTotalFacturado() {
		return totalFacturado;
	}

	public void setTotalFacturado(BigDecimal totalFacturado) {
		this.totalFacturado = totalFacturado;
	}

	public String getResumenProductos() {
		return resumenProductos;
	}

	public void setResumenProductos(String resumenProductos) {
		this.resumenProductos = resumenProductos;
	}
}
