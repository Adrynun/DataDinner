package com.datadinner.service;

import com.datadinner.exception.ProductoNotFoundException;
import com.datadinner.model.Producto;
import com.datadinner.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

	private final ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}

	public Producto getProductoById(int id) {
		return productoRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
	}

	public Producto saveProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	public void deleteProducto(int id) {
		Producto producto = getProductoById(id);
		productoRepository.delete(producto);
	}
	public List<Producto> getProductosByCategoriaId(int categoriaId) {
		return productoRepository.findByCategoriaId(categoriaId);
	}
}
