package com.gleyson.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.repository.CategoriaRepositorio;
import com.gleyson.cursomc.services.excecap.MessagensExcecao;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositorio repositorio;
	
	public Categoria find(Integer id) {
		
		Categoria dados = repositorio.findOne(id);
		if(dados == null) {
			throw new MessagensExcecao("Objeto não encontrado, id:" + id + ",Tipo" + Categoria.class.getName());
		}
		
		return dados;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		
		find(obj.getId());
		return repositorio.save(obj);
	}

}
