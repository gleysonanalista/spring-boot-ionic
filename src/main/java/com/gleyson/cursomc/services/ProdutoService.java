package com.gleyson.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.dominio.Produto;
import com.gleyson.cursomc.repository.CategoriaRepositorio;
import com.gleyson.cursomc.repository.ProdutoRepositorio;
import com.gleyson.cursomc.services.excecap.MessagensExcecao;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepositorio repositorio;
	
	@Autowired
	private CategoriaRepositorio categoria;
	
	public Produto buscar(Integer id) {
		
		Produto dados = repositorio.findOne(id);
		if(dados == null) {
			throw new MessagensExcecao("Objeto não encontrado, id:" + id + ",Tipo" + Produto.class.getName());
		}
		
		return dados;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoria.findAll(ids);
		return repositorio.search(nome, categorias, pageRequest);
		
	}

}
