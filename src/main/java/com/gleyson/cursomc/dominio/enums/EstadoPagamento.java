package com.gleyson.cursomc.dominio.enums;

public enum EstadoPagamento {
	
	PEDENTE(1,"Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(2, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private EstadoPagamento(Integer cod, String desc) {
		this.codigo = cod;
		this.descricao = desc;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (EstadoPagamento tipo : EstadoPagamento.values()) {
			
			if(cod.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("id invalido");
	}
}
