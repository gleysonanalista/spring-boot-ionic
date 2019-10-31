package com.gleyson.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gleyson.cursomc.dominio.Cliente;
import com.gleyson.cursomc.dominio.enums.TipoCliente;
import com.gleyson.cursomc.dto.ClienteNewDTO;
import com.gleyson.cursomc.repository.ClienteRepositorio;
import com.gleyson.cursomc.resources.exception.CampoMessagens;
import com.gleyson.cursomc.services.validation.Utils.ValidacaoUtil;;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepositorio clienteRepo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<CampoMessagens> lista = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !ValidacaoUtil.isValidCPF(objDto.getCpfOuCnpj())) {
			lista.add(new CampoMessagens("cpf", "CPF Invalido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !ValidacaoUtil.isValidaCNPJ(objDto.getCpfOuCnpj())) {
			lista.add(new CampoMessagens("cnpj", "CNPJ Invalido"));
		}
		
		Cliente aux = clienteRepo.findByEmail(objDto.getEmail());
		
		if(aux !=null) {
			lista.add(new CampoMessagens("email", "email já existe no banco de dados"));
		}
		
		// inclua os testes aqui, inserindo erros na lista

		for (CampoMessagens e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getCampoNome())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}

}
