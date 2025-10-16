package com.datadinner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.datadinner.model.Pedido;
import com.datadinner.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // 1. Listar todos los pedidos
    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    // 2. Obtener un pedido por ID
    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable int id) {
        return pedidoService.getPedidoById(id);
    }

    // 3. Crear un nuevo pedido
    @PostMapping
    public Pedido createPedido(@Valid @RequestBody Pedido pedido) {
        return pedidoService.savePedido(pedido);
    }

    // 4. Actualizar un pedido existente
    @PutMapping("/{id}")
    public Pedido updatePedido(@PathVariable int id, @Valid @RequestBody Pedido pedido) {
        Pedido existing = pedidoService.getPedidoById(id);
        existing.setUsuario(pedido.getUsuario());
        existing.setMesa(pedido.getMesa());
        existing.setEstado(pedido.getEstado());
        existing.setFechaHora(pedido.getFechaHora());
        return pedidoService.savePedido(existing);
    }

    // 5. Eliminar un pedido
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable int id) {
        pedidoService.deletePedido(id);
    }
}
