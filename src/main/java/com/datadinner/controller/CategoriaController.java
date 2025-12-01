package com.datadinner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.datadinner.model.Categoria;
import com.datadinner.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Obtener todas las categorías
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // Obtener categoría por ID
    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }
}
