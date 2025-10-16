package com.datadinner.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "mesas")
public class Mesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Positive(message = "El número de mesa debe ser mayor que 0")
	private int numero;

	@NotNull(message = "El estado de la mesa es obligatorio")
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Min(value = 1, message = "La capacidad debe ser al menos 1")
	private int capacidad;

	@Min(value = 0, message = "La coordenada X no puede ser negativa")
	private int x;

	@Min(value = 0, message = "La coordenada Y no puede ser negativa")
	private int y;

	@OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pedido> pedidos = new ArrayList<>();

	public enum Estado {
		LIBRE, OCUPADA, RESERVADA
	}

	public Mesa() {
	}

	public Mesa(int numero, Estado estado, int capacidad, int x, int y) {
		this.numero = numero;
		this.estado = estado;
		this.capacidad = capacidad;
		this.x = x;
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
