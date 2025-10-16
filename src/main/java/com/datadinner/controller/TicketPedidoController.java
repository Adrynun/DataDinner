package com.datadinner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datadinner.model.TicketPedido;
import com.datadinner.service.TicketPedidoService;

@RestController
@RequestMapping("/tickets")
public class TicketPedidoController {

	private final TicketPedidoService service;

	public TicketPedidoController(TicketPedidoService service) {
		this.service = service;
	}

	@GetMapping("/pendientes")
	public List<TicketPedido> getPendientes() {
		return service.getTicketsPendientes();
	}

	@PostMapping
	public TicketPedido crearTicket(@RequestBody TicketPedido ticket) {
		return service.save(ticket);
	}

	@PutMapping("/cobrar/{id}")
	public TicketPedido cobrarTicket(@PathVariable int id) {
		return service.marcarCobrado(id);
	}
}
