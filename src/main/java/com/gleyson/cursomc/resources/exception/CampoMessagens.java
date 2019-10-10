package com.gleyson.cursomc.resources.exception;

import java.io.Serializable;

public class CampoMessagens implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String campoNome;
	private String messagem;
	
	public CampoMessagens()
	{
		
	}

	public CampoMessagens(String campoNome, String messagem) {
		super();
		this.campoNome = campoNome;
		this.messagem = messagem;
	}

	public String getCampoNome() {
		return campoNome;
	}

	public void setCampoNome(String campoNome) {
		this.campoNome = campoNome;
	}

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}
}
