package com.gleyson.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gleyson.cursomc.dominio.Pedido;
import com.gleyson.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService servico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Pedido dados = servico.buscar(id);
		return ResponseEntity.ok().body(dados);

		/*
		 * Pedido cat1 = new Pedido(1, "Gleyson Costa"); Pedido cat2 = new
		 * Pedido(2,"Bruno Costa");
		 * 
		 * List<Pedido> lista = new ArrayList<>(); lista.add(cat1); lista.add(cat2);
		 * 
		 * return lista;
		 */
	}

}
