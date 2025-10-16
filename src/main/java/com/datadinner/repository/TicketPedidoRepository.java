package com.datadinner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datadinner.model.TicketPedido;

@Repository
public interface TicketPedidoRepository extends JpaRepository<TicketPedido, Integer> {
	List<TicketPedido> findByEstado(String estado);
}
