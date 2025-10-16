package com.datadinner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datadinner.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByMesaId(int mesaId);
    List<Pedido> findByUsuarioId(int usuarioId);

}
