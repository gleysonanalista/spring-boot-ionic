package com.gleyson.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gleyson.cursomc.dominio.Endereco;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Integer> {
	
	

}
