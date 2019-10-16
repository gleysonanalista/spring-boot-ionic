package com.gleyson.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.Cliente;
import com.gleyson.cursomc.dto.ClienteDTO;
import com.gleyson.cursomc.repository.ClienteRepositorio;
import com.gleyson.cursomc.services.excecap.DataIntegrityException;
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
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		
		Cliente novoObjeto = buscar(obj.getId());
		updateData(novoObjeto, obj);
		return repositorio.save(novoObjeto);
	}
	
	public void delete(Integer id) {
		buscar(id);
		
		try {
			repositorio.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois há entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll(){
		return repositorio.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorio.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objeto) {
		return new Cliente(objeto.getId(), objeto.getNome(), objeto.getEmail(), null, null);
	}
	
	private void updateData(Cliente novoObjeto, Cliente obj) {
		novoObjeto.setNome(obj.getNome());
		novoObjeto.setEmail(obj.getEmail());
	}


}
