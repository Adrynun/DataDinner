package com.datadinner.controller;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datadinner.model.Pedido;
import com.datadinner.model.Factura;
import com.datadinner.service.FacturaService;
import com.datadinner.service.PedidoService;
import com.datadinner.service.JasperReportService;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

	private final FacturaService facturaService;
	private final PedidoService pedidoService;
	private final JasperReportService jasperReportService;

	public FacturaController(FacturaService facturaService, PedidoService pedidoService,
			JasperReportService jasperReportService) {
		this.facturaService = facturaService;
		this.pedidoService = pedidoService;
		this.jasperReportService = jasperReportService;
	}

	@PostMapping("/generar/{pedidoId}")
	public Factura generarFactura(@PathVariable int pedidoId) {
		Pedido pedido = pedidoService.getPedidoById(pedidoId);
		return facturaService.generarFactura(pedido);
	}

	@GetMapping("/pendientes")
	public List<Factura> getFacturasPendientes() {
		return facturaService.getFacturasPendientes();
	}

	@PostMapping("/cerrar/{id}")
	public void cerrarFactura(@PathVariable int id) {
		facturaService.cerrarFactura(id);
	}

	@GetMapping("/ticket/{pedidoId}")
	public ResponseEntity<byte[]> descargarTicket(@PathVariable int pedidoId) throws JRException {
		Pedido pedido = pedidoService.getPedidoById(pedidoId);
		byte[] pdf = jasperReportService.generarTicket(pedido);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket_" + pedidoId + ".pdf")
				.contentType(MediaType.APPLICATION_PDF).body(pdf);
	}
}
