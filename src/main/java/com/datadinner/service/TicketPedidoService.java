package com.datadinner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datadinner.model.TicketPedido;
import com.datadinner.repository.TicketPedidoRepository;

@Service
public class TicketPedidoService {

	private final TicketPedidoRepository repository;

	public TicketPedidoService(TicketPedidoRepository repository) {
		this.repository = repository;
	}

	public TicketPedido save(TicketPedido ticket) {
		return repository.save(ticket);
	}

	public List<TicketPedido> getTicketsPendientes() {
		return repository.findByEstado("PENDIENTE");
	}

	public TicketPedido marcarCobrado(int id) {
		TicketPedido ticket = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ticket no encontrado: " + id));
		ticket.setEstado("COBRADO");
		return repository.save(ticket);
	}
}
