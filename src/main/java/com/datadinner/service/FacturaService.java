package com.datadinner.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.datadinner.model.Factura;
import com.datadinner.model.Pedido;
import com.datadinner.repository.FacturaRepository;

@Service
public class FacturaService {

	private final FacturaRepository facturaRepository;

	public FacturaService(FacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository;
	}

	public Factura generarFactura(Pedido pedido) {
		BigDecimal total = pedido.getProductos().stream() 
				.map(pp -> pp.getProducto().getPrecio().multiply(BigDecimal.valueOf(pp.getCantidad())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		Factura factura = new Factura(pedido, total);
		return facturaRepository.save(factura);
	}

	public List<Factura> getFacturasPendientes() {
		return facturaRepository.findByCerradaFalse();
	}

	public List<Factura> getFacturasPorRango(LocalDateTime inicio, LocalDateTime fin) {
		return facturaRepository.findByFechaBetween(inicio, fin);
	}

	public void cerrarFactura(int id) {
		Factura factura = facturaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Factura no encontrada"));
		factura.setCerrada(true);
		facturaRepository.save(factura);
	}
}
