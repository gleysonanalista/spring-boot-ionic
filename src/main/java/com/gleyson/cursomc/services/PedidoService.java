package com.gleyson.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.dominio.Pedido;
import com.gleyson.cursomc.repository.CategoriaRepositorio;
import com.gleyson.cursomc.repository.PedidoRepositorio;
import com.gleyson.cursomc.services.excecap.MessagensExcecao;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepositorio repositorio;
	
	public Pedido buscar(Integer id) {
		
		Pedido dados = repositorio.findOne(id);
		if(dados == null) {
			throw new MessagensExcecao("Objeto não encontrado, id:" + id + ",Tipo" + Pedido.class.getName());
		}
		
		return dados;
	}

}
