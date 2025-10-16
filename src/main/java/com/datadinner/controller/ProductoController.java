package com.datadinner.controller;

import com.datadinner.model.Producto;
import com.datadinner.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable int id) {
        return productoService.getProductoById(id);
    }

    @PostMapping
    public Producto createProducto(@Valid @RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        Producto existing = productoService.getProductoById(id);
        existing.setNombre(producto.getNombre());
        existing.setPrecio(producto.getPrecio());
        existing.setCategoria(producto.getCategoria()); 
        return productoService.saveProducto(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable int id) {
        productoService.deleteProducto(id);
    }
}
