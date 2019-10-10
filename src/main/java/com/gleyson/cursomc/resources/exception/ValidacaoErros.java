package com.gleyson.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoErros extends StandardErro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidacaoErros(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}
	
	public List<CampoMessagens> lista = new ArrayList<>();
	

	public List<CampoMessagens> getErrros(){
		return lista;
	}

	public void adicionarErro(String campoNome, String messagem) {
		lista.add(new CampoMessagens(campoNome, messagem));
	}

}
