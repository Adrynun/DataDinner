package com.datadinner.service;

import org.springframework.stereotype.Service;

import com.datadinner.model.Categoria;
import com.datadinner.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria getCategoriaById(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría con id " + id + " no encontrada"));
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(int id) {
        Categoria categoria = getCategoriaById(id);
        categoriaRepository.delete(categoria);
    }
}
