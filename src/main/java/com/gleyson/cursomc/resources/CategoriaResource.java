package com.gleyson.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.dto.CategoriaDTO;
import com.gleyson.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria dados = servico.find(id);
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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = servico.fromDTO(objDto);
		obj = servico.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO obj, @PathVariable Integer id){
		
		Categoria objDto = servico.fromDTO(obj);
		objDto.setId(id);
		objDto = servico.update(objDto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		servico.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> lista = servico.findAll();
		List<CategoriaDTO> listaDTO = lista.stream().map( obj-> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);

	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page" , defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage" , defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy" , defaultValue="nome") String orderBy, 
			@RequestParam(value="direction" , defaultValue="ASC") String direction) {

		Page<Categoria> lista = servico.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaDTO = lista.map( obj-> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listaDTO);

	}
}
