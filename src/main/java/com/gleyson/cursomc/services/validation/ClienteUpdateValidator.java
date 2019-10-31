package com.gleyson.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.gleyson.cursomc.dominio.Cliente;
import com.gleyson.cursomc.dominio.enums.TipoCliente;
import com.gleyson.cursomc.dto.ClienteDTO;
import com.gleyson.cursomc.repository.ClienteRepositorio;
import com.gleyson.cursomc.resources.exception.CampoMessagens;
import com.gleyson.cursomc.services.validation.Utils.ValidacaoUtil;;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	ClienteRepositorio clienteRepo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer urid = Integer.parseInt(map.get("id"));
		
		List<CampoMessagens> lista = new ArrayList<>();
		
		
		Cliente aux = clienteRepo.findByEmail(objDto.getEmail());
		
		if(aux !=null && !aux.getId().equals(urid)) {
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
