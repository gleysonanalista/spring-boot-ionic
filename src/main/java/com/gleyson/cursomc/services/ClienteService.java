package com.gleyson.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.Cliente;
import com.gleyson.cursomc.repository.ClienteRepositorio;
import com.gleyson.cursomc.services.excecap.MessagensExcecao;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepositorio repositorio;
	
	public Cliente buscar(Integer id) {
		
		Cliente dados = repositorio.findOne(id);
		if(dados == null) {
			throw new MessagensExcecao("Objeto não encontrado, id:" + id + ",Tipo" + Cliente.class.getName());
		}
		
		return dados;
	}

}
