package com.gleyson.cursomc.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gleyson.cursomc.dominio.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	
}
