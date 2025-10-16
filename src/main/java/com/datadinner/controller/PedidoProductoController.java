package com.datadinner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.datadinner.model.PedidoProducto;
import com.datadinner.service.PedidoProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido-productos")
public class PedidoProductoController {

    private final PedidoProductoService service;

    public PedidoProductoController(PedidoProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoProducto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PedidoProducto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/pedido/{pedidoId}")
    public List<PedidoProducto> getByPedidoId(@PathVariable int pedidoId) {
        return service.getByPedidoId(pedidoId);
    }

    @PostMapping
    public PedidoProducto create(@Valid @RequestBody PedidoProducto pp) {
        return service.create(pp);
    }

    @PutMapping("/{id}")
    public PedidoProducto update(@PathVariable int id, @Valid @RequestBody PedidoProducto pp) {
        return service.update(id, pp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
