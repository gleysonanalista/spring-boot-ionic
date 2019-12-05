package com.gleyson.cursomc.dominio.enums;

public enum Perfil {
	
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private Integer codigo;
	private String descricao;
	
	private Perfil(Integer cod, String desc) {
		this.codigo = cod;
		this.descricao = desc;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (Perfil tipo : Perfil.values()) {
			
			if(cod.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("id invalido");
	}
}
