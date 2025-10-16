package com.datadinner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datadinner.exception.PedidoNotFoundException;
import com.datadinner.model.Pedido;
import com.datadinner.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
    }

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deletePedido(int id) {
        Pedido pedido = getPedidoById(id);
        pedidoRepository.delete(pedido);
    }
}
