package com.gleyson.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gleyson.cursomc.dominio.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {
	
	

}
