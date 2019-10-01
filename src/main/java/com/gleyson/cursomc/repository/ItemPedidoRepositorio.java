package com.gleyson.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gleyson.cursomc.dominio.ItemPedido;

@Repository
public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Integer> {
	
	

}
