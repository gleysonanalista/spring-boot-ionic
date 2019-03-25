package com.gleyson.cursomc.services.excecap;

public class MessagensExcecao extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessagensExcecao(String msg) {
		super(msg);
	}

	public MessagensExcecao(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
}