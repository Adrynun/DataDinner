package com.datadinner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
	@Column(nullable = false, unique = true, length = 100)
	private String nombre;

	@Min(value = 0, message = "El precio debe ser mayor o igual a 0")
	@Column(nullable = false)
	private BigDecimal precio;

	@ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoProducto> pedidos = new ArrayList<>();

	
	public Producto() {
	}

	public Producto(String nombre, BigDecimal precio, Categoria categoria) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
