package com.gleyson.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gleyson.cursomc.dominio.Pagamento;

@Repository
public interface PagamentoRepositorio extends JpaRepository<Pagamento, Integer> {
	
	

}
