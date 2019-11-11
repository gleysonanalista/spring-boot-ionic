package com.gleyson.cursomc.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gleyson.cursomc.dominio.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoCartao extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer numParcelas;
	
	
	public PagamentoCartao() {
	}


	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numParcelas) {
		super(id, estado, pedido);
		this.numParcelas = numParcelas;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNumParcelas() {
		return numParcelas;
	}


	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}
	
}
