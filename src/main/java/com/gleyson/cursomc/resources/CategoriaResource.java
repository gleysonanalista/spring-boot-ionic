package com.gleyson.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {

		Categoria dados = servico.buscar(id);
		return ResponseEntity.ok().body(dados);

		/*
		 * Categoria cat1 = new Categoria(1, "Gleyson Costa"); Categoria cat2 = new
		 * Categoria(2,"Bruno Costa");
		 * 
		 * List<Categoria> lista = new ArrayList<>(); lista.add(cat1); lista.add(cat2);
		 * 
		 * return lista;
		 */
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = servico.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
