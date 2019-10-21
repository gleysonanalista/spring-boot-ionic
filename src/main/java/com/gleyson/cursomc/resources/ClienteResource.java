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

import com.gleyson.cursomc.dominio.Cliente;
import com.gleyson.cursomc.dto.ClienteDTO;
import com.gleyson.cursomc.dto.ClienteNewDTO;
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO obj, @PathVariable Integer id){
		
		Cliente objDto = servico.fromDTO(obj);
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
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> lista = servico.findAll();
		List<ClienteDTO> listaDTO = lista.stream().map( obj-> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);

	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page" , defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage" , defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy" , defaultValue="nome") String orderBy, 
			@RequestParam(value="direction" , defaultValue="ASC") String direction) {

		Page<Cliente> lista = servico.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaDTO = lista.map( obj-> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDTO);

	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente obj = servico.fromDTO(objDto);
		obj = servico.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}


