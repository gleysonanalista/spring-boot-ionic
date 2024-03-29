package com.gleyson.cursomc.dominio;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ItemPedido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	@JsonIgnore
	@Embedded
	@Id
	private ItemPedidoPK id = new ItemPedidoPK();
	
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public ItemPedido() {
		
	}
	public ItemPedidoPK getId() {
		return id;
	}
	public void setId(ItemPedidoPK id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	@JsonIgnore
	public Produto getProduto() {
		return id.getProduto();
	}
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}
	
	
	
	@Override
	public String toString() {
		NumberFormat numero = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qte:");
		builder.append(getQuantidade());
		builder.append(", Preço Unitario: ");
		builder.append(numero.format(getPreco()));
		builder.append(", Subtotal:");
		builder.append(numero.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
}
