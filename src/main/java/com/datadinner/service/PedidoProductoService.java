package com.datadinner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datadinner.exception.PedidoProductoNotFoundException;
import com.datadinner.model.PedidoProducto;
import com.datadinner.repository.PedidoProductoRepository;

@Service
public class PedidoProductoService {

	private final PedidoProductoRepository repository;

	public PedidoProductoService(PedidoProductoRepository repository) {
		this.repository = repository;
	}

	public List<PedidoProducto> getAll() {
		return repository.findAll();
	}

	public PedidoProducto getById(int id) {
		return repository.findById(id).orElseThrow(() -> new PedidoProductoNotFoundException(id));
	}

	public List<PedidoProducto> getByPedidoId(int pedidoId) {
		return repository.findByPedidoId(pedidoId);
	}

	public PedidoProducto create(PedidoProducto pp) {
		return repository.save(pp);
	}

	public PedidoProducto update(int id, PedidoProducto pp) {
		PedidoProducto existing = getById(id);
		existing.setPedido(pp.getPedido());
		existing.setProducto(pp.getProducto());
		existing.setCantidad(pp.getCantidad());
		return repository.save(existing);
	}

	public void delete(int id) {
		PedidoProducto existing = getById(id);
		repository.delete(existing);
	}
}
