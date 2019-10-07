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

import com.gleyson.cursomc.dominio.Cliente;
import com.gleyson.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService servico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {

		Cliente dados = servico.buscar(id);
		return ResponseEntity.ok().body(dados);

		/*
		 * Cliente cat1 = new Cliente(1, "Gleyson Costa"); Cliente cat2 = new
		 * Cliente(2,"Bruno Costa");
		 * 
		 * List<Cliente> lista = new ArrayList<>(); lista.add(cat1); lista.add(cat2);
		 * 
		 * return lista;
		 */
	}

}
