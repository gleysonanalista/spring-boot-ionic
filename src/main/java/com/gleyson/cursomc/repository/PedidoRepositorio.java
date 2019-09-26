package com.gleyson.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gleyson.cursomc.dominio.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
	
	

}
